package org.rent.rentify.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "otp_codes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Otp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String telephone;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    @Column(nullable = false)
    private Boolean verified = false;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public UUID getId() { return id; }
    public String getTelephone() { return telephone; }
    public String getCode() { return code; }
    public LocalDateTime getExpiresAt() { return expiresAt; }
    public Boolean getVerified() { return verified; }
    
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public void setCode(String code) { this.code = code; }
    public void setExpiresAt(LocalDateTime expiresAt) { this.expiresAt = expiresAt; }
    public void setVerified(Boolean verified) { this.verified = verified; }
}
