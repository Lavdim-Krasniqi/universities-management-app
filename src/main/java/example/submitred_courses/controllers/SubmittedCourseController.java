package example.submitred_courses.controllers;

import example.submitred_courses.entities.GradeRequestDto;
import example.submitred_courses.entities.SubmittedCourseResponseDto;
import example.submitred_courses.services.SubmittedCourseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/submittedCourse/")
public class SubmittedCourseController {

    private SubmittedCourseService service;

    @PostMapping("addGrade")
    public void addStudentGrade(@RequestBody GradeRequestDto requestDto) {
        service.addStudentGrade(requestDto);
    }

    @GetMapping("getSubmittedCourseByDeadlineId/{studentId}/{deadlineId}")
    public List<SubmittedCourseResponseDto> getSubmittedCourseByDeadlineId(@PathVariable Long studentId, @PathVariable Long deadlineId) {
        return service.getSubmittedCourseByDeadlineId(studentId, deadlineId);
    }

    @GetMapping("refuseGradeOfGivenCourse/{studentId}/{deadlineId}/{courseId}")
    public void refuseGradeOfGivenCourse(@PathVariable Long studentId, @PathVariable Long deadlineId, @PathVariable Long courseId) {
        service.refuseGradeOfGivenCourse(studentId, deadlineId, courseId);
    }
}
