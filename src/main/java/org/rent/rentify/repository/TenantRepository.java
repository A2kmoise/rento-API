package org.rent.rentify.repository;

import org.rent.rentify.model.TenantProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TenantRepository extends JpaRepository<TenantProfile, UUID> {
    Optional<TenantProfile> findByUser_Telephone(String telephone);
}
