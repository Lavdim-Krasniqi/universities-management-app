package example.program.services;

import example.course.services.CourseService;
import example.department.entities.Department;
import example.department.services.DepartmentService;
import example.profesor.services.ProfessorService;
import example.program.entities.Program;
import example.program.entities.ProgramRequestDto;
import example.program.entities.ProgramResponseDto;
import example.program.repositories.ProgramRepository;
import example.semester.services.SemesterService;
import example.student.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgramService {

    @Autowired
    private ProgramRepository repo;
    @Autowired
    @Lazy
    private DepartmentService departmentService;

    @Autowired
    @Lazy
    private SemesterService semesterService;

    @Autowired
    @Lazy
    private StudentService studentService;

    @Autowired
    @Lazy
    private CourseService courseService;

    @Autowired
    @Lazy
    private ProfessorService professorService;

    public Program addProgram(ProgramRequestDto requestDto) {
        Optional<Department> department = departmentService.getDepartmentById(requestDto.departmentId);
        if (department.isPresent()) {
            return repo.save(new Program(requestDto.id, requestDto.name, requestDto.level, requestDto.numberOfSemester,
                    requestDto.createdDate, requestDto.lastModifiedDate, department.get(), null, null, null, null, null));
        }
        throw new RuntimeException("Department does not exist");
    }

    public Optional<Program> getProgramById(Long programId) {
        return repo.findById(programId);
    }

    public Page<ProgramResponseDto> getAllProgramsOfGivenDepartment(Long departmentId, int page, int size) {
        return repo.getAllPrograms(departmentId, PageRequest.of(page, size));
    }

    public void deleteByDepartmentsIds(List<Long> departmentsIds) {
        List<Long> programsIds = repo.getProgramsIdsByListOfDepartmentsIds(departmentsIds);
        semesterService.deleteSemesterByProgramsIds(programsIds);
        studentService.deleteStudentsOfGivenPrograms(programsIds);
        courseService.deleteByProgramsIds(programsIds);
        professorService.deleteProgramProfessorRelations(programsIds);
        repo.deleteProgramsByGivenDepartmentsIds(departmentsIds);
    }

    public void deleteByDepartmentId(Long departmentId) {
        List<Long> programsIds = repo.getProgramsIdsByDepartmentId(departmentId);
        semesterService.deleteSemesterByProgramsIds(programsIds);
        studentService.deleteStudentsOfGivenPrograms(programsIds);
        courseService.deleteByProgramsIds(programsIds);
        professorService.deleteProgramProfessorRelations(programsIds);
        repo.deleteByDepartmentId(departmentId);
    }

    public void deleteProgramById(Long programId) {
        semesterService.deleteSemesterByProgramId(programId);
        studentService.deleteStudentsOfGivenProgram(programId);
        courseService.deleteByProgramId(programId);
        professorService.deleteProgramProfessorRelation(programId);
        repo.deleteById(programId);
    }
}
