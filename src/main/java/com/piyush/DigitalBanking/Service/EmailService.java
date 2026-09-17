package com.piyush.DigitalBanking.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendAccountCreationEmail(
            String customerEmail,
            String customerName,
            String accountNumber,
            String cifNumber) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(customerEmail);
        message.setSubject("Digital Banking - Account Created Successfully");

        message.setText(
                "Dear " + customerName + ",\n\n" +
                        "Your bank account has been created successfully.\n\n" +
                        "CIF Number: " + cifNumber + "\n" +
                        "Account Number: " + accountNumber + "\n\n" +
                        "Thank you for choosing Digital Banking.\n\n" +
                        "Regards,\n" +
                        "Digital Banking Team"
        );

        mailSender.send(message);
    }
}