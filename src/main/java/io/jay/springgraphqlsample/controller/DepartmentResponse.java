package io.jay.springgraphqlsample.controller;

import io.jay.springgraphqlsample.Department;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class DepartmentResponse {
    private long id;
    private String name;
    private List<EmployeeResponse> employees;

    public DepartmentResponse(Department department) {
        this.id = department.getId();
        this.name = department.getName();
        this.setEmployees(EmployeeResponse.of(department.getEmployees()));
    }
}
