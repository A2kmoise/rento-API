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
}
