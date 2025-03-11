//package com.example.expense_tracker.security;
//
//import com.example.expense_tracker.service.CustomUserDetails;
//import com.example.expense_tracker.service.UserService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    private final UserService userService;
//
//    public SecurityConfig(UserService userService) {
//        this.userService = userService;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//            .authorizeRequests()
//                .requestMatchers("/auth/register", "/auth/login").permitAll()  // Allow registration and login
//                .anyRequest().authenticated()  // All other requests need authentication
//            .and()
//            .formLogin().permitAll()  // Enable form login
//            .and()
//            .logout().permitAll();  // Enable logout
//
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();  // BCrypt for strong password encoding
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return username -> userService.findByUsername(username)
//                .map(CustomUserDetails::new)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//    }
//}
