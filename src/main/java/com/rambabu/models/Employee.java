package com.rambabu.models;


import com.rambabu.payload.request.EmployeeRequest;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employee",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name"}),
        })
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    private String name;

    @NotNull
    private Float salary;


    public Employee() {
    }

    public Employee(Long id, String name, Float salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public static Employee buildEmployeeFromReq(EmployeeRequest req) {
        Employee employee = new Employee(req.getId(), req.getName(), req.getSalary());
        return employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }
}
