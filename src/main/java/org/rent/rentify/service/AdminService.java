package org.rent.rentify.service;

import org.rent.rentify.enums.PaymentStatus;
import org.rent.rentify.enums.UserRoles;
import org.rent.rentify.model.Payment;
import org.rent.rentify.model.Property;
import org.rent.rentify.model.User;
import org.rent.rentify.repository.PaymentRepository;
import org.rent.rentify.repository.PropertyRepository;
import org.rent.rentify.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;
    private final PaymentRepository paymentRepository;

    public AdminService(UserRepository userRepository, PropertyRepository propertyRepository, PaymentRepository paymentRepository) {
        this.userRepository = userRepository;
        this.propertyRepository = propertyRepository;
        this.paymentRepository = paymentRepository;
    }

    public long totalUsers() {
        return userRepository.count();
    }

    public long totalProperties() {
        return propertyRepository.count();
    }

    public long pendingPayments() {
        return paymentRepository.countByStatus(PaymentStatus.PENDING);
    }

    public long revenueReceived() {
        return paymentRepository.countByStatus(PaymentStatus.COMPLETED) + 
               paymentRepository.countByStatus(PaymentStatus.FULL);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public List<Property> getPropertiesDetails() {
        return propertyRepository.findAll();
    }

    public List<Payment> getPaymentsDetails() {
        return paymentRepository.findAll();
    }

    public void deactivateAccount(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        // deactivation is changing use to unverified status
        user.setPhoneVerified(false);
        userRepository.save(user);
    }

    public void validateAdmin(UUID adminId) {
        User user = userRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin user not found"));
        if (user.getRole() != UserRoles.ADMIN) {
            throw new RuntimeException("Access denied: User is not an admin");
        }
    }
}
