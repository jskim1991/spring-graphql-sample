package io.jay.springgraphqlsample.controller;

import io.jay.springgraphqlsample.Department;
import io.jay.springgraphqlsample.Employee;
import io.jay.springgraphqlsample.service.DepartmentService;
import io.jay.springgraphqlsample.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.WebGraphQlTester;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.mockito.Mockito.when;

@SpringBootTest
public class GraphQLControllerTests {

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private EmployeeService mockEmployeeService;
    
    @MockBean
    private DepartmentService mockDepartmentService;

    private WebGraphQlTester graphQlTester;

    public static String fileToQuery(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get("src/test/resources/graphql-queries/" + fileName)));
    }

    @BeforeEach
    void setUp() {
        WebTestClient client = MockMvcWebTestClient.bindToApplicationContext(context)
                        .configureClient()
                        .baseUrl("/graphql")
                        .build();
        graphQlTester = WebGraphQlTester.builder(client).build();
    }

    @Test
    void test_query_employees_returnsEmployeeList() throws IOException {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("John");
        Department department = new Department();
        department.setId(999L);
        employee.setDepartment(department);

        when(mockEmployeeService.getAll())
                .thenReturn(Arrays.asList(new EmployeeResponse(employee)));


        graphQlTester.query(fileToQuery("employees.graphql"))
                .execute()
                .path("employees[0].id").entity(Long.class).isEqualTo(1L)
                .path("employees[0].name").entity(String.class).isEqualTo("John")
                .path("employees[0].departmentId").entity(Long.class).isEqualTo(999L)
        ;
    }

    @Test
    void test_mutation_updateDepartmentName_updatesName() throws IOException {
        Department department = new Department();
        department.setId(1L);
        department.setName("Human Resources");

        when(mockDepartmentService.find(1L))
                .thenReturn(department);

        Department departmentWithUpdatedName = new Department();
        departmentWithUpdatedName.setId(1L);
        departmentWithUpdatedName.setName("DevOps");
        when(mockDepartmentService.persist(department))
                .thenReturn(departmentWithUpdatedName);


        graphQlTester.query(fileToQuery("updateDepartmentName.graphql"))
                .execute()
                .path("updateDepartmentName.id").entity(Long.class).isEqualTo(1L)
                .path("updateDepartmentName.name").entity(String.class).isEqualTo("DevOps")
        ;
    }
}
