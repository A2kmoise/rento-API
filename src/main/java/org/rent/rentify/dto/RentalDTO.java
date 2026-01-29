package org.rent.rentify.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalDTO {
    private UUID rentalId;
    private UUID propertyId;
    private String propertyDescription;
    private String propertyLocation;
    private Integer bedrooms;
    private Integer bathrooms;
    private Double rentAmount;
    private Integer dueDay;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean active;
    private String ownerName;
    private String ownerTelephone;

    public void setRentalId(UUID rentalId) { this.rentalId = rentalId; }
    public void setPropertyId(UUID propertyId) { this.propertyId = propertyId; }
    public void setPropertyDescription(String propertyDescription) { this.propertyDescription = propertyDescription; }
    public void setPropertyLocation(String propertyLocation) { this.propertyLocation = propertyLocation; }
    public void setBedrooms(Integer bedrooms) { this.bedrooms = bedrooms; }
    public void setBathrooms(Integer bathrooms) { this.bathrooms = bathrooms; }
    public void setRentAmount(Double rentAmount) { this.rentAmount = rentAmount; }
    public void setDueDay(Integer dueDay) { this.dueDay = dueDay; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }
    public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }
    public void setActive(Boolean active) { this.active = active; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
    public void setOwnerTelephone(String ownerTelephone) { this.ownerTelephone = ownerTelephone; }

    public UUID getRentalId() { return rentalId; }
    public UUID getPropertyId() { return propertyId; }
}
