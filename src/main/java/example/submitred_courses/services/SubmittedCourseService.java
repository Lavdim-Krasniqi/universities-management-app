package example.submitred_courses.services;

import example.course.entities.Course;
import example.course.services.CourseService;
import example.deadline.services.DeadlineService;
import example.submitred_courses.entities.GradeRequestDto;
import example.submitred_courses.entities.SubmittedCourse;
import example.submitred_courses.entities.SubmittedCourseResponseDto;
import example.submitred_courses.entities.SubmittedCourseSaveDto;
import example.submitred_courses.repositories.SubmittedCourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SubmittedCourseService {
    private SubmittedCourseRepository repo;
    private CourseService courseService;

    private DeadlineService deadlineService;

    public SubmittedCourse addSubmittedCourse(SubmittedCourseSaveDto saveDto) {
        return repo.save(new SubmittedCourse(null, null, false, null, saveDto.submittedDate,
                null, null, saveDto.deadline, saveDto.student, saveDto.course));
    }

    public Optional<SubmittedCourse> getSubmittedCourseById(Long id) {
        return repo.findById(id);
    }

    public void addStudentGrade(GradeRequestDto requestDto) {
        if (repo.checkIfCourseIsSubmitted(requestDto.studentId, requestDto.deadlineId, requestDto.courseId)) {
            Optional<Course> course = courseService.getCourseById(requestDto.courseId);
            if (course.isPresent()) {
                if (course.get().getProfessor().getId().equals(requestDto.professorId)) {
                    repo.addStudentGrade(requestDto.studentId, requestDto.deadlineId, requestDto.courseId, requestDto.grade, Instant.now());
                    return;
                }
                throw new RuntimeException("Given Id of professor doesn't match professorId of given course");
            }
            throw new RuntimeException("Course does not exist");
        }
        throw new RuntimeException("Course is not submitted yet by student");
    }

    public List<SubmittedCourseResponseDto> getSubmittedCourseByDeadlineId(Long studentId, Long deadlineId) {
        if (repo.checkIfSubmittedCourseExist(studentId, deadlineId)) {
            if (deadlineService.checkIfDeadlineIsExpired(deadlineId)) {
                return repo.getSubmittedCourseByDeadlineIdExpiredDate(studentId, deadlineId, Instant.now());
            } else return repo.getSubmittedCourseByDeadlineIdNotExpired(studentId, deadlineId, Instant.now());
        }
        throw new RuntimeException("SubmittedCourses does not exist");
    }


    public void refuseGradeOfGivenCourse(Long studentId, Long deadlineId, Long courseId) {
        //check if it exists
        //check if course is assessed by professor
        //check if grade can be changed
        if (repo.checkIfCourseIsSubmitted(studentId, deadlineId, courseId)) {
            if (repo.checkIfCourseIsAssessed(studentId, deadlineId, courseId)) {
                if (!deadlineService.checkIfDeadlineIsExpired(deadlineId)) {
                    repo.refuseGradeOfGivenCourse(studentId, deadlineId, courseId);
                }
                throw new RuntimeException("Grade cant be changed deadline has expired");
            }
            throw new RuntimeException("Course is not assessed by professor yet so you can't refuse it");
        }
        throw new RuntimeException("This course is not submitted");


    }

}
