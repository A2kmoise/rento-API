package org.rent.rentify.repository;

import org.rent.rentify.enums.PropertyStatus;
import org.rent.rentify.model.Property;
import org.rent.rentify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PropertyRepository extends JpaRepository<Property, UUID> {
    List<Property> findByOwner(User owner);
    List<Property> findByStatus(PropertyStatus status);
}
