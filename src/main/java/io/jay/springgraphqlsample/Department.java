package io.jay.springgraphqlsample;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class Department {

    public Department(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue
    @Column(name = "DEPARTMENT_ID")
    private long id;
    private String name;

    @OneToMany
    @JoinColumn(name = "DEPARTMENT_ID")
    private List<Employee> employees = new ArrayList<>();
}
