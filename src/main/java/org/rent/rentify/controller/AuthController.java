package org.rent.rentify.controller;

import jakarta.validation.Valid;
import org.rent.rentify.dto.AuthResponse;
import org.rent.rentify.dto.LoginRequest;
import org.rent.rentify.dto.RegisterRequest;
import org.rent.rentify.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    /*
    USER REGISTERING (OWNER AND TENANT)
     */
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }
    /*
      USER LOGIN (OWNER AND TENANT)
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
    /*
    SIGNUP OTP
     */
    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestParam String telephone) {
        authService.sendOtp(telephone);
        return ResponseEntity.ok("OTP sent successfully (simulated)");
    }
    /*
    OTP VERIFICATION
     */
    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestParam String telephone, @RequestParam String code) {
        authService.verifyOtp(telephone, code);
        return ResponseEntity.ok("OTP verified successfully (simulated)");
    }
}
