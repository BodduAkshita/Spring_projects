package com.example.expense_tracker.model;

import jakarta.persistence.*;
import lombok.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder  // Add the Builder annotation here
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double amount;
    private String category;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // Expense is linked to a user

    @CreationTimestamp
    private LocalDateTime createdAt;  // Auto-set when an expense is created

    @UpdateTimestamp
    private LocalDateTime updatedAt;  // Auto-set when an expense is updated
}
