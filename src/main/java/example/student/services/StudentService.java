package example.student.services;

import example.course.entities.Course;
import example.course.services.CourseService;
import example.deadline.entities.Deadline;
import example.deadline.services.DeadlineService;
import example.program.entities.Program;
import example.program.services.ProgramService;
import example.semester.entities.Semester;
import example.semester.services.SemesterService;
import example.student.entities.*;
import example.student.repositories.StudentRepository;
import example.submitred_courses.entities.SubmittedCourse;
import example.submitred_courses.entities.SubmittedCourseRequestDto;
import example.submitred_courses.entities.SubmittedCourseSaveDto;
import example.submitred_courses.services.SubmittedCourseService;
import example.student.entities.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {
    private StudentRepository repo;
    private ProgramService programService;
    private SemesterService semesterService;
    private CourseService courseService;
    private DeadlineService deadlineService;
    private SubmittedCourseService submittedCourseService;

    public Student addStudent(StudentRequestDto requestDto) {
        Optional<Program> program = programService.getProgramById(requestDto.programId);
        if (program.isPresent()) {
            return repo.save(new Student(null, requestDto.name, requestDto.surname, requestDto.personalNumber,
                    requestDto.birthday, requestDto.gender, requestDto.email, requestDto.password, requestDto.phoneNumber,
                    requestDto.address, false, 0, requestDto.level, null, null, program.get(), null, null, null));
        }
        throw new RuntimeException("Program does not exist");
    }

    public Optional<Student> getStudentById(Long studentId) {
        return repo.findById(studentId);
    }

    //Should try to optimize code, meaning same work with less query
    public SubmittedCourse submitCourse(SubmittedCourseRequestDto requestDto) {
        Optional<Deadline> deadline = deadlineService.getDeadlineById(requestDto.getDeadlineId());
        if (deadline.isPresent()) {
            if (deadline.get().getStartDate().isBefore(Instant.now())) {
                if (deadline.get().getEndDate().isAfter(Instant.now())) {
                    Optional<Student> student = getStudentById(requestDto.getStudentId());
                    if (student.isPresent()) {
                        Optional<Course> course = courseService.getCourseById(requestDto.getCourseId());
                        if (course.isPresent()) {
                            if (repo.checkIfCourseIsRegisteredByStudent(requestDto.getStudentId(), requestDto.getCourseId())) {
                                if (repo.checkIfSubmittedCourseIsNotAccepted(requestDto.getStudentId(), requestDto.getCourseId())) {
                                    return submittedCourseService.addSubmittedCourse(
                                            new SubmittedCourseSaveDto(Instant.now(), deadline.get(), student.get(), course.get()));
                                }
                                throw new RuntimeException("This course is submitted before and the grade is accepted by student");
                            }
                            throw new RuntimeException("Course is not registered yet by student");
                        }
                        throw new RuntimeException("Course does not exist");
                    }
                    throw new RuntimeException("Student does not exist");
                }
                throw new RuntimeException("Deadline is closed");
            }
            throw new RuntimeException("Deadline is not opened yet");
        }
        throw new RuntimeException("Deadline does not exist");
    }

    public void addSemester(Long studentId, Long semesterId) {
        Optional<Semester> semester = semesterService.getSemesterById(semesterId);
        if (semester.isPresent()) {
            if (repo.checkIfSemesterCanBeAdded(studentId, semester.get().getPassability(),
                    semester.get().getProgram().getId())) {
                if (semester.get().getEndDate().isAfter(Instant.now())) {
                    repo.addStudentSemesterRelation(studentId, semesterId);
                    return;
                }
                throw new RuntimeException("This semester can't be added because date has expired");
            }
            throw new RuntimeException("Student does not have enough credits to add this semester or programs of semester and student didn't match");
        }
        throw new RuntimeException("Semester does not exist");
    }
    public void addStudentCourses(Long studentId, Long courseId) {
        Optional<Course> course = courseService.getCourseById(courseId);
        if (course.isPresent()) {
            if (repo.checkIfCourseCanBeAdded(studentId,
                    course.get().getProgram().getId(), course.get().getSemesterNumber())) {
                repo.addStudentCourses(studentId, courseId);
                return;
            }
            throw new RuntimeException("Course can't be added");
        }
        throw new RuntimeException("Course does not exist");
    }

    public Page<StudentResponseDto> getAllStudentsOfGivenProgram(Long programId, Integer page, Integer size) {
        return repo.getStudentsOfGivenProgram(programId, PageRequest.of(page, size));
    }

    public List<StudentSemestersDto> getSemestersOfStudent(Long studentId) {
        return repo.getSemestersOfStudent(studentId);
    }

    public List<StudentCoursesDto> getAllStudentCourses(Long studentId) {
        return repo.getAllStudentCourses(studentId);
    }
    //when a student is deleted all courses,submittedCourses,deadline,semesters relation should be deleted
    public void deleteStudentsOfGivenProgram(Long programId) {
        repo.deleteStudentsByProgramId(programId);
    }

    public void deleteStudentsOfGivenPrograms(List<Long> programsIds) {
        repo.deleteStudentsByProgramsIds(programsIds);
    }

    public void deleteStudentById(Long studentId) {
        repo.deleteById(studentId);
    }

    public List<StudentCourses> getUnSubmittedCourses(Long studentId, Long deadlineId) {
        return repo.getUnSubmittedCourses(studentId, deadlineId);
    }

    public List<Student> getStudentUsingNativeQuery(Long studentId) {
        return repo.getStudentUsingNativeQuery(studentId);
    }
}
