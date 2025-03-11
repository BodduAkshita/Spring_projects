package com.example.expense_tracker.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String username;
    private String password; // Only used here, not in UserDTO
    private String email;
    private String fullName;
    private String phoneNumber;
}
