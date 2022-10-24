package example.department.controllers;

import example.department.entities.Department;
import example.department.entities.DepartmentRequestDto;
import example.department.entities.DepartmentResponseDto;
import example.department.services.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/department")
public class DepartmentController {

    private DepartmentService service;

    @PostMapping("/addDepartment")
    public Department addDepartment(@RequestBody DepartmentRequestDto requestDto) {
        return service.addDepartment(requestDto);
    }

    @GetMapping("/getAllDepartments/{facultyId}/{page}/{size}")
    public Page<DepartmentResponseDto> getAllDepartments(@PathVariable Long facultyId, @PathVariable int page, @PathVariable int size) {
        return service.getAllDepartments(facultyId, page, size);
    }

    @DeleteMapping("/deleteDepartment/{departmentId}")
    public void deleteDepartmentById(@PathVariable Long departmentId) {
        service.deleteByDepartmentId(departmentId);
    }
}
