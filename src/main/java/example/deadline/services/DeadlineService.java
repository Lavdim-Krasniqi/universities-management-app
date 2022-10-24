package example.deadline.services;

import example.deadline.entities.Deadline;
import example.deadline.entities.DeadlineRequestDto;
import example.deadline.repositories.DeadlineRepository;
import example.program.entities.Program;
import example.program.services.ProgramService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DeadlineService {

    private DeadlineRepository repo;
    private ProgramService programService;

    public Deadline addDeadline(DeadlineRequestDto requestDto) {
        Optional<Program> program = programService.getProgramById(requestDto.programId);
        if (program.isPresent()) {
            if (requestDto.getEndDate().isAfter(Instant.now()) && requestDto.getStartDate().isBefore(requestDto.getEndDate())) {
                return repo.save(new Deadline(null, requestDto.name, requestDto.startDate, requestDto.endDate,
                        requestDto.level, requestDto.type, null, null, program.get(), null));
            }
            throw new RuntimeException("Date is given bad");
        }
        throw new RuntimeException("Program does not exist");
    }

    public Optional<Deadline> getDeadlineById(Long deadlineId) {
        return repo.findById(deadlineId);
    }

    public boolean checkIfDeadlineIsOpen(Long programId, Instant time) {
        return repo.checkIfDeadlineIsOpen(programId, time);
    }

    public Deadline getOpenDeadline(Long programId) {
        return repo.getOpenDeadline(programId, Instant.now());
    }

    public boolean checkIfDeadlineIsExpired(Long deadlineId) {
        return repo.checkIfDeadlineIsExpired(deadlineId, Instant.now());
    }
}
