package org.rent.rentify.controller;

import org.rent.rentify.dto.PaymentDTO;
import org.rent.rentify.dto.RentalDTO;
import org.rent.rentify.model.Notification;
import org.rent.rentify.security.JwtUtil;
import org.rent.rentify.service.TenantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tenant")
public class TenantController {

    private final TenantService tenantService;
    private final JwtUtil jwtUtil;

    public TenantController(TenantService tenantService, JwtUtil jwtUtil) {
        this.tenantService = tenantService;
        this.jwtUtil = jwtUtil;
    }
/*
GET ALL RENTALS
 */
    @GetMapping("/rentals")
    public ResponseEntity<List<RentalDTO>> getMyRentals(@RequestHeader("Authorization") String token) {
        UUID tenantId = extractUserIdFromToken(token);
        return ResponseEntity.ok(tenantService.getMyRentals(tenantId));
    }
/*
GET OWNERS PAYMENT HISTORY
 */
    @GetMapping("/payments")
    public ResponseEntity<List<PaymentDTO>> getPaymentHistory(@RequestHeader("Authorization") String token) {
        UUID tenantId = extractUserIdFromToken(token);
        return ResponseEntity.ok(tenantService.getMyPaymentHistory(tenantId));
    }

    /*
    GET MTY NOTIFICATIONS
     */
    @GetMapping("/notifications")
    public ResponseEntity<List<Notification>> getNotifications(@RequestHeader("Authorization") String token) {
        UUID tenantId = extractUserIdFromToken(token);
        return ResponseEntity.ok(tenantService.getMyNotifications(tenantId));
    }
/*
MARK AS READ
 */
    @PutMapping("/notifications/{id}/read")
    public ResponseEntity<String> markNotificationAsRead(@PathVariable UUID id) {
        tenantService.markNotificationAsRead(id);
        return ResponseEntity.ok("Notification marked as read");
    }

    private UUID extractUserIdFromToken(String authHeader) {
        String token = authHeader.substring(7); // Remove "Bearer " prefix
        return jwtUtil.extractUserId(token);
    }
}
