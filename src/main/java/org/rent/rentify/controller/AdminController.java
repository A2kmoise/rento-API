package org.rent.rentify.controller;

import jakarta.validation.Valid;
import org.rent.rentify.dto.AuthResponse;
import org.rent.rentify.dto.LoginRequest;
import org.rent.rentify.security.JwtUtil;
import org.rent.rentify.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("api/v1/admin")
public class AdminController {

    private final AdminService adminService;
    private  final JwtUtil jwtUtil;

    private AdminController(AdminService adminService, JwtUtil jwtUtil) {
        this.adminService = adminService;
        this.jwtUtil = jwtUtil;
    }

    /*
    ADMIN VIEW TOTAL USERS
     */
    @GetMapping("/view/users")
    public ResponseEntity<Number> totalUsers(@RequestHeader("Authorization") String token){
        UUID userId = extractUserIdFromToken(token);
        return ResponseEntity.ok(adminService.)
    }

    private UUID extractUserIdFromToken(String authHeader) {
        String token = authHeader.substring(7); // Remove "Bearer " prefix
        return jwtUtil.extractUserId(token);
    }
}
