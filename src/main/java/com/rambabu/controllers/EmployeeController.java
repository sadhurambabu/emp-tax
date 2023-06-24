package com.rambabu.controllers;

import com.rambabu.models.Employee;
import com.rambabu.payload.request.EmployeeRequest;
import com.rambabu.payload.response.MessageResponse;
import com.rambabu.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

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
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        return ResponseEntity.ok(employee.get());
    }
}
