package org.rent.rentify.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    @NotNull(message = "Rental ID is required")
    private UUID rentalId;

    @NotNull(message = "Amount is required")
    @Min(value = 0, message = "Amount must be positive")
    private Double amount;

    @NotBlank(message = "Payment method is required")
    private String paymentMethod; // "CASH", "MOBILE_MONEY", "BANK_TRANSFER"

    public UUID getRentalId() { return rentalId; }
    public Double getAmount() { return amount; }
    public String getPaymentMethod() { return paymentMethod; }
}
