package example.semester.services;

import example.course.services.CourseService;
import example.program.entities.Program;
import example.program.services.ProgramService;
import example.semester.entities.Semester;
import example.semester.entities.SemesterRequestDto;
import example.semester.entities.SemesterResponseDto;
import example.semester.repositories.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class SemesterService {

    @Autowired
    private SemesterRepository repo;
    @Autowired
    private ProgramService programService;
    @Autowired
    @Lazy
    private CourseService courseService;

    public Semester addSemester(SemesterRequestDto requestDto) {
        Optional<Program> program = programService.getProgramById(requestDto.program_id);

        if (program.isPresent()) {
            if (requestDto.endDate.isAfter(Instant.now()) && requestDto.startDate.isBefore(requestDto.endDate)) {
                return repo.save(new Semester(null, requestDto.number, requestDto.level, requestDto.description, requestDto.credits,
                        requestDto.passability, requestDto.startDate, requestDto.endDate, null, null, program.get(), null));
            }
            throw new RuntimeException("Date is given bad");
        }
        throw new RuntimeException("Program does not exist");
    }

    public Page<SemesterResponseDto> getSemesters(Long programId, Integer page, Integer size) {
        return repo.getSemester(programId, PageRequest.of(page, size));
    }

    public Optional<Semester> getSemesterById(Long semesterId) {
        return repo.findById(semesterId);
    }

    public void deleteSemesterByProgramsIds(List<Long> programsIds) {
        //when I delete semester i should delete also courses but this is done at programService
        repo.deleteByProgramsIds(programsIds);
    }

    public void deleteSemesterByProgramId(Long programId) {
        repo.deleteByProgramId(programId);
    }

    public void deleteSemesterById(Long semesterId) {
        repo.deleteById(semesterId);
    }

    public boolean checkIfGivenProgramIdMatchesInGivenSemester(Long semesterId, Long programId) {
        return repo.checkIfGivenProgramIdMatchesInGivenSemester(semesterId, programId);
    }

}
