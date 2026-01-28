package org.rent.rentify.repository;

import org.rent.rentify.model.Payment;
import org.rent.rentify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    List<Payment> findByTenantOrderByPaidDateDesc(User tenant);
    List<Payment> findByOwnerOrderByPaidDateDesc(User owner);
}
