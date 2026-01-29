package org.rent.rentify.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rent.rentify.enums.PropertyStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "properties")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(
            name = "owner_id",
            nullable = false
    )
    private User owner;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private Double rentAmount;

    @Column(nullable = false)
    private Integer dueDay; // Day of month when rent is due (1-31)

    @Column
    private Integer bedrooms;

    @Column
    private Integer bathrooms;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PropertyStatus status;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column
    private LocalDateTime updatedAt;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public User getOwner() { return owner; }
    public void setOwner(User owner) { this.owner = owner; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public Double getRentAmount() { return rentAmount; }
    public void setRentAmount(Double rentAmount) { this.rentAmount = rentAmount; }
    public Integer getDueDay() { return dueDay; }
    public void setDueDay(Integer dueDay) { this.dueDay = dueDay; }
    public Integer getBedrooms() { return bedrooms; }
    public void setBedrooms(Integer bedrooms) { this.bedrooms = bedrooms; }
    public Integer getBathrooms() { return bathrooms; }
    public void setBathrooms(Integer bathrooms) { this.bathrooms = bathrooms; }
    public PropertyStatus getStatus() { return status; }
    public void setStatus(PropertyStatus status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
