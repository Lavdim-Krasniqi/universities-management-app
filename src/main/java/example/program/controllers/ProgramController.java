package example.program.controllers;

import example.program.entities.Program;
import example.program.entities.ProgramRequestDto;
import example.program.entities.ProgramResponseDto;
import example.program.services.ProgramService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/program")
public class ProgramController {

    private ProgramService service;

    @PostMapping("/addProgram")
    public Program addProgram(@RequestBody ProgramRequestDto requestDto) {
        return service.addProgram(requestDto);
    }

    @GetMapping("/getPrograms/{departmentId}/{page}/{size}")
    public Page<ProgramResponseDto> getAllPrograms(@PathVariable Long departmentId, @PathVariable Integer page, @PathVariable Integer size) {
        return service.getAllProgramsOfGivenDepartment(departmentId, page, size);
    }

    @DeleteMapping("/deleteProgram/{programId}")
    public void deleteProgramById(@PathVariable Long programId) {
        service.deleteProgramById(programId);
    }
}
