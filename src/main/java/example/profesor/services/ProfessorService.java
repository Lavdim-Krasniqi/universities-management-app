package example.profesor.services;

import example.course.services.CourseService;
import example.profesor.entities.*;
import example.profesor.entities.*;
import example.profesor.repositories.ProfessorRepository;
import example.program.entities.Program;
import example.program.services.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository repo;
    @Autowired
    private ProgramService programService;

    @Autowired
    @Lazy
    private CourseService courseService;

    public Professor addProfessor(ProfessorRequestDto requestDto) {
        return repo.save(new Professor(null, requestDto.name, requestDto.surname, requestDto.personalNumber, requestDto.email,
                requestDto.gender, requestDto.birthday, null, null, null, null));
    }

    public void addProfessorProgramRelation(Long programId, Long professorId) {
        Optional<Professor> professor = repo.findById(professorId);
        Optional<Program> program = programService.getProgramById(programId);
        if (professor.isPresent()) {
            if (program.isPresent()) {
                repo.addProfessorProgramRelation(programId, professorId);
            }
            throw new RuntimeException("Professor does not exist");
        }
        throw new RuntimeException("Program does not exist");
    }

    public Page<ProfessorResponseDto> getAllProfessors(Integer page, Integer size) {
        return repo.getAllProfessors(PageRequest.of(page, size));
    }

    public Page<ProfessorResponseDto> getProfessorsOfGivenProgram(Long programId, Integer page, Integer size) {
        return repo.getAllProfessorsOfGivenProgram(programId, PageRequest.of(page, size));
    }

    public List<ProfessorProgramResponseDto> getProfessorById(Long professorId) {
        return repo.getProfessorById(professorId);
    }

    public boolean checkIfProfessorIsPartOfGivenProgram(Long professorId, Long programId) {
        return repo.checkIfProfessorIsPartOfGivenProgram(professorId, programId);
    }


    public List<ProfessorProgramSemesterDto> getInfoForProfessor(Long professorId) {
        return repo.getInfoForProfessor(professorId);
    }

    public Optional<Professor> getProfessor(Long professorId) {
        return repo.findById(professorId);
    }

    public void deleteProgramProfessorRelation(Long programId) {
        repo.deleteProgramProfessorRelation(programId);
    }

    public void deleteProgramProfessorRelations(List<Long> programsIds) {
        repo.deleteProgramProfessorRelations(programsIds);
    }

    public void deleteProfessorById(Long professorId) {
        deleteProgramProfessorRelationByProfessorId(professorId);
        courseService.deleteByProfessorId(professorId);
        repo.deleteById(professorId);
    }

    public void deleteProgramProfessorRelationByProfessorId(Long professorId) {
        repo.deleteProgramProfessorRelationByProfessorId(professorId);
    }

    public void deleteProgramProfessorRelationsByProfessorId(List<Long> professorId) {
        repo.deleteProgramProfessorRelationsByProfessorId(professorId);
    }
}
