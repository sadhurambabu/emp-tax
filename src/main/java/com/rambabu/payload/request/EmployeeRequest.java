package com.rambabu.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

public class EmployeeRequest {
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

    @NotNull
    @Size(max = 25)
    private List<String> phoneNumbers;

    @NotNull
    private Date dateOfJoining;

    @NotNull
    private Float salaryPerMonth;

    public EmployeeRequest() {
    }

    public EmployeeRequest(String firstName, String lastName, String email, List<String> phoneNumbers, Date dateOfJoining, Float salaryPerMonth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumbers = phoneNumbers;
        this.dateOfJoining = dateOfJoining;
        this.salaryPerMonth = salaryPerMonth;
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

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
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

}
