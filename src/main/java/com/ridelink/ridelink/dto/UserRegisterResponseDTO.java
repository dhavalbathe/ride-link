package com.ridelink.ridelink.dto;

import com.ridelink.ridelink.enums.Role;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class UserRegisterResponseDTO {

    private Long id;

    private String name;

    private String email;

    private String phone;

    private Role role;
}
