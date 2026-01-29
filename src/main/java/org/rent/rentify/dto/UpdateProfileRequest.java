package org.rent.rentify.dto;

import lombok.Data;

@Data
public class UpdateProfileRequest {
    private String fullName;
    private String password;

    public UpdateProfileRequest(String fullName, String password) {
        this.fullName = fullName;
        this.password = password;
    }

    public String getFullName() { return fullName; }
    public String getPassword() { return password; }
}
