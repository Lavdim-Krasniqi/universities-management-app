package example.student.controllers;

import example.student.entities.*;
import example.student.entities.*;
import example.student.services.StudentService;
import example.submitred_courses.entities.SubmittedCourse;
import example.submitred_courses.entities.SubmittedCourseRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private StudentService service;

    @PostMapping("/addStudent")
    public Student addStudent(@RequestBody StudentRequestDto requestDto) {
        return service.addStudent(requestDto);
    }

    @GetMapping("/getStudents/{programId}/{book}/{size}")
    public Page<StudentResponseDto> getStudentsOfGivenProgram(@PathVariable Long programId, @PathVariable Integer book,
                                                              @PathVariable Integer size) {
        return service.getAllStudentsOfGivenProgram(programId, book, size);
    }

    @GetMapping("/getSemestersOfStudent/{studentId}")
    public List<StudentSemestersDto> getSemestersOfStudent(@PathVariable Long studentId) {
        return service.getSemestersOfStudent(studentId);
    }

    @GetMapping("/getAllStudentCourses/{studentId}")
    public List<StudentCoursesDto> getAllStudentCourses(@PathVariable Long studentId) {
        return service.getAllStudentCourses(studentId);
    }

    @DeleteMapping("/deleteStudent/{studentId}")
    public void deleteStudentById(@PathVariable Long studentId) {
        service.deleteStudentById(studentId);
    }

    @PostMapping("addSemester/{studentId}/{semesterId}")
    public void addSemester(@PathVariable Long studentId, @PathVariable Long semesterId) {
        service.addSemester(studentId, semesterId);
    }

    @PostMapping("/addStudentCourse/{studentId}/{courseId}")
    public void addStudentCourses(@PathVariable Long studentId, @PathVariable Long courseId) {
        service.addStudentCourses(studentId, courseId);
    }

    @PostMapping("/submitCourse")
    public SubmittedCourse submitCourse(@RequestBody SubmittedCourseRequestDto requestDto) {
        return service.submitCourse(requestDto);
    }

    @GetMapping("/getUnSubmittedCourses/{studentId}/{deadlineId}")
    public List<StudentCourses> getUnSubmittedCourses(@PathVariable Long studentId, @PathVariable Long deadlineId) {
        return service.getUnSubmittedCourses(studentId, deadlineId);
    }

    @GetMapping("/getSSS/{studentId}")
    public List<Student>  getStudentUsingNativeQuery(@PathVariable Long studentId) {
        return service.getStudentUsingNativeQuery(studentId);
    }

}
