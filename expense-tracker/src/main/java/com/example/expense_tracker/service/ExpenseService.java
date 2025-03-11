package com.example.expense_tracker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.expense_tracker.model.Expense;
import com.example.expense_tracker.model.User;
import com.example.expense_tracker.repository.ExpenseRepository;
import com.example.expense_tracker.repository.UserRepository;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public ExpenseService(ExpenseRepository expenseRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    // Add new expense
    public Expense addExpense(Long userId, Expense expense) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        expense.setUser(user); // Assign user to expense
        return expenseRepository.save(expense);
    }

    // Get all expenses for a user
    public List<Expense> getAllExpenses(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return expenseRepository.findByUser(user);
    }

    // Edit an existing expense
    public Expense editExpense(Long userId, Long expenseId, Expense updatedExpense) {
        // Fetch user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Fetch expense by ID and check ownership
        Expense existingExpense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        if (!existingExpense.getUser().equals(user)) {
            throw new RuntimeException("Expense does not belong to the given user");
        }

        // Update the fields (you can update specific fields as required)
        existingExpense.setName(updatedExpense.getName());
        existingExpense.setAmount(updatedExpense.getAmount());
        existingExpense.setCategory(updatedExpense.getCategory());

        // Save the updated expense
        return expenseRepository.save(existingExpense);
    }
}
