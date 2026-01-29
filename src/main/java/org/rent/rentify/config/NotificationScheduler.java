package org.rent.rentify.config;

import org.rent.rentify.model.Notification;
import org.rent.rentify.model.Rental;
import org.rent.rentify.repository.NotificationRepository;
import org.rent.rentify.repository.RentalRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationScheduler {

    private final RentalRepository rentalRepository;
    private final NotificationRepository notificationRepository;

    public NotificationScheduler(RentalRepository rentalRepository, NotificationRepository notificationRepository) {
        this.rentalRepository = rentalRepository;
        this.notificationRepository = notificationRepository;
    }

    // Runs on the 1st of every month at midnight
    @Scheduled(cron = "0 0 0 1 * ?")
    public void sendMonthlyRentReminders() {
        List<Rental> activeRentals = rentalRepository.findAll().stream()
                .filter(Rental::getActive)
                .toList();

        for (Rental rental : activeRentals) {
            Notification notification = new Notification();
            notification.setUser(rental.getTenant());
            notification.setMessage("Reminder: Your rent for " + rental.getProperty().getDescription() + 
                    " is due on day " + rental.getProperty().getDueDay() + " of this month.");
            notification.setType("RENT_REMINDER");
            notificationRepository.save(notification);
        }
    }
}
