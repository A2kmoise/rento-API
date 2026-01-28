package org.rent.rentify.service;

import org.rent.rentify.dto.PaymentRequest;
import org.rent.rentify.enums.PaymentStatus;
import org.rent.rentify.model.Notification;
import org.rent.rentify.model.Payment;
import org.rent.rentify.model.Rental;
import org.rent.rentify.repository.NotificationRepository;
import org.rent.rentify.repository.PaymentRepository;
import org.rent.rentify.repository.RentalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final RentalRepository rentalRepository;
    private final NotificationRepository notificationRepository;

    public PaymentService(PaymentRepository paymentRepository, RentalRepository rentalRepository,
                          NotificationRepository notificationRepository) {
        this.paymentRepository = paymentRepository;
        this.rentalRepository = rentalRepository;
        this.notificationRepository = notificationRepository;
    }

    @Transactional
    public Payment simulatePayment(PaymentRequest request) {
        Rental rental = rentalRepository.findById(request.getRentalId())
                .orElseThrow(() -> new RuntimeException("Rental not found"));

        // Create payment record
        Payment payment = new Payment();
        payment.setRental(rental);
        payment.setTenant(rental.getTenant());
        payment.setOwner(rental.getProperty().getOwner());
        payment.setAmount(request.getAmount());
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setStatus(PaymentStatus.COMPLETED); // Simulated payment is always successful

        Payment savedPayment = paymentRepository.save(payment);

        // Create notification for owner
        Notification ownerNotification = new Notification();
        ownerNotification.setUser(rental.getProperty().getOwner());
        ownerNotification.setMessage("Payment received: " + request.getAmount() + " from " + rental.getTenant().getFullName());
        ownerNotification.setType("PAYMENT_RECEIVED");
        notificationRepository.save(ownerNotification);

        // Create notification for tenant
        Notification tenantNotification = new Notification();
        tenantNotification.setUser(rental.getTenant());
        tenantNotification.setMessage("Payment successful: " + request.getAmount() + " for " + rental.getProperty().getDescription());
        tenantNotification.setType("PAYMENT_SUCCESSFUL");
        notificationRepository.save(tenantNotification);

        return savedPayment;
    }
}
