package example.submitred_courses.repositories;

import example.submitred_courses.entities.SubmittedCourse;
import example.submitred_courses.entities.SubmittedCourseResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Repository
public interface SubmittedCourseRepository extends JpaRepository<SubmittedCourse, Long> {

    @Modifying
    @Transactional
    @Query("update SubmittedCourse s set s.grade = ?4, s.placedDate =?5, s.isAccepted = true where s.id = ?1 and s.deadline.id = ?2 and s.course.id = ?3")
    void addStudentGrade(Long studentId, Long deadlineId, Long courseId, Integer grade, Instant placedDate);

    @Query("select case when count(s)>0 then true else false end from SubmittedCourse s where s.student.id =?1 and s.deadline.id=?2 and s.course.id = ?3")
    boolean checkIfCourseIsSubmitted(Long studentId, Long deadlineId, Long courseId);

    @Query("select new example.submitred_courses.entities.SubmittedCourseResponseDto(s.id,c.name,c.id,s.student.id,s.grade," +
            "s.isAccepted,s.placedDate,s.submittedDate,false) from SubmittedCourse s inner join s.course c " +
            " inner join s.deadline d where s.student.id = ?1 and s.deadline.id = ?2 and d.endDate <= ?3")
    List<SubmittedCourseResponseDto> getSubmittedCourseByDeadlineIdExpiredDate(Long studentId, Long deadlineId, Instant time);

    @Query("select new example.submitred_courses.entities.SubmittedCourseResponseDto(s.id,c.name,c.id,s.student.id,s.grade," +
            "s.isAccepted,s.placedDate,s.submittedDate,true) from SubmittedCourse s inner join s.course c " +
            " inner join s.deadline d where s.student.id = ?1 and s.deadline.id = ?2 and d.endDate > ?3")
    List<SubmittedCourseResponseDto> getSubmittedCourseByDeadlineIdNotExpired(Long studentId, Long deadlineId, Instant time);

    @Query("select case when count(s)>0 then true else false end from SubmittedCourse s where s.student.id = ?1 and s.deadline.id = ?2")
    boolean checkIfSubmittedCourseExist(Long studentId, Long deadlineId);

    @Modifying
    @Transactional
    @Query("update SubmittedCourse s set s.isAccepted = false where  s.student.id = ?1 and s.deadline.id= ?2 and s.course.id= ?3")
    void refuseGradeOfGivenCourse(Long studentId, Long deadlineId, Long courseId);

    @Query("select case when count(s)>0 then true else false end from SubmittedCourse s " +
            "where s.student.id = ?1 and s.deadline.id = ?2 and s.course.id = ?3 and s.grade is not NULL")
    boolean checkIfCourseIsAssessed(Long studentId,Long deadlineId, Long courseId);
}
