package org.rent.rentify.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rent.rentify.enums.PaymentStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
    private UUID paymentId;
    private UUID rentalId;
    private Double amount;
    private String paymentMethod;
    private PaymentStatus status;
    private LocalDateTime paidDate;
    private String ownerName;
    private String tenantName;
}
