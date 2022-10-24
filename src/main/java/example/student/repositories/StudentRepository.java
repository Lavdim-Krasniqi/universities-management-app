package example.student.repositories;

import example.student.entities.*;
import example.student.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("select new example.student.entities.StudentResponseDto(s.id,s.name,s.surname,s.personalNumber,s.birthday,s.gender,s.email,s.password," +
            "s.phoneNumber,s.address,s.isGraduated,s.createdDate,s.lastModifiedDate,s.program.id) from Student s where s.program.id = ?1")
    Page<StudentResponseDto> getStudentsOfGivenProgram(Long programId, Pageable pageable);

    @Modifying
    @Transactional
    @Query("delete from Student s where s.program.id = ?1")
    void deleteStudentsByProgramId(Long programId);

    @Modifying
    @Transactional
    @Query("delete from Student s where s.program.id in ?1")
    void deleteStudentsByProgramsIds(List<Long> programsIds);

    @Modifying
    @Transactional
    @Query(value = "insert into student_courses (student_id,course_id) values (?1,?2)", nativeQuery = true)
    void addStudentCourses(Long studentId, Long courseId);

    @Modifying
    @Transactional
    @Query(value = "insert into student_semesters (student_id,semester_id) values (?1,?2)", nativeQuery = true)
    void addStudentSemesterRelation(Long studentId, Long semesterId);

    //here I should check semester by number so student can't add semester 2 if semester 1 is not registered before
    @Query("select case when count(s)>0 then true else false end from Student s where " +
            "s.id = ?1 and s.program.id = ?3 and s.credits >= ?2 ")
    boolean checkIfSemesterCanBeAdded(Long studentId, Integer thresholdOfPassing, Long semesterProgramId);

    @Query("select case when count(s)>0 then true else false end  from Student s join s.semesters sm where " +
            " s.id =?1 and s.program.id = ?2 and sm.id =?3")
    boolean checkIfCourseCanBeAdded(Long studentId, Long courseProgramId, Integer semesterNumber);

    @Query("select case when count(s)>0 then true else false end from Student s join s.courses c where s.id = ?1 and c.id = ?2")
    boolean checkIfCourseIsRegisteredByStudent(Long studentId, Long courseId);

    @Query("select case when count(s)>0 then false else true end from Student s join s.submittedCourses c where s.id = ?1 " +
            "and c.id = ?2 and c.isAccepted = true")
    boolean checkIfSubmittedCourseIsNotAccepted(Long studentId, Long courseId);

    @Query("select new example.student.entities.StudentSemestersDto(s.id,s.name,sm.id,sm.program.id,sm.number,sm.level,sm.description,sm.credits,sm.passability," +
            "sm.startDate,sm.endDate) from Student s join s.semesters sm where s.id = ?1")
    List<StudentSemestersDto> getSemestersOfStudent(Long studentId);

    @Query("select new example.student.entities.StudentCoursesDto(s.id,s.name,c.id,c.name,c.credits,c.type,c.program.id," +
            "c.professor.id,c.semesterNumber) from Student s join s.courses c where s.id = ?1")
    List<StudentCoursesDto> getAllStudentCourses(Long studentId);

    @Query(value = "select c.* from student s inner join student_courses sc on s.id=sc.student_id inner join course c on " +
            "sc.course_id = c.id where s.id = ?1 and c.id not in (select sc1.course_id from student s1 inner join submitted_course sc1 " +
            "on s1.id = sc1.student_id where s1.id = ?1 and sc1.is_accepted = true or sc1.deadline_id= ?2)", nativeQuery = true)
    List<StudentCourses> getUnSubmittedCourses(Long studentId, Long deadlineId);

    @Query(value = "select s.* from student s  where s.id = ?1", nativeQuery = true)
    List<Student> getStudentUsingNativeQuery(Long studentId);
}