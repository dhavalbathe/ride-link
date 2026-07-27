package com.ridelink.ridelink.service;

import com.ridelink.ridelink.dto.UserLoginRequestDTO;
import com.ridelink.ridelink.dto.UserLoginResponseDTO;
import com.ridelink.ridelink.dto.UserRegisterRequestDTO;
import com.ridelink.ridelink.dto.UserRegisterResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    public UserRegisterResponseDTO register(UserRegisterRequestDTO request);

    public UserLoginResponseDTO login(UserLoginRequestDTO request);
}
