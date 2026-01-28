package org.rent.rentify.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table( name = "otp" )
public class Otp {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne
    @JoinColumn( name = "user")
    private User use;

}
