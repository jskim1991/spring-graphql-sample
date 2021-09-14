package io.jay.springgraphqlsample.service;

import io.jay.springgraphqlsample.Department;
import io.jay.springgraphqlsample.controller.DepartmentResponse;

import java.util.List;

public interface DepartmentService {
    List<DepartmentResponse> getAll();
    DepartmentResponse getById(Long id);

    Department find(Long id);

    Department persist(Department department);
}
