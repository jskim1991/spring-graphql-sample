package io.jay.springgraphqlsample.service;

import io.jay.springgraphqlsample.Employee;
import io.jay.springgraphqlsample.controller.EmployeeResponse;
import io.jay.springgraphqlsample.store.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultEmployeeService implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeResponse> getAll() {
        List<Employee> employees = employeeRepository.findAll();
        return EmployeeResponse.of(employees);
    }

    @Override
    public Employee persist(Employee employee) {
        return employeeRepository.save(employee);
    }
}
