package example.course.repositories;

import example.course.entities.Course;
import example.course.entities.CourseResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {


    @Query("select new example.course.entities.CourseResponseDto(c.id,c.name,c.credits,c.type,c.program.id,c.professor.id,c.semesterNumber) " +
            "from Course c where c.program.id = ?1")
    Page<CourseResponseDto> getAllCoursesOfGivenProgram(Long programId, Pageable pageable);

    @Query("select new example.course.entities.CourseResponseDto(c.id,c.name,c.credits,c.type,c.program.id,c.professor.id,c.semesterNumber) " +
            "from Course c where c.program.id = ?1 and c.professor.id =?2")
    Page<CourseResponseDto> getCoursesByProgramAndProfessor(Long programId, Long professorId, Pageable pageable);

    @Query("select new example.course.entities.CourseResponseDto(c.id,c.name,c.credits,c.type,c.program.id,c.professor.id,c.semesterNumber) " +
            "from Course c where c.professor.id = ?1")
    Page<CourseResponseDto> getCoursesByProfessorId(Long professorId, Pageable pageable);

    @Query("select new example.course.entities.CourseResponseDto(c.id,c.name,c.credits,c.type,c.program.id,c.professor.id,c.semesterNumber) " +
            "from Course c where c.program.id = ?1 and c.semesterNumber =?2")
    Page<CourseResponseDto> getCoursesByProgramAndSemester(Long programId, Integer semesterNumber, Pageable pageable);

    @Modifying
    @Transactional
    @Query("delete from Course c where c.program.id = ?1")
    void deleteByProgramId(Long programId);

    @Modifying
    @Transactional
    @Query("delete from Course c where c.program.id in ?1")
    void deleteByProgramsIds(List<Long> programsIds);

    @Modifying
    @Transactional
    @Query("delete from Course c where c.professor.id = ?1")
    void deleteByProfessorId(Long professorId);

    @Modifying
    @Transactional
    @Query("delete from Course c where c.professor.id in ?1")
    void deleteByProfessorsIds(List<Long> professorsIds);



}
