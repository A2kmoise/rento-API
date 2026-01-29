package org.rent.rentify.controller;

import jakarta.validation.Valid;
import org.rent.rentify.dto.PaymentRequest;
import org.rent.rentify.model.Payment;
import org.rent.rentify.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/simulate")
    public ResponseEntity<Payment> simulatePayment(@Valid @RequestBody PaymentRequest request) {
        return ResponseEntity.ok(paymentService.simulatePayment(request));
    }
}
