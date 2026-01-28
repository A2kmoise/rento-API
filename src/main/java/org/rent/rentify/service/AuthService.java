package org.rent.rentify.service;

import org.rent.rentify.dto.AuthResponse;
import org.rent.rentify.dto.LoginRequest;
import org.rent.rentify.dto.RegisterRequest;
import org.rent.rentify.model.User;
import org.rent.rentify.repository.UserRepository;
import org.rent.rentify.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse register(RegisterRequest request) {
        // Check if user already exists
        if (userRepository.existsByTelephone(request.getTelephone())) {
            throw new RuntimeException("User with this telephone already exists");
        }

        // Create new user
        User user = new User();
        user.setFullName(request.getFullName());
        user.setTelephone(request.getTelephone());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setPhoneVerified(false); // Will be verified via OTP later

        User savedUser = userRepository.save(user);

        // Generate JWT token
        String token = jwtUtil.generateToken(savedUser.getId(), savedUser.getTelephone(), savedUser.getRole().name());

        return new AuthResponse(
                token,
                savedUser.getId(),
                savedUser.getRole().name(),
                savedUser.getFullName(),
                savedUser.getTelephone()
        );
    }

    public AuthResponse login(LoginRequest request) {
        // Authenticate user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getTelephone(), request.getPassword())
        );

        // Find user
        User user = userRepository.findByTelephone(request.getTelephone())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Generate JWT token
        String token = jwtUtil.generateToken(user.getId(), user.getTelephone(), user.getRole().name());

        return new AuthResponse(
                token,
                user.getId(),
                user.getRole().name(),
                user.getFullName(),
                user.getTelephone()
        );
    }

    // Placeholder for OTP verification - to be implemented later
    public void sendOtp(String telephone) {
        // TODO: Implement SMS OTP sending
        System.out.println("OTP would be sent to: " + telephone);
    }

    public void verifyOtp(String telephone, String code) {
        // TODO: Implement OTP verification
        System.out.println("OTP verification for: " + telephone);
    }
}
