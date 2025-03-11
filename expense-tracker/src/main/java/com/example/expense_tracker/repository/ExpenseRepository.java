package com.example.expense_tracker.repository;

import com.example.expense_tracker.model.Expense;
import com.example.expense_tracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    // Find all expenses for a specific user
    List<Expense> findByUser(User user);
}
