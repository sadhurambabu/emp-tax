package com.rambabu.controllers;

import com.rambabu.models.Employee;
import com.rambabu.payload.request.EmployeeRequest;
import com.rambabu.payload.response.EmployeeWithTaxDeduction;
import com.rambabu.payload.response.MessageResponse;
import com.rambabu.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping("/employee")
    public ResponseEntity<?> saveEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
        Employee employee = Employee.buildEmployeeFromReq(employeeRequest);
        employeeRepository.save(employee);
        return ResponseEntity.ok(new MessageResponse("Employee saved successfully!"));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<?> getEmployeeDetails(@PathVariable Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get();


        LocalDate startOfFinancialYear = LocalDate.of(LocalDate.now().getYear() - 1, Month.APRIL, 1);
        LocalDate endOfFinancialYear = LocalDate.of(LocalDate.now().getYear(), Month.MARCH, 31);
        LocalDate employeeDOJ = employee.getDateOfJoining().toLocalDate();

        if (employeeDOJ.isAfter(endOfFinancialYear) || employeeDOJ.isEqual(endOfFinancialYear)) {
            // Employee joined in the current or future financial year
            return ResponseEntity.ok("Employee joined in the current or future financial year");
        } else if (employeeDOJ.isBefore(startOfFinancialYear)) {
            // Employee joined before the start of the financial year
            employeeDOJ = startOfFinancialYear;
        }

        long monthsOfService = ChronoUnit.MONTHS.between(employeeDOJ, endOfFinancialYear);
        double totalSalary = employee.getSalaryPerMonth() * monthsOfService;

        // Calculate tax amount based on tax slabs
        double taxAmount = 0;
        if (totalSalary > 250000 && totalSalary <= 500000) {
            taxAmount = (totalSalary - 250000) * 0.05;
        } else if (totalSalary > 500000 && totalSalary <= 1000000) {
            taxAmount = 250000 * 0.05 + (totalSalary - 500000) * 0.1;
        } else if (totalSalary > 1000000) {
            taxAmount = 250000 * 0.05 + 500000 * 0.1 + (totalSalary - 1000000) * 0.2;
        }

        // Calculate cess amount for salary above 2500000
        double cessAmount = (totalSalary > 2500000) ? (totalSalary - 2500000) * 0.02 : 0;

        // Create TaxDeduction object and add it to the list
        EmployeeWithTaxDeduction taxDeduction = new EmployeeWithTaxDeduction(employee.getId().toString(), employee.getFirstName(),
                employee.getLastName(), totalSalary, taxAmount, cessAmount);

        return ResponseEntity.ok(taxDeduction);
    }
}
