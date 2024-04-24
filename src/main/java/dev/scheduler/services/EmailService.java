package dev.scheduler.services;

import dev.scheduler.classes.MultipleReceiverRequest;
import dev.scheduler.classes.SingleReceiverRequest;
import dev.scheduler.entities.auth.AuthUserEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class EmailService {
    private JavaMailSender javaMailSender;
    @Value("{spring.mail.sender.email}") private String senderEmail;
    @Value("{spring.mail.sender.text}") private String senderText;
    public void sendTextEmail(AuthUserEntity request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        message.setTo(request.getEmail());
        message.setSubject("Тестовое письмо");
        message.setText("Текстовое сообщение в тестовом письме.");
        javaMailSender.send(message);
    }
    public void sendHtmlEmail(MultipleReceiverRequest request) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(new InternetAddress(senderEmail, senderText));
        helper.setTo(request.getReceivers().toArray(new String[0]));
        helper.setCc(request.getCopy());
        helper.setBcc(request.getHiddenCopy());
        helper.setSubject("Тестовое письмо");
        helper.setText("<p>Сообщение в формате <b>Html</b>.<br>Вторая строка.</p>", true);
        javaMailSender.send(message);
    }
}
