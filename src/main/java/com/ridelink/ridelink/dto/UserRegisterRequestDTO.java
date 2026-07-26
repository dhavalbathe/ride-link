package com.ridelink.ridelink.dto;

import com.ridelink.ridelink.enums.Role;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequestDTO {
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email address")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Enter a valid 10-digit phone number"
    )
    private String phone;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    private String password;

    @NotNull(message = "Role is required")
    private Role role;
}
