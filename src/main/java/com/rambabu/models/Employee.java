package com.rambabu.models;


import com.rambabu.payload.request.EmployeeRequest;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "employee",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"firstName"}),
        })
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    @Size(max = 25)
    private String firstName;

    @NotBlank
    @Size(max = 25)
    private String lastName;

    @NotBlank
    @Size(max = 25)
    private String email;

    @NotBlank
    @Size(max = 25)
    private String phoneNumbers;

    @NotNull
    private Date dateOfJoining;

    @NotNull
    private Float salaryPerMonth;


    public Employee() {
    }

    public Employee(String firstName, String lastName, String email, String phoneNumbers, Date dateOfJoining, Float salaryPerMonth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumbers = phoneNumbers;
        this.dateOfJoining = dateOfJoining;
        this.salaryPerMonth = salaryPerMonth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(String phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public Date getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public Float getSalaryPerMonth() {
        return salaryPerMonth;
    }

    public void setSalaryPerMonth(Float salaryPerMonth) {
        this.salaryPerMonth = salaryPerMonth;
    }

    public static Employee buildEmployeeFromReq(EmployeeRequest req) {
        Employee employee = new Employee(req.getFirstName(), req.getLastName(), req.getEmail(), String.join(",", req.getPhoneNumbers()), new Date(req.getDateOfJoining().getTime()), req.getSalaryPerMonth());
        return employee;
    }

}
