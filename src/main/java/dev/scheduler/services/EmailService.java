package dev.scheduler.services;

import dev.scheduler.entities.auth.AuthUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("{spring.mail.sender.email}") private String senderEmail;
    @Value("{spring.mail.sender.text}") private String senderText;
    public void sendTextEmail(AuthUserEntity authUser) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        message.setTo(authUser.getEmail());
        message.setSubject("Тестовое письмо");
        message.setText("Текстовое сообщение в тестовом письме.");
        javaMailSender.send(message);
    }

}
