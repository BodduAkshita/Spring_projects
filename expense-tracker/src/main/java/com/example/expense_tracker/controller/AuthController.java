package com.example.expense_tracker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.expense_tracker.model.LoginRequest;
import com.example.expense_tracker.model.RegisterRequest;
import com.example.expense_tracker.model.dto.UserDTO;
import com.example.expense_tracker.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Register User
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody RegisterRequest request) {
        UserDTO registeredUserDTO = userService.registerUser(request); // This will return a UserDTO now
        return ResponseEntity.ok(registeredUserDTO); // Return the UserDTO
    }

    // Login User
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest) {
        boolean authenticated = userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());

        if (authenticated) {
            return ResponseEntity.ok("Login successful!");
        } else {
            return ResponseEntity.status(401).body("Invalid username or password!");
        }
    }
}
