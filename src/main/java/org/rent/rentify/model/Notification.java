package org.rent.rentify.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 500)
    private String message;

    @Column(nullable = false)
    private String type; // "RENT_REMINDER", "PAYMENT_RECEIVED", "PROPERTY_ASSIGNED"

    @Column(nullable = false)
    private Boolean isRead = false;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public UUID getId() { return id; }
    public User getUser() { return user; }
    public String getMessage() { return message; }
    public String getType() { return type; }
    public Boolean getIsRead() { return isRead; }
    
    public void setUser(User user) { this.user = user; }
    public void setMessage(String message) { this.message = message; }
    public void setType(String type) { this.type = type; }
    public void setIsRead(Boolean isRead) { this.isRead = isRead; }
}
