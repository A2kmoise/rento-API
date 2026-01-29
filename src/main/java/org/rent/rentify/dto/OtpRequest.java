package org.rent.rentify.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OtpRequest {
    @NotBlank(message = "Telephone is required")
    private String telephone;
    
    public String getTelephone() { return telephone; }
}
