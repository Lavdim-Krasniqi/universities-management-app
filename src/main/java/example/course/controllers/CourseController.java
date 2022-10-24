package example.course.controllers;

import example.course.entities.Course;
import example.course.entities.CourseRequestDto;
import example.course.entities.CourseResponseDto;
import example.course.services.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/course")
public class CourseController {

    private CourseService service;

    @PostMapping("/addCourse")
    public Course addCourse(@RequestBody CourseRequestDto requestDto) {
        return service.addCourse(requestDto);
    }

    @GetMapping("/getAllCoursesOfGivenProgram/{programId}/{page}/{size}")
    public Page<CourseResponseDto> getAllCoursesOfGivenProgram(@PathVariable Long programId, @PathVariable Integer page, @PathVariable Integer size) {
        return service.getAllCoursesOfGivenProgram(programId, page, size);
    }

    @GetMapping("/getCoursesByProgramAndProfessor/{programId}/{professorId}/{page}/{size}")
    public Page<CourseResponseDto> getCoursesByProgramAndProfessor(@PathVariable Long programId, @PathVariable Long professorId, @PathVariable Integer page, @PathVariable Integer size) {
        return service.getCoursesByProgramAndProfessor(programId, professorId, page, size);
    }

    @GetMapping("/getCoursesByProfessorId/{professorId}/{page}/{size}")
    public Page<CourseResponseDto> getCoursesByProfessorId(@PathVariable Long professorId, @PathVariable Integer page, @PathVariable Integer size) {
        return service.getCoursesByProfessorId(professorId, page, size);
    }

    @GetMapping("/getCoursesByProgramAndSemester/{programId}/{semesterNUmber}/{page}/{size}")
    public Page<CourseResponseDto> getCoursesByProgramAndSemester(@PathVariable Long programId, @PathVariable Integer semesterNUmber, @PathVariable Integer page, @PathVariable Integer size) {
        return service.getCoursesByProgramAndSemester(programId, semesterNUmber, page, size);
    }

    @DeleteMapping("/deleteByProgramId/{programId}")
    public void deleteByProgramId(@PathVariable Long programId) {
        service.deleteByProgramId(programId);
    }

    @DeleteMapping("/deleteByProfessorId/{professorId}")
    public void deleteByProfessorId(@PathVariable Long professorId) {
        service.deleteByProfessorId(professorId);
    }

    @DeleteMapping("/deleteCourseById/{courseId}")
    public void deleteCourseById(@PathVariable Long courseId) {
        service.deleteCourseById(courseId);
    }

}
