package org.rent.rentify.dto;

import lombok.Data;
import org.rent.rentify.enums.PropertyStatus;
import java.util.UUID;

@Data
public class PropertyListDTO {
    private UUID propertyId;
    private String description;
    private String location;
    private Double rentAmount;
    private Integer dueDay;
    private Integer bedrooms;
    private Integer bathrooms;
    private PropertyStatus status;
    private String ownerName;
    private String ownerTelephone;

    public void setPropertyId(UUID propertyId) { this.propertyId = propertyId; }
    public void setDescription(String description) { this.description = description; }
    public void setLocation(String location) { this.location = location; }
    public void setRentAmount(Double rentAmount) { this.rentAmount = rentAmount; }
    public void setDueDay(Integer dueDay) { this.dueDay = dueDay; }
    public void setBedrooms(Integer bedrooms) { this.bedrooms = bedrooms; }
    public void setBathrooms(Integer bathrooms) { this.bathrooms = bathrooms; }
    public void setStatus(PropertyStatus status) { this.status = status; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
    public void setOwnerTelephone(String ownerTelephone) { this.ownerTelephone = ownerTelephone; }
}
