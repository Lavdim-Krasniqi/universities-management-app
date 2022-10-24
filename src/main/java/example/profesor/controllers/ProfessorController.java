package example.profesor.controllers;

import example.profesor.entities.*;
import example.profesor.entities.*;
import example.profesor.services.ProfessorService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/professor")
public class ProfessorController {

    private ProfessorService service;

    @PostMapping("/addProfessor")
    public Professor addProfessor(@RequestBody ProfessorRequestDto requestDto) {
        return service.addProfessor(requestDto);
    }

    @PostMapping("/addProgram/{professorId}/{programId}")
    public void addProfessorProgramRelation(@PathVariable Long professorId, @PathVariable Long programId) {
        service.addProfessorProgramRelation(programId, professorId);
    }

    @GetMapping("/getAllProfessors/{page}/{size}")
    public Page<ProfessorResponseDto> getAllProfessors(@PathVariable Integer page, @PathVariable Integer size) {
        return service.getAllProfessors(page, size);
    }

    @GetMapping("/getAllProfessorsOfGivenProgram/{programId}/{page}/{size}")
    public Page<ProfessorResponseDto> getProfessorsOfGivenProgram(@PathVariable Long programId, @PathVariable Integer page, @PathVariable Integer size) {
        return service.getProfessorsOfGivenProgram(programId, page, size);
    }


    @GetMapping("/getProfessorById/{professorId}")
    public List<ProfessorProgramResponseDto> getProfessorById(@PathVariable Long professorId) {
        return service.getProfessorById(professorId);
    }

    //this method should be deleted
    @GetMapping("/getInfoForProfessor/{professorId}")
    public List<ProfessorProgramSemesterDto> getInfoForProfessor(@PathVariable Long professorId) {
        return service.getInfoForProfessor(professorId);
    }

    @DeleteMapping("/deleteProfessorById/{professorId}")
    public void deleteProfessorById(@PathVariable Long professorId) {
        service.deleteProfessorById(professorId);
    }
}
