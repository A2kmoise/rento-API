package org.rent.rentify.repository;

import org.rent.rentify.model.Rental;
import org.rent.rentify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RentalRepository extends JpaRepository<Rental, UUID> {
    List<Rental> findByTenantAndActiveTrue(User tenant);
    List<Rental> findByProperty_OwnerAndActiveTrue(User owner);
    Optional<Rental> findByTenantAndActiveTrue(UUID tenantId);
    List<Rental> findByProperty_Owner(User owner);
}
