package org.rent.rentify.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class LoginRequest {
    @NotBlank(message = "Telephone is required")
    private String telephone;

    @NotBlank(message = "Password is required")
    private String password;

    public LoginRequest(String telephone, String password) {
        this.telephone = telephone;
        this.password = password;
    }

    public String getTelephone() { return telephone; }
    public String getPassword() { return password; }
}
