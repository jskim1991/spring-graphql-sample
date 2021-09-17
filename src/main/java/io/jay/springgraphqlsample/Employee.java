package io.jay.springgraphqlsample;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
public class Employee {

    public Employee(String name, Department department) {
        this.name = name;
        this.department = department;
    }

    @Id
    @GeneratedValue
    private long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID", insertable = false, updatable = false)
    private Department department;
}
