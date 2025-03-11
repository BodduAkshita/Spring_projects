package com.example.multi_tenancy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.multi_tenancy.config.TenantService;
import com.example.multi_tenancy.model.Expense;
import com.example.multi_tenancy.repository.ExpenseRepository;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private TenantService tenantService;

    public List<Expense> getExpenses() {
        String tenantId = tenantService.getCurrentTenant(); // Fetch current tenant ID
        return expenseRepository.findByTenantId(tenantId); // Fetch expenses for this tenant
    }

    public Expense createExpense(Expense expense) {
        String tenantId = tenantService.getCurrentTenant(); // Fetch current tenant ID
        expense.setTenantId(tenantId); // Set the tenant ID for this expense
        return expenseRepository.save(expense); // Save expense
    }
}
