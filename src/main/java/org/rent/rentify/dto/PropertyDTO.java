package org.rent.rentify.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rent.rentify.enums.PropertyStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyDTO {
    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Rent amount is required")
    @Min(value = 0, message = "Rent amount must be positive")
    private Double rentAmount;

    @NotNull(message = "Due day is required")
    @Min(value = 1, message = "Due day must be between 1 and 31")
    private Integer dueDay;

    private Integer bedrooms;
    private Integer bathrooms;

    @NotNull(message = "Status is required")
    private PropertyStatus status;

    public String getDescription() { return description; }
    public String getLocation() { return location; }
    public Double getRentAmount() { return rentAmount; }
    public Integer getDueDay() { return dueDay; }
    public Integer getBedrooms() { return bedrooms; }
    public Integer getBathrooms() { return bathrooms; }
    public PropertyStatus getStatus() { return status; }
}
