package org.rent.rentify.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
public class AuthResponse {
    private String token;
    private UUID userId;
    private String role;
    private String fullName;
    private String telephone;

    public AuthResponse(String token, UUID userId, String role, String fullName, String telephone) {
        this.token = token;
        this.userId = userId;
        this.role = role;
        this.fullName = fullName;
        this.telephone = telephone;
    }

    public String getToken() { return token; }
    public UUID getUserId() { return userId; }
    public String getRole() { return role; }
    public String getFullName() { return fullName; }
    public String getTelephone() { return telephone; }
}
