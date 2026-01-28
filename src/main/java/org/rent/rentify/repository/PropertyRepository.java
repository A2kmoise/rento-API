package org.rent.rentify.repository;

import org.hibernate.boot.models.JpaAnnotations;
import org.rent.rentify.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PropertyRepository extends JpaRepository<Property, UUID> {

}
