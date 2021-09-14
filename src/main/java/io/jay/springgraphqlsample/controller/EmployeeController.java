package io.jay.springgraphqlsample.controller;

import io.jay.springgraphqlsample.Department;
import io.jay.springgraphqlsample.Employee;
import io.jay.springgraphqlsample.service.DepartmentService;
import io.jay.springgraphqlsample.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    @GetMapping("/all")
    public List<EmployeeResponse> getAllEmployees() {
        return employeeService.getAll();
    }

    @PostMapping("/add")
    @Transactional
    public long addEmployee(@RequestParam Long teamId, @RequestParam String name) {
        Employee employee = new Employee();
        employee.setName(name);

        Employee savedEmployee = employeeService.persist(employee);

        Department department = departmentService.find(teamId);
        department.getEmployees().add(employee);
        departmentService.persist(department);

        return savedEmployee.getId();
    }
}
