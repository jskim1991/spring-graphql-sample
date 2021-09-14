package io.jay.springgraphqlsample;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Department {
    @Id
    @GeneratedValue
    @Column(name = "DEPARTMENT_ID")
    private long id;
    private String name;

    @OneToMany
    @JoinColumn(name = "DEPARTMENT_ID")
    private List<Employee> employees = new ArrayList<>();
}
