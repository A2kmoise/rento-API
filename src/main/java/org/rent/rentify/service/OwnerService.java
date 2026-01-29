package org.rent.rentify.service;

import org.rent.rentify.dto.AssignTenantRequest;
import org.rent.rentify.dto.PaymentDTO;
import org.rent.rentify.dto.PropertyDTO;
import org.rent.rentify.enums.UserRoles;
import org.rent.rentify.model.*;
import org.rent.rentify.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OwnerService {

    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;
    private final RentalRepository rentalRepository;
    private final PaymentRepository paymentRepository;
    private final NotificationRepository notificationRepository;

    public OwnerService(UserRepository userRepository, PropertyRepository propertyRepository,
                        RentalRepository rentalRepository, PaymentRepository paymentRepository,
                        NotificationRepository notificationRepository) {
        this.userRepository = userRepository;
        this.propertyRepository = propertyRepository;
        this.rentalRepository = rentalRepository;
        this.paymentRepository = paymentRepository;
        this.notificationRepository = notificationRepository;
    }

    public Property addProperty(UUID ownerId, PropertyDTO dto) {
        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        if (owner.getRole() != UserRoles.OWNER) {
            throw new RuntimeException("Only owners can add properties");
        }

        Property property = new Property();
        property.setOwner(owner);
        property.setDescription(dto.getDescription());
        property.setLocation(dto.getLocation());
        property.setRentAmount(dto.getRentAmount());
        property.setDueDay(dto.getDueDay());
        property.setBedrooms(dto.getBedrooms());
        property.setBathrooms(dto.getBathrooms());
        property.setStatus(dto.getStatus());

        return propertyRepository.save(property);
    }

    public Property updateProperty(UUID ownerId, UUID propertyId, PropertyDTO dto) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));

        if (!property.getOwner().getId().equals(ownerId)) {
            throw new RuntimeException("You can only update your own properties");
        }

        property.setDescription(dto.getDescription());
        property.setLocation(dto.getLocation());
        property.setRentAmount(dto.getRentAmount());
        property.setDueDay(dto.getDueDay());
        property.setBedrooms(dto.getBedrooms());
        property.setBathrooms(dto.getBathrooms());
        property.setStatus(dto.getStatus());
        property.setUpdatedAt(LocalDateTime.now());

        return propertyRepository.save(property);
    }

    public void deleteProperty(UUID ownerId, UUID propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));

        if (!property.getOwner().getId().equals(ownerId)) {
            throw new RuntimeException("You can only delete your own properties");
        }

        propertyRepository.delete(property);
    }

    public List<Property> getMyProperties(UUID ownerId) {
        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        return propertyRepository.findByOwner(owner);
    }

    @Transactional
    public Rental assignTenant(UUID ownerId, AssignTenantRequest request) {
        Property property = propertyRepository.findById(request.getPropertyId())
                .orElseThrow(() -> new RuntimeException("Property not found"));

        if (!property.getOwner().getId().equals(ownerId)) {
            throw new RuntimeException("You can only assign tenants to your own properties");
        }

        User tenant = userRepository.findById(request.getTenantId())
                .orElseThrow(() -> new RuntimeException("Tenant not found"));

        if (tenant.getRole() != UserRoles.TENANT) {
            throw new RuntimeException("Selected user is not a tenant");
        }

        // Create rental
        Rental rental = new Rental();
        rental.setTenant(tenant);
        rental.setProperty(property);
        rental.setStartDate(LocalDateTime.now());
        rental.setActive(true);

        Rental savedRental = rentalRepository.save(rental);

        // Create notification for tenant
        Notification notification = new Notification();
        notification.setUser(tenant);
        notification.setMessage("You have been assigned to property: " + property.getDescription() + " at " + property.getLocation());
        notification.setType("PROPERTY_ASSIGNED");
        notificationRepository.save(notification);

        return savedRental;
    }

    public List<User> getMyTenants(UUID ownerId) {
        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        List<Rental> rentals = rentalRepository.findByProperty_OwnerAndActiveTrue(owner);

        return rentals.stream()
                .map(Rental::getTenant)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<PaymentDTO> getPaymentHistory(UUID ownerId) {
        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        List<Payment> payments = paymentRepository.findByOwnerOrderByPaidDateDesc(owner);

        return payments.stream().map(this::convertToPaymentDTO).collect(Collectors.toList());
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
