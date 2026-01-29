package org.rent.rentify.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignTenantRequest {
    @NotNull(message = "Property ID is required")
    private UUID propertyId;

    @NotNull(message = "Tenant ID is required")
    private UUID tenantId;

    public UUID getPropertyId() { return propertyId; }
    public UUID getTenantId() { return tenantId; }
}
