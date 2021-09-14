package io.jay.springgraphqlsample.service;

import io.jay.springgraphqlsample.Department;
import io.jay.springgraphqlsample.controller.DepartmentResponse;
import io.jay.springgraphqlsample.store.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultDepartmentService implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public List<DepartmentResponse> getAll() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream()
                .map(DepartmentResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentResponse getById(Long id) {
        Department department = departmentRepository.findById(id).get();
        return new DepartmentResponse(department);
    }

    @Override
    public Department find(Long id) {
        return departmentRepository.getById(id);
    }

    @Override
    public Department persist(Department department) {
        return departmentRepository.save(department);
    }
}
