package org.rent.rentify.repository;

import org.rent.rentify.model.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OtpRepository extends JpaRepository<Otp, UUID> {
    Optional<Otp> findByTelephoneAndVerifiedFalseOrderByCreatedAtDesc(String telephone);

}
