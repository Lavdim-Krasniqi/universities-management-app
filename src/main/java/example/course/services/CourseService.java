package example.course.services;

import example.course.entities.Course;
import example.course.entities.CourseRequestDto;
import example.course.entities.CourseResponseDto;
import example.course.repositories.CourseRepository;
import example.profesor.entities.Professor;
import example.profesor.services.ProfessorService;
import example.program.entities.Program;
import example.program.services.ProgramService;
import example.semester.services.SemesterService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class CourseService {

    private CourseRepository repo;
    private SemesterService semesterService;
    private ProfessorService professorService;
    private ProgramService programService;

    public Course addCourse(CourseRequestDto requestDto) {
        Optional<Professor> professor = professorService.getProfessor(requestDto.professorId);
        Optional<Program> program = programService.getProgramById(requestDto.programId);

        if (professor.isPresent()) {
            if (professorService.checkIfProfessorIsPartOfGivenProgram(requestDto.professorId, requestDto.programId) && program.isPresent()) {
                if (requestDto.getSemesterNumber() <= program.get().getNumberOfSemester()) {
                    return repo.save(new Course(null, requestDto.name, requestDto.credits, requestDto.type, null, null,
                            program.get(), requestDto.semesterNumber, null, professor.get(), null));
                }
                throw new RuntimeException("Number of semester for given course is greater than number of semesters that program has");
            }
            throw new RuntimeException("Professor does not belong to given program");
        }
        throw new RuntimeException("Professor does not exist");
    }

    public Optional<Course> getCourseById(Long courseId) {
        return repo.findById(courseId);
    }

    public Page<CourseResponseDto> getAllCoursesOfGivenProgram(Long programId, Integer page, Integer size) {
        return repo.getAllCoursesOfGivenProgram(programId, PageRequest.of(page, size));
    }

    public Page<CourseResponseDto> getCoursesByProgramAndProfessor(Long programId, Long professorId, Integer page, Integer size) {
        return repo.getCoursesByProgramAndProfessor(programId, professorId, PageRequest.of(page, size));
    }

    public Page<CourseResponseDto> getCoursesByProfessorId(Long professorId, Integer page, Integer size) {
        return repo.getCoursesByProfessorId(professorId, PageRequest.of(page, size));
    }

    public Page<CourseResponseDto> getCoursesByProgramAndSemester(Long programId, Integer semesterNumber, Integer page, Integer size) {
        return repo.getCoursesByProgramAndSemester(programId, semesterNumber, PageRequest.of(page, size));
    }

    public void deleteByProgramId(Long programId) {
        repo.deleteByProgramId(programId);
    }

    public void deleteByProfessorId(Long professorId) {
        repo.deleteByProfessorId(professorId);
    }

    public void deleteCourseById(Long courseId) {
        repo.deleteById(courseId);
    }

    public void deleteByProgramsIds(List<Long> programsIds) {
        repo.deleteByProgramsIds(programsIds);
    }

    public void deleteByProfessorsIds(List<Long> professorsIds) {
        repo.deleteByProfessorsIds(professorsIds);
    }
}
