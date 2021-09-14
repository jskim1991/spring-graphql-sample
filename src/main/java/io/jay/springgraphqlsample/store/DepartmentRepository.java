package io.jay.springgraphqlsample.store;

import io.jay.springgraphqlsample.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
