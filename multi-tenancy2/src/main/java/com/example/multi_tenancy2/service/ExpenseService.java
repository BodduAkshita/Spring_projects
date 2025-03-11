package com.example.multi_tenancy2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.multi_tenancy2.model.Expense;
import com.example.multi_tenancy2.repository.ExpenseRepository;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    // Fetch expenses for the current tenant
    public List<Expense> getExpenses() {
        return expenseRepository.findAll();  // The repository will use the tenant-specific data source
    }

    // Create an expense for the current tenant
    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);  // The repository will use the tenant-specific data source
    }
}
