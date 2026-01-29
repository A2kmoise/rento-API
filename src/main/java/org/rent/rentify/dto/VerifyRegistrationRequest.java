package org.rent.rentify.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.rent.rentify.enums.UserRoles;

@Data
public class VerifyRegistrationRequest {
    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Telephone is required")
    private String telephone;

    @NotBlank(message = "Password is required")
    private String password;

    @NotNull(message = "Role is required")
    private UserRoles role;

    @NotBlank(message = "OTP code is required")
    private String otp;
    
    public String getFullName() { return fullName; }
    public String getTelephone() { return telephone; }
    public String getPassword() { return password; }
    public UserRoles getRole() { return role; }
    public String getOtp() { return otp; }
}
