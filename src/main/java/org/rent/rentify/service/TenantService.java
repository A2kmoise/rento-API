package org.rent.rentify.service;

import org.rent.rentify.dto.PaymentDTO;
import org.rent.rentify.dto.PropertyListDTO;
import org.rent.rentify.dto.RentalDTO;
import org.rent.rentify.enums.PropertyStatus;
import org.rent.rentify.model.*;
import org.rent.rentify.repository.NotificationRepository;
import org.rent.rentify.repository.PaymentRepository;
import org.rent.rentify.repository.PropertyRepository;
import org.rent.rentify.repository.RentalRepository;
import org.rent.rentify.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TenantService {

    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;
    private final RentalRepository rentalRepository;
    private final PaymentRepository paymentRepository;
    private final NotificationRepository notificationRepository;

    public TenantService(UserRepository userRepository, PropertyRepository propertyRepository,
                         RentalRepository rentalRepository, PaymentRepository paymentRepository,
                         NotificationRepository notificationRepository) {
        this.userRepository = userRepository;
        this.propertyRepository = propertyRepository;
        this.rentalRepository = rentalRepository;
        this.paymentRepository = paymentRepository;
        this.notificationRepository = notificationRepository;
    }

    public List<PropertyListDTO> getAvailableProperties() {
        List<Property> properties = propertyRepository.findByStatus(PropertyStatus.AVAILABLE);
        return properties.stream().map(this::convertToPropertyDTO).collect(Collectors.toList());
    }

    public List<RentalDTO> getMyRentals(UUID tenantId) {
        User tenant = userRepository.findById(tenantId)
                .orElseThrow(() -> new RuntimeException("Tenant not found"));
        List<Rental> rentals = rentalRepository.findByTenantAndActiveTrue(tenant);
        return rentals.stream().map(this::convertToRentalDTO).collect(Collectors.toList());
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

    private PropertyListDTO convertToPropertyDTO(Property property) {
        PropertyListDTO dto = new PropertyListDTO();
        dto.setPropertyId(property.getId());
        dto.setDescription(property.getDescription());
        dto.setLocation(property.getLocation());
        dto.setRentAmount(property.getRentAmount());
        dto.setDueDay(property.getDueDay());
        dto.setBedrooms(property.getBedrooms());
        dto.setBathrooms(property.getBathrooms());
        dto.setStatus(property.getStatus());
        dto.setOwnerName(property.getOwner().getFullName());
        dto.setOwnerTelephone(property.getOwner().getTelephone());
        return dto;
    }

    private RentalDTO convertToRentalDTO(Rental rental) {
        RentalDTO dto = new RentalDTO();
        dto.setRentalId(rental.getId());
        dto.setPropertyId(rental.getProperty().getId());
        dto.setPropertyDescription(rental.getProperty().getDescription());
        dto.setPropertyLocation(rental.getProperty().getLocation());
        dto.setBedrooms(rental.getProperty().getBedrooms());
        dto.setBathrooms(rental.getProperty().getBathrooms());
        dto.setRentAmount(rental.getProperty().getRentAmount());
        dto.setDueDay(rental.getProperty().getDueDay());
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
