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

    public void setPaymentId(UUID paymentId) { this.paymentId = paymentId; }
    public void setRentalId(UUID rentalId) { this.rentalId = rentalId; }
    public void setAmount(Double amount) { this.amount = amount; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public void setStatus(PaymentStatus status) { this.status = status; }
    public void setPaidDate(LocalDateTime paidDate) { this.paidDate = paidDate; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
    public void setTenantName(String tenantName) { this.tenantName = tenantName; }

    public UUID getPaymentId() { return paymentId; }
    public UUID getRentalId() { return rentalId; }
    public Double getAmount() { return amount; }
    public String getPaymentMethod() { return paymentMethod; }
    public PaymentStatus getStatus() { return status; }
    public LocalDateTime getPaidDate() { return paidDate; }
    public String getOwnerName() { return ownerName; }
    public String getTenantName() { return tenantName; }
}
