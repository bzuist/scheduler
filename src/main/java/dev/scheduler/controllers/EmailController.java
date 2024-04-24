package dev.scheduler.controllers;

import dev.scheduler.classes.SingleReceiverRequest;
import dev.scheduler.services.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/emails")

public class EmailController {

    EmailService emailService;
    @PostMapping(value = "/text")
    public void sendTextEmail(@RequestBody SingleReceiverRequest request) {
        emailService.sendTextEmail(request);
    }

}