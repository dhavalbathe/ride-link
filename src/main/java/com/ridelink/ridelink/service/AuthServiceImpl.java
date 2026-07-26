package com.ridelink.ridelink.service;

import com.ridelink.ridelink.dto.UserRegisterRequestDTO;
import com.ridelink.ridelink.dto.UserRegisterResponseDTO;
import com.ridelink.ridelink.entity.User;
import com.ridelink.ridelink.exception.EmailAlreadyExists;
import com.ridelink.ridelink.exception.PhoneAlreadyExists;
import com.ridelink.ridelink.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserRegisterResponseDTO register(UserRegisterRequestDTO request) {

        if(userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExists("User with this email already exists, try another email.");
        }

        if(userRepository.existsByPhone(request.getPhone())) {
            throw new PhoneAlreadyExists("User with this phone number already exists, try another phone number");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        User newUser = userRepository.save(user);

        return UserRegisterResponseDTO.builder()
                .id(newUser.getId())
                .name(newUser.getName())
                .email(newUser.getEmail())
                .phone(newUser.getPhone())
                .role(newUser.getRole())
                .build();
    }
}
