package org.rent.rentify.model;

import jakarta.persistence.*;
import org.rent.rentify.enums.BookingStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table( name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne
    @JoinColumn( name = "tenant_id" )
    private User tenant;

    @ManyToOne
    @JoinColumn( name = "owner_id" )
    private User owner;

    @OneToOne
    @JoinColumn( name = "property_id" )
    private Property property;

    @Column(nullable = false)
    private LocalDateTime startDate = LocalDateTime.now();

    @Column(nullable = false)
    private  LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;
}
