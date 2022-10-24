package example.university.controllers;

import example.university.entities.University;
import example.university.services.UniversityService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/university")
public class UniversityController {

    UniversityService service;

    @PostMapping("/addUniversity")
    public University addUniversity(@RequestBody University university) {
        return service.addUniversity(university);
    }

    @GetMapping("/getUniversities/{page}/{size}")
    public Page<University> getUniversities(@PathVariable Integer page, @PathVariable Integer size) {
        return service.getUniversities(page,size);
    }

    @DeleteMapping("/deleteUniversity/{universityId}")
    public void deleteUniversityBuId(@PathVariable Long universityId) {
        service.deleteUniversityById(universityId);
    }
}
