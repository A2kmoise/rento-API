package org.rent.rentify.service;

import org.rent.rentify.dto.PaymentDTO;
import org.rent.rentify.dto.RentalDTO;
import org.rent.rentify.model.Notification;
import org.rent.rentify.model.Payment;
import org.rent.rentify.model.Rental;
import org.rent.rentify.model.User;
import org.rent.rentify.repository.NotificationRepository;
import org.rent.rentify.repository.PaymentRepository;
import org.rent.rentify.repository.RentalRepository;
import org.rent.rentify.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TenantService {

    private final UserRepository userRepository;
    private final RentalRepository rentalRepository;
    private final PaymentRepository paymentRepository;
    private final NotificationRepository notificationRepository;

    public TenantService(UserRepository userRepository, RentalRepository rentalRepository,
                         PaymentRepository paymentRepository, NotificationRepository notificationRepository) {
        this.userRepository = userRepository;
        this.rentalRepository = rentalRepository;
        this.paymentRepository = paymentRepository;
        this.notificationRepository = notificationRepository;
    }

    public List<RentalDTO> getMyRentals(UUID tenantId) {
        User tenant = userRepository.findById(tenantId)
                .orElseThrow(() -> new RuntimeException("Tenant not found"));

        List<Rental> rentals = rentalRepository.findByTenantAndActiveTrue(tenant);

        return rentals.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<PaymentDTO> getMyPaymentHistory(UUID tenantId) {
        User tenant = userRepository.findById(tenantId)
                .orElseThrow(() -> new RuntimeException("Tenant not found"));

        List<Payment> payments = paymentRepository.findByTenantOrderByPaidDateDesc(tenant);

        return payments.stream().map(this::convertToPaymentDTO).collect(Collectors.toList());
    }

    public List<Notification> getMyNotifications(UUID tenantId) {
        User tenant = userRepository.findById(tenantId)
                .orElseThrow(() -> new RuntimeException("Tenant not found"));

        return notificationRepository.findByUserOrderByCreatedAtDesc(tenant);
    }

    public void markNotificationAsRead(UUID notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setIsRead(true);
        notificationRepository.save(notification);
    }

    private RentalDTO convertToDTO(Rental rental) {
        RentalDTO dto = new RentalDTO();
        dto.setRentalId(rental.getId());
        dto.setPropertyId(rental.getProperty().getId());
        dto.setPropertyDescription(rental.getProperty().getDescription());
        dto.setPropertyLocation(rental.getProperty().getLocation());
        dto.setBedrooms(rental.getProperty().getBedrooms());
        dto.setBathrooms(rental.getProperty().getBathrooms());
        dto.setRentAmount(rental.getRentAmount());
        dto.setDueDay(rental.getDueDay());
        dto.setStartDate(rental.getStartDate());
        dto.setEndDate(rental.getEndDate());
        dto.setActive(rental.getActive());
        dto.setOwnerName(rental.getProperty().getOwner().getFullName());
        dto.setOwnerTelephone(rental.getProperty().getOwner().getTelephone());
        return dto;
    }

    private PaymentDTO convertToPaymentDTO(Payment payment) {
        PaymentDTO dto = new PaymentDTO();
        dto.setPaymentId(payment.getId());
        dto.setRentalId(payment.getRental() != null ? payment.getRental().getId() : null);
        dto.setAmount(payment.getAmount());
        dto.setPaymentMethod(payment.getPaymentMethod());
        dto.setStatus(payment.getStatus());
        dto.setPaidDate(payment.getPaidDate());
        dto.setOwnerName(payment.getOwner().getFullName());
        dto.setTenantName(payment.getTenant().getFullName());
        return dto;
    }
}
