package example.deadline.controllers;

import example.deadline.entities.Deadline;
import example.deadline.entities.DeadlineRequestDto;
import example.deadline.services.DeadlineService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/deadline/")
public class DeadlineController {

    private DeadlineService service;

    @PostMapping("addDeadline")
    public Deadline addDeadline(@RequestBody DeadlineRequestDto requestDto) {
        return service.addDeadline(requestDto);
    }

    @GetMapping("getOpenDeadline/{programId}")
    public Deadline getOpenDeadline(@PathVariable Long programId) {
        return service.getOpenDeadline(programId);
    }
}
