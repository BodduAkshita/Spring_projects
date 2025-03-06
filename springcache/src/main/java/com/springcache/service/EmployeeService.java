package com.springcache.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.springcache.entity.Employee;
import com.springcache.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	 @Autowired
	 private EmployeeRepository employeeRepository;
	 
	public Employee addEmployee(Employee employee) {
		if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new RuntimeException("Employee with email already exists!");
        }
        return employeeRepository.save(employee);
	}
	// Fetch without caching (always from DB)
    public List<Employee> getEmployeesWithoutCache() {
        long start = System.currentTimeMillis();
        List<Employee> employees = employeeRepository.findAll();
        long end = System.currentTimeMillis();
        System.out.println("Without Cache: Time taken = " + (end - start) + "ms");
        return employees;
    }

    // Fetch with caching
    @Cacheable(value = "employees")
    public List<Employee> getEmployeesWithCache() {
        System.out.println("Fetching from DB (Cache Miss)!");
        long start = System.currentTimeMillis();
        List<Employee> employees = employeeRepository.findAll();
        long end = System.currentTimeMillis();
        System.out.println("With Cache: Time taken = " + (end - start) + "ms");
        return employees;
    }

}
