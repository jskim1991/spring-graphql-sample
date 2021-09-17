package io.jay.springgraphqlsample;

import io.jay.springgraphqlsample.service.DepartmentService;
import io.jay.springgraphqlsample.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SpringGraphqlSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringGraphqlSampleApplication.class, args);
	}

}

@Component
@Profile("!test")
@RequiredArgsConstructor
class Initializr implements CommandLineRunner {

	private final DepartmentService departmentService;
	private final EmployeeService employeeService;

	@Override
	public void run(String... args) {
		Department savedDepartment = departmentService.persist(new Department("DevOps"));
		List<Employee> employees = Arrays.asList(new Employee("Sam", savedDepartment),
				new Employee("John", savedDepartment), new Employee("Ayush", savedDepartment));
		for (Employee e : employees) {
			employeeService.persist(e);
			savedDepartment.getEmployees().add(e);
		}
		departmentService.persist(savedDepartment);
	}
}
