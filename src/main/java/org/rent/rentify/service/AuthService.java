package org.rent.rentify.service;

import org.rent.rentify.dto.AuthResponse;
import org.rent.rentify.dto.LoginRequest;
import org.rent.rentify.dto.RegisterRequest;
import org.rent.rentify.dto.VerifyRegistrationRequest;
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
    private final OtpService otpService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil, AuthenticationManager authenticationManager,
                       OtpService otpService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.otpService = otpService;
    }

    public String register(RegisterRequest request) {
        // Check if user already exists
        if (userRepository.existsByTelephone(request.getTelephone())) {
            throw new RuntimeException("User with this telephone already exists");
        }

        // Create and save user but set verified to false
        User user = new User();
        user.setFullName(request.getFullName());
        user.setTelephone(request.getTelephone());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setPhoneVerified(false);
        userRepository.save(user);

        // Send OTP
        otpService.generateAndSendOtp(request.getTelephone());

        return "OTP sent to " + request.getTelephone() + ". Please verify to complete registration.";
    }

    public AuthResponse verifyRegistration(VerifyRegistrationRequest request) {
        // Verify OTP
        boolean verified = otpService.verifyOtp(request.getTelephone(), request.getOtp());
        if (!verified) {
            throw new RuntimeException("Invalid or expired OTP");
        }

        // Find user and mark as verified
        User user = userRepository.findByTelephone(request.getTelephone())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        user.setPhoneVerified(true);
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
        // Find user
        User user = userRepository.findByTelephone(request.getTelephone())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if phone is verified
        if (!user.getPhoneVerified()) {
            throw new RuntimeException("Phone number not verified. Please verify using OTP.");
        }

        // Authenticate user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getTelephone(), request.getPassword())
        );

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

    public void sendOtp(String telephone) {
        otpService.generateAndSendOtp(telephone);
    }

    public AuthResponse verifyOtp(String telephone, String code) {
        boolean verified = otpService.verifyOtp(telephone, code);
        if (verified) {
            User user = userRepository.findByTelephone(telephone)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            
            user.setPhoneVerified(true);
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
        } else {
            throw new RuntimeException("Invalid or expired OTP");
        }
    }
}
