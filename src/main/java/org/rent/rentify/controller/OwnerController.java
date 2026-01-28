package org.rent.rentify.controller;

import jakarta.validation.Valid;
import org.rent.rentify.dto.AssignTenantRequest;
import org.rent.rentify.dto.PaymentDTO;
import org.rent.rentify.dto.PropertyDTO;
import org.rent.rentify.model.Property;
import org.rent.rentify.model.Rental;
import org.rent.rentify.model.User;
import org.rent.rentify.security.JwtUtil;
import org.rent.rentify.service.OwnerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/owner")
public class OwnerController {

    private final OwnerService ownerService;
    private final JwtUtil jwtUtil;

    public OwnerController(OwnerService ownerService, JwtUtil jwtUtil) {
        this.ownerService = ownerService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/properties")
    public ResponseEntity<Property> addProperty(
            @RequestHeader("Authorization") String token,
            @Valid @RequestBody PropertyDTO dto) {
        UUID ownerId = extractUserIdFromToken(token);
        return ResponseEntity.ok(ownerService.addProperty(ownerId, dto));
    }

    @PutMapping("/properties/{id}")
    public ResponseEntity<Property> updateProperty(
            @RequestHeader("Authorization") String token,
            @PathVariable UUID id,
            @Valid @RequestBody PropertyDTO dto) {
        UUID ownerId = extractUserIdFromToken(token);
        return ResponseEntity.ok(ownerService.updateProperty(ownerId, id, dto));
    }

    @DeleteMapping("/properties/{id}")
    public ResponseEntity<String> deleteProperty(
            @RequestHeader("Authorization") String token,
            @PathVariable UUID id) {
        UUID ownerId = extractUserIdFromToken(token);
        ownerService.deleteProperty(ownerId, id);
        return ResponseEntity.ok("Property deleted successfully");
    }

    @GetMapping("/properties")
    public ResponseEntity<List<Property>> getMyProperties(@RequestHeader("Authorization") String token) {
        UUID ownerId = extractUserIdFromToken(token);
        return ResponseEntity.ok(ownerService.getMyProperties(ownerId));
    }

    @PostMapping("/assign-tenant")
    public ResponseEntity<Rental> assignTenant(
            @RequestHeader("Authorization") String token,
            @Valid @RequestBody AssignTenantRequest request) {
        UUID ownerId = extractUserIdFromToken(token);
        return ResponseEntity.ok(ownerService.assignTenant(ownerId, request));
    }

    @GetMapping("/tenants")
    public ResponseEntity<List<User>> getMyTenants(@RequestHeader("Authorization") String token) {
        UUID ownerId = extractUserIdFromToken(token);
        return ResponseEntity.ok(ownerService.getMyTenants(ownerId));
    }

    @GetMapping("/payments")
    public ResponseEntity<List<PaymentDTO>> getPaymentHistory(@RequestHeader("Authorization") String token) {
        UUID ownerId = extractUserIdFromToken(token);
        return ResponseEntity.ok(ownerService.getPaymentHistory(ownerId));
    }

    private UUID extractUserIdFromToken(String authHeader) {
        String token = authHeader.substring(7); // Remove "Bearer " prefix
        return jwtUtil.extractUserId(token);
    }
}
