package com.example.expense_tracker.model.dto;

import com.example.expense_tracker.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private Long id;
    private String username;
    private String email;
    private String fullName;
    private String phoneNumber;
    private String tenantId;

    // Mapping method to convert from User entity to UserDTO
    public static UserDTO fromEntity(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .phoneNumber(user.getPhoneNumber())
                .tenantId(user.getTenantId())
                .build();
    }

    // Mapping method to convert from UserDTO to User entity
    public static User toEntity(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .fullName(userDTO.getFullName())
                .phoneNumber(userDTO.getPhoneNumber())
                .tenantId(userDTO.getTenantId())
                .build();
    }
}
