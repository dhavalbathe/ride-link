package com.ridelink.ridelink.service;

import com.ridelink.ridelink.dto.UserLoginRequestDTO;
import com.ridelink.ridelink.dto.UserLoginResponseDTO;
import com.ridelink.ridelink.dto.UserRegisterRequestDTO;
import com.ridelink.ridelink.dto.UserRegisterResponseDTO;
import com.ridelink.ridelink.entity.User;
import com.ridelink.ridelink.exception.EmailAlreadyExists;
import com.ridelink.ridelink.exception.PhoneAlreadyExists;
import com.ridelink.ridelink.repository.UserRepository;
import com.ridelink.ridelink.security.UserPrinciple;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
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

    @Override
    public UserLoginResponseDTO login(UserLoginRequestDTO request) {
        Authentication authenticationRequest = new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        );
        Authentication authentication = authenticationManager.authenticate(authenticationRequest);

        UserPrinciple userDetails = (UserPrinciple) authentication.getPrincipal();

        String token = jwtService.generateToken(userDetails);

        return UserLoginResponseDTO.builder()
                .id(userDetails.getId())
                .name(userDetails.getName())
                .email(userDetails.getUsername())
                .role(userDetails.getRole())
                .token(token)
                .build();
    }
}
