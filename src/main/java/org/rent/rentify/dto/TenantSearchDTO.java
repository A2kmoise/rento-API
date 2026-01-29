package org.rent.rentify.dto;

import lombok.Data;
import org.rent.rentify.enums.ReputationStatus;

@Data
public class TenantSearchDTO {
    private String name;
    private String phone;
    private String recentRentalLocation;
    private ReputationStatus reputationStatus;

    public void setName(String name) { this.name = name; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setRecentRentalLocation(String recentRentalLocation) { this.recentRentalLocation = recentRentalLocation; }
    public void setReputationStatus(ReputationStatus reputationStatus) { this.reputationStatus = reputationStatus; }
}
