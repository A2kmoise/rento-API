package org.rent.rentify.controller;

import jakarta.validation.Valid;
import org.rent.rentify.dto.AuthResponse;
import org.rent.rentify.dto.LoginRequest;
import org.rent.rentify.dto.RegisterRequest;
import org.rent.rentify.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestParam String telephone) {
        authService.sendOtp(telephone);
        return ResponseEntity.ok("OTP sent successfully (simulated)");
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestParam String telephone, @RequestParam String code) {
        authService.verifyOtp(telephone, code);
        return ResponseEntity.ok("OTP verified successfully (simulated)");
    }
}
