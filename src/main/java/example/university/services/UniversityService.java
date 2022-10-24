package example.university.services;

import example.faculty.services.FacultyService;
import example.university.entities.University;
import example.university.repositories.UniversityRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UniversityService {

    private UniversityRepository repo;
    private FacultyService facultyService;

    public University addUniversity(University university) {
        return repo.save(university);
    }

    public Optional<University> findUniversityById(Long id) {
        return repo.findById(id);
    }

    public Page<University> getUniversities(Integer page, Integer size) {
        return repo.getAllUniversities(PageRequest.of(page, size));
    }

    public void deleteUniversityById(Long universityId) {
        facultyService.deleteFacultyByUniversityId(universityId);
        repo.deleteById(universityId);
    }
}
