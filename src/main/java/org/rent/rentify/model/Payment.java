package org.rent.rentify.model;

import jakarta.persistence.*;
import org.rent.rentify.enums.PaymentStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "paymentTransactions")
public class Payment {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;

    @OneToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private LocalDateTime paidDate=LocalDateTime.now();

    @Column(nullable = false)
    private PaymentStatus status;
}
