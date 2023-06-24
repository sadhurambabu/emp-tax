package com.rambabu.controllers;

import com.rambabu.models.Employee;
import com.rambabu.payload.request.EmployeeRequest;
import com.rambabu.payload.response.MessageResponse;
import com.rambabu.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping("/employee")
    public ResponseEntity<?> saveEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        employee.setName(employeeRequest.getName());
        employeeRepository.save(employee);

        return ResponseEntity.ok(new MessageResponse("Employee saved successfully!"));
    }

    @GetMapping("/employee")
    public ResponseEntity<?> getEmployeeDetails() {
        return ResponseEntity.ok(new MessageResponse("return emp"));
    }



}
