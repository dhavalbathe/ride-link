package com.ridelink.ridelink.controller;

import com.ridelink.ridelink.dto.UserRegisterRequestDTO;
import com.ridelink.ridelink.dto.UserRegisterResponseDTO;
import com.ridelink.ridelink.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDTO> registerUser(@RequestBody @Valid UserRegisterRequestDTO requestDTO) {
        UserRegisterResponseDTO response = authService.register(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
