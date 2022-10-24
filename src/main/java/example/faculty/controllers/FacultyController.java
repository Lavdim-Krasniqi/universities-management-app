package example.faculty.controllers;

import example.faculty.entities.Faculty;
import example.faculty.entities.FacultyDto;
import example.faculty.services.FacultyService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/faculty")
public class FacultyController {

    private FacultyService service;

    @PostMapping("/addFaculty")
    public Faculty addFaculty(@RequestBody FacultyDto faculty) {
        return service.addFaculty(faculty);
    }

    @GetMapping("/getAllFaculties/{universityId}/{page}/{size}")
    public Page<FacultyDto> getAllFaculties(@PathVariable Long universityId, @PathVariable int page, @PathVariable int size) {
        return service.getAllFaculties(universityId, page, size);
    }

    @DeleteMapping("/deleteFaculty/{facultyId}")
    public void deleteByFacultyId(@PathVariable Long facultyId) {
        service.deleteByFacultyId(facultyId);
    }

}
