package com.springcache.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcache.entity.Employee;
import com.springcache.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody(required = false) Employee employee) {
        if (employee == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(employeeService.addEmployee(employee));
    }
    
    @GetMapping("/without-cache")
    public List<Employee> getEmployeesWithoutCache() {
        return employeeService.getEmployeesWithoutCache();
    }

    // Fetch with cache
    @GetMapping("/with-cache")
    public List<Employee> getEmployeesWithCache() {
        return employeeService.getEmployeesWithCache();
    }

}