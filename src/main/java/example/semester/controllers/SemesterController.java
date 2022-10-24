package example.semester.controllers;

import example.semester.entities.Semester;
import example.semester.entities.SemesterRequestDto;
import example.semester.entities.SemesterResponseDto;
import example.semester.services.SemesterService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/semester")
public class SemesterController {

    private SemesterService service;

    @PostMapping("/addSemester")
    public Semester addSemester(@RequestBody SemesterRequestDto requestDto) {
        return service.addSemester(requestDto);
    }

    @GetMapping("/getSemester/{programId}/{page}/{size}")
    public Page<SemesterResponseDto> getPages(@PathVariable Long programId, @PathVariable Integer page, @PathVariable Integer size) {
        return service.getSemesters(programId, page, size);
    }

    @DeleteMapping("/deleteSemester/{semesterId}")
    public void deleteSemesterById(@PathVariable Long semesterId) {
        service.deleteSemesterById(semesterId);
    }
}
