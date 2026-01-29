package org.rent.rentify.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rent.rentify.enums.ReputationStatus;

import java.util.UUID;

@Entity
@Table(name = "tenant_profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TenantProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne
    @JoinColumn(
            name = "user_id",
            nullable = false,
            unique = true
    )
    private User user;

    @Column
    private String photoUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReputationStatus status = ReputationStatus.GOOD;

    public User getUser() { return user; }
    public ReputationStatus getStatus() { return status; }
    public void setStatus(ReputationStatus status) { this.status = status; }
}
