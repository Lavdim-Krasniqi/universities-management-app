package example.department.services;

import example.department.entities.Department;
import example.department.entities.DepartmentRequestDto;
import example.department.entities.DepartmentResponseDto;
import example.department.repositories.DepartmentRepository;
import example.faculty.entities.Faculty;
import example.faculty.services.FacultyService;
import example.program.services.ProgramService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DepartmentService {

    private DepartmentRepository repo;
    private FacultyService facultyService;
    private ProgramService programService;

    public Department addDepartment(DepartmentRequestDto department) {
        Optional<Faculty> faculty = facultyService.getFacultyById(department.facultyId);
        if (faculty.isPresent()) {
            return repo.save(new Department(department.id, department.name, department.createdDate,
                    department.lastModifiedDate, faculty.get(), null));
        }
        throw new RuntimeException("Faculty does not exist");
    }

    public Page<DepartmentResponseDto> getAllDepartments(Long facultyId, int page, int size) {
        return repo.getAllDepartments(facultyId, PageRequest.of(page, size));
    }

    public Optional<Department> getDepartmentById(Long departmentId) {
        return repo.findById(departmentId);
    }

    public void deleteByDepartmentId(Long departmentId) {
        programService.deleteByDepartmentId(departmentId);
        repo.deleteById(departmentId);
    }

    public void deleteDepartmentByFacultyId(Long facultyId) {
        programService.deleteByDepartmentsIds(repo.getDepartmentsOfGivenFaculty(facultyId));
        repo.deleteDepartmentByFacultyId(facultyId);
    }

    public void deleteDepartmentsByFacultiesIds(List<Long> facultiesIds) {
        programService.deleteByDepartmentsIds(repo.getDepartmentsIdsOfEachFaculty(facultiesIds));
        repo.deleteDepartmentsByFacultiesIds(facultiesIds);
    }
}
