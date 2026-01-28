package org.rent.rentify.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TenantRepository extends JpaRepository<org.rent.rentify.model.TenantProfile, UUID> {

}
