package io.jay.springgraphqlsample.controller;

import io.jay.springgraphqlsample.Department;
import io.jay.springgraphqlsample.service.DepartmentService;
import io.jay.springgraphqlsample.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GraphQLController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    @QueryMapping
    public List<EmployeeResponse> employees() {
        return employeeService.getAll();
    }

    @QueryMapping
    public List<DepartmentResponse> departments() {
        return departmentService.getAll();
    }

    @QueryMapping
    public DepartmentResponse departmentById(@Argument long id) {
        return departmentService.getById(id);
    }

    @MutationMapping
    public DepartmentResponse updateDepartmentName(@Argument("id") long id, @Argument("name") String name) {
        Department department = departmentService.find(id);
        department.setName(name);
        Department updated = departmentService.persist(department);
        return new DepartmentResponse(updated);
    }
}
