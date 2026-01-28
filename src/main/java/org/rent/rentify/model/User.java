package org.rent.rentify.model;

import jakarta.persistence.*;
import org.rent.rentify.enums.UserRoles;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "users", indexes = {
                @Index(name = "idx_users_phonenumber", columnList = "phonenumber")
        })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String Telephone;

    @Column(nullable = false)
    private  String Password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRoles role;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
