package com.example.multi_tenancy2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.multi_tenancy2.model.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByTenantId(String tenantId);  // Fetch expenses based on tenant ID
}
