package com.example.crud.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*Unable to find column with logical name:
department_id in org.hibernate.mapping.Table(department)
and its related supertables and secondary tables */

@Entity
/* @Table(name="tbldepartment", catalog = "test") */
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
/* @Column(name="department_id") */
    private long id;
    private String name;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   public List<Employee> employees;

    public Department() {
        employees= new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
