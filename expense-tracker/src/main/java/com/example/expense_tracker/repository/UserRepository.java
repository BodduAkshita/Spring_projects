package com.example.expense_tracker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.expense_tracker.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndTenantId(String username, String tenantId);
    List<User> findByTenantId(String tenantId);
	Optional<User> findByUsername(String username);  
}
