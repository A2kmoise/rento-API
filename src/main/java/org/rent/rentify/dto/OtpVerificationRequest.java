package org.rent.rentify.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OtpVerificationRequest {
    @NotBlank(message = "Telephone is required")
    private String telephone;

    @NotBlank(message = "OTP code is required")
    private String otp;
    
    public String getTelephone() { return telephone; }
    public String getOtp() { return otp; }
}
