//package com.example.expense_tracker.service;
//
//import com.example.expense_tracker.service.UserService;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private final UserService userService;
//
//    public CustomUserDetailsService(UserService userService) {
//        this.userService = userService;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) {
//        return userService.findByUsername(username)
//                .map(CustomUserDetails::new)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//    }
//}
