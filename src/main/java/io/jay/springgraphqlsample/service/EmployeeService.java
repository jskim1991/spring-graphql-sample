package io.jay.springgraphqlsample.service;

import io.jay.springgraphqlsample.Employee;
import io.jay.springgraphqlsample.controller.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponse> getAll();

    Employee persist(Employee employee);
}
