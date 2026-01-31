package org.rent.rentify.controller;

import org.rent.rentify.model.Payment;
import org.rent.rentify.model.Property;
import org.rent.rentify.model.User;
import org.rent.rentify.security.JwtUtil;
import org.rent.rentify.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final AdminService adminService;
    private final JwtUtil jwtUtil;

    public AdminController(AdminService adminService, JwtUtil jwtUtil) {
        this.adminService = adminService;
        this.jwtUtil = jwtUtil;
    }

    /*
     * ADMIN: TOTAL USERS
     */
    @GetMapping("/users/count")
    public ResponseEntity<Long> totalUsers(
            @RequestHeader("Authorization") String authHeader
    ) {
        validateAdmin(authHeader);
        return ResponseEntity.ok(adminService.totalUsers());
    }

    /*
     * ADMIN: TOTAL PROPERTIES
     */
    @GetMapping("/properties/count")
    public ResponseEntity<Long> totalProperties(
            @RequestHeader("Authorization") String authHeader
    ) {
        validateAdmin(authHeader);
        return ResponseEntity.ok(adminService.totalProperties());
    }

    /*
     * ADMIN: PENDING PAYMENTS COUNT
     */
    @GetMapping("/payments/pending/count")
    public ResponseEntity<Long> pendingPayments(
            @RequestHeader("Authorization") String authHeader
    ) {
        validateAdmin(authHeader);
        return ResponseEntity.ok(adminService.pendingPayments());
    }

    /*
     * ADMIN: COMPLETED PAYMENTS / REVENUE
     */
    @GetMapping("/payments/completed/count")
    public ResponseEntity<Long> completedPayments(
            @RequestHeader("Authorization") String authHeader
    ) {
        validateAdmin(authHeader);
        return ResponseEntity.ok(adminService.revenueReceived());
    }

    /*
     * ADMIN: VIEW ALL USERS
     */
    @GetMapping("/users")
    public ResponseEntity<List<User>> allUsers(
            @RequestHeader("Authorization") String authHeader
    ) {
        validateAdmin(authHeader);
        return ResponseEntity.ok(adminService.getUsers());
    }

    /*
     * ADMIN: VIEW ALL PROPERTIES
     */
    @GetMapping("/properties")
    public ResponseEntity<List<Property>> allProperties(
            @RequestHeader("Authorization") String authHeader
    ) {
        validateAdmin(authHeader);
        return ResponseEntity.ok(adminService.getPropertiesDetails());
    }

    /*
     * ADMIN: VIEW ALL PAYMENTS
     */
    @GetMapping("/payments")
    public ResponseEntity<List<Payment>> allPayments(
            @RequestHeader("Authorization") String authHeader
    ) {
        validateAdmin(authHeader);
        return ResponseEntity.ok(adminService.getPaymentsDetails());
    }

    /*
     * ADMIN: DEACTIVATE MALICIOUS ACCOUNT
     */
    @PostMapping("/users/deactivate")
    public ResponseEntity<Void> deactivateAccount(
            @RequestHeader("Authorization") String authHeader,
            @RequestParam UUID userId
    ) {
        validateAdmin(authHeader);
        adminService.deactivateAccount(userId);
        return ResponseEntity.ok().build();
    }

    //HELPERS

    private void validateAdmin(String authHeader) {
        UUID adminId = extractUserIdFromToken(authHeader);
        adminService.validateAdmin(adminId);
    }

    private UUID extractUserIdFromToken(String authHeader) {
        String token = authHeader.substring(7); // "Bearer "
        return jwtUtil.extractUserId(token);
    }
}
