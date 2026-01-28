package org.rent.rentify.model;

import jakarta.persistence.*;
import org.rent.rentify.enums.PropertyStatus;

import java.util.UUID;

@Entity
@Table(name = "property")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private UUID id;

    @OneToOne
    @JoinColumn(
            name = "owner_id",
            nullable = false,
            unique = true
    )
    private User owner;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String pricePerMonth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PropertyStatus status;

}
