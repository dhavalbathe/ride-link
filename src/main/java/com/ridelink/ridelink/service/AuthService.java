package com.ridelink.ridelink.service;

import com.ridelink.ridelink.dto.UserRegisterRequestDTO;
import com.ridelink.ridelink.dto.UserRegisterResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    public UserRegisterResponseDTO register(UserRegisterRequestDTO request);
}
