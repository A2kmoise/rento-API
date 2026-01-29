package org.rent.rentify.controller;

import jakarta.validation.Valid;
import org.rent.rentify.dto.AuthResponse;
import org.rent.rentify.dto.LoginRequest;
import org.rent.rentify.dto.RegisterRequest;
import org.rent.rentify.dto.VerifyRegistrationRequest;
import org.rent.rentify.dto.OtpRequest;
import org.rent.rentify.dto.OtpVerificationRequest;
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
    USER REGISTERING - STEP 1: SEND OTP
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    /*
    USER REGISTERING - STEP 2: VERIFY OTP AND SAVE USER
     */
    @PostMapping("/verify-registration")
    public ResponseEntity<AuthResponse> verifyRegistration(@Valid @RequestBody VerifyRegistrationRequest request) {
        return ResponseEntity.ok(authService.verifyRegistration(request));
    }
    /*
      USER LOGIN (OWNER AND TENANT)
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
    /*
    SIGNUP OTP (Resend OTP)
     */
    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@Valid @RequestBody OtpRequest request) {
        authService.sendOtp(request.getTelephone());
        return ResponseEntity.ok("OTP sent successfully to " + request.getTelephone());
    }
    /*
    OTP VERIFICATION (General Purpose)
     */
    @PostMapping("/verify-otp")
    public ResponseEntity<AuthResponse> verifyOtp(@Valid @RequestBody OtpVerificationRequest request) {
        return ResponseEntity.ok(authService.verifyOtp(request.getTelephone(), request.getOtp()));
    }
}
