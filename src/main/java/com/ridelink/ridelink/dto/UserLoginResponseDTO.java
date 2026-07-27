package com.ridelink.ridelink.dto;

import com.ridelink.ridelink.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginResponseDTO {
    private Long id;

    private String name;

    private String email;

    private Role role;

    private String token;
}
