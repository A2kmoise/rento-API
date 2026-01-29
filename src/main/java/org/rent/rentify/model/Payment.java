package org.rent.rentify.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rent.rentify.enums.PaymentStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private User tenant;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @Column(nullable = false)
    private Double amount;

    @Column
    private String paymentMethod; // Simulated: "CASH", "MOBILE_MONEY", "BANK_TRANSFER"

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    @Column(nullable = false)
    private LocalDateTime paidDate = LocalDateTime.now();

    public UUID getId() { return id; }
    public Rental getRental() { return rental; }
    public User getTenant() { return tenant; }
    public User getOwner() { return owner; }
    public Double getAmount() { return amount; }
    public String getPaymentMethod() { return paymentMethod; }
    public org.rent.rentify.enums.PaymentStatus getStatus() { return status; }
    public LocalDateTime getPaidDate() { return paidDate; }
    
    public void setRental(Rental rental) { this.rental = rental; }
    public void setTenant(User tenant) { this.tenant = tenant; }
    public void setOwner(User owner) { this.owner = owner; }
    public void setAmount(Double amount) { this.amount = amount; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public void setStatus(org.rent.rentify.enums.PaymentStatus status) { this.status = status; }
}
