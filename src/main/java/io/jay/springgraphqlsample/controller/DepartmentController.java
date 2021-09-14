package io.jay.springgraphqlsample.controller;

import io.jay.springgraphqlsample.Department;
import io.jay.springgraphqlsample.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/all")
    public List<DepartmentResponse> getAllDepartments() {
        return departmentService.getAll();
    }

    @PostMapping("/add")
    public long addDepartment(@RequestParam String name) {
        Department department = new Department();
        department.setName(name);
        return departmentService.persist(department).getId();
    }
}
