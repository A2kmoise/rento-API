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
}
