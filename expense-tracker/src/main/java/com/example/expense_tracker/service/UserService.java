package com.example.expense_tracker.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.expense_tracker.config.TenantContext;
import com.example.expense_tracker.model.RegisterRequest;
import com.example.expense_tracker.model.User;
import com.example.expense_tracker.model.dto.UserDTO;
import com.example.expense_tracker.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Register user with tenant ID
    public UserDTO registerUser(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword()); // Store password in plain text (not secure)
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setTenantId(TenantContext.getTenantId());  // Set a default tenant ID or you can modify this

        User savedUser = userRepository.save(user); // Save the user to the database
        return UserDTO.fromEntity(savedUser); // Convert the saved user to UserDTO and return it
    }


    // Get user by ID
    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return UserDTO.fromEntity(user);
    }

    // Get all users for the current tenant
    public List<UserDTO> getAllUsers() {
        String tenantId = TenantContext.getTenantId();  // Get tenant ID from context
        List<User> users = userRepository.findByTenantId(tenantId);
        return users.stream().map(UserDTO::fromEntity).collect(Collectors.toList());
    }
    
//    public List<UserDTO> getAllUsers() {
//        String tenantId = TenantContext.getTenantId();  // Get tenant ID from context
//        List<User> users = userRepository.findByTenantId(tenantId); // Fetch users from DB
//        
//        // Convert List<User> to List<UserDTO> using a traditional loop
//        List<UserDTO> userDTOs = new ArrayList<>();
//        for (User user : users) {
//            userDTOs.add(UserDTO.fromEntity(user));
//        }
//
//        return userDTOs; // Return the final List<UserDTO>
//    }

    
    

    // Update user
    public UserDTO updateUser(Long id, UserDTO updatedUserDTO) {
        Optional<User> optionalUser = userRepository.findById(id);
        
        if (!optionalUser.isPresent()) {
            return null; // Or return an empty UserDTO
        }

        User user = optionalUser.get();
        user.setUsername(updatedUserDTO.getUsername());
        user.setEmail(updatedUserDTO.getEmail());
        user.setFullName(updatedUserDTO.getFullName());
        user.setPhoneNumber(updatedUserDTO.getPhoneNumber());

        userRepository.save(user);
        return UserDTO.fromEntity(user);
    }


    // Delete user
    public void deleteUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get()); // Delete only if user exists
        }
        // If user does not exist, do nothing (silently fail)
    }

    
//    // Find user by username (without security concerns)
//    public Optional<User> findByUsername(String username) {
//        return userRepository.findByUsername(username);
//    }
    
    public boolean authenticate(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if the password matches the one stored in the database (plain text comparison)
        return user.getPassword().equals(password);
    }
}
