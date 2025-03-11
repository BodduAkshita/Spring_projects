package com.example.multi_tenancy2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.multi_tenancy2.model.Expense;
import com.example.multi_tenancy2.service.ExpenseService;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping
    public List<Expense> getExpenses() {
        return expenseService.getExpenses();  // Get expenses for the current tenant
    }

    @PostMapping
    public Expense createExpense(@RequestBody Expense expense, @RequestHeader("X-Tenant-ID") String tenantId) {
        expense.setTenantId(tenantId);  // Ensure tenantId is set for the expense
        return expenseService.createExpense(expense);  // Create an expense for the current tenant
    }
}
