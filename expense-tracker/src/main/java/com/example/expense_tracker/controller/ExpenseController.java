package com.example.expense_tracker.controller;

import com.example.expense_tracker.model.Expense;
import com.example.expense_tracker.service.ExpenseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    // Endpoint to add a new expense
    @PostMapping("/add/{userId}")
    public Expense addExpense(@PathVariable Long userId, @RequestBody Expense expense) {
        return expenseService.addExpense(userId, expense);
    }

    // Endpoint to get all expenses of a user
    @GetMapping("/user/{userId}")
    public List<Expense> getExpensesByUser(@PathVariable Long userId) {
        return expenseService.getAllExpenses(userId);
    }

    // Endpoint to edit an existing expense
    @PutMapping("/edit/{userId}/{expenseId}")
    public Expense editExpense(@PathVariable Long userId, @PathVariable Long expenseId, @RequestBody Expense updatedExpense) {
        return expenseService.editExpense(userId, expenseId, updatedExpense);
    }
}
