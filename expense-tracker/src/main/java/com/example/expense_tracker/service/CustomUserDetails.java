//package com.example.expense_tracker.service;
//
//import com.example.expense_tracker.model.User;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//
//public class CustomUserDetails implements UserDetails {
//
//    private final User user;
//
//    public CustomUserDetails(User user) {
//        this.user = user;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        // You can return a list of roles or permissions
//        return null;  // Example: return List.of(new SimpleGrantedAuthority("ROLE_USER"));
//    }
//
//    @Override
//    public String getPassword() {
//        return user.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return user.getUsername();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;  // You can implement this for account expiration logic
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;  // Implement this for account lock logic
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;  // Implement this for credentials expiration
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;  // Implement this to check if the account is enabled
//    }
//
//    public User getUser() {
//        return user;
//    }
//}
