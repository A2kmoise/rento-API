package org.rent.rentify.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.rent.rentify.model.Otp;
import org.rent.rentify.repository.OtpRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class OtpService {

    private final OtpRepository otpRepository;

    @Value("${twilio.account-sid:ACplaceholder}")
    private String accountSid;

    @Value("${twilio.auth-token:tokenplaceholder}")
    private String authToken;

    @Value("${twilio.phone-number:+1234567890}")
    private String fromPhoneNumber;

    public OtpService(OtpRepository otpRepository) {
        this.otpRepository = otpRepository;
    }

    public void generateAndSendOtp(String telephone) {
        String code = String.format("%06d", new Random().nextInt(999999));
        
        Otp otp = new Otp();
        otp.setTelephone(telephone);
        otp.setCode(code);
        otp.setExpiresAt(LocalDateTime.now().plusMinutes(10));
        otp.setVerified(false);
        
        otpRepository.save(otp);

        try {
            // Send actual SMS
            Message message = Message.creator(
                    new PhoneNumber(telephone),
                    new PhoneNumber(fromPhoneNumber),
                    "Your Rentify OTP is " + code
            ).create();

            System.out.println("Twilio SMS sent to " + telephone + ". SID: " + message.getSid());
        } catch (Exception e) {
            System.err.println("Failed to send Twilio SMS to " + telephone + ": " + e.getMessage());
            // In a real app, you might want to re-throw or handle this differently
            // For now, we'll log it and let the user know it was "simulated" if it fails
            System.out.println("Simulated OTP for " + telephone + " (due to Twilio error): " + code);
        }
    }

    public boolean verifyOtp(String telephone, String code) {
        return otpRepository.findAll().stream()
                .filter(otp -> otp.getTelephone().equals(telephone))
                .filter(otp -> otp.getCode().equals(code))
                .filter(otp -> !otp.getVerified())
                .filter(otp -> otp.getExpiresAt().isAfter(LocalDateTime.now()))
                .findFirst()
                .map(otp -> {
                    otp.setVerified(true);
                    otpRepository.save(otp);
                    return true;
                })
                .orElse(false);
    }
}
