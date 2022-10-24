package example.faculty.services;

import example.department.services.DepartmentService;
import example.faculty.entities.Faculty;
import example.faculty.entities.FacultyDto;
import example.faculty.repositories.FacultyRepository;
import example.university.entities.University;
import example.university.services.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FacultyService {

    @Autowired
    FacultyRepository repo;
    @Lazy
    @Autowired
    UniversityService universityService;

    @Autowired
    @Lazy
    DepartmentService departmentService;

    public Faculty addFaculty(FacultyDto facultyDto) {
        Optional<University> university = universityService.findUniversityById(facultyDto.getUniversityId());
        if (university.isPresent()) {
            return repo.save(new Faculty(facultyDto.getId(), facultyDto.getName(), facultyDto.getLocation(), facultyDto.getCreatedDate(),
                    facultyDto.getLastModifiedDate(), university.get(), null));
        }
        throw new RuntimeException("University does not exists");
    }

    public Page<FacultyDto> getAllFaculties(Long universityId, int page, int size) {
        return repo.getAllFaculties(universityId, PageRequest.of(page, size));
    }

    public Optional<Faculty> getFacultyById(Long facultyId) {
        return repo.findById(facultyId);
    }

    public void deleteByFacultyId(Long facultyId) {
        departmentService.deleteDepartmentByFacultyId(facultyId);
        repo.deleteById(facultyId);
    }

    public void deleteFacultyByUniversityId(Long universityId) {
        departmentService.deleteDepartmentsByFacultiesIds(
                repo.getFacultiesOfGivenUniversity(universityId));

        repo.deleteFacultyByUniversityId(universityId);
    }

}
