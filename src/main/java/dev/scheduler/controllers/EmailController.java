package dev.scheduler.controllers;

import antlr.build.Tool;
import dev.scheduler.entities.auth.AuthUserEntity;
import dev.scheduler.repos.AuthUserRepo;
import dev.scheduler.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "emails")

public class EmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private AuthUserRepo authUserRepo;

    @GetMapping(value = "/text/{userID}")
    public void sendTextEmail(/*@RequestBody AuthUserEntity authUser*/ @PathVariable("userID") Long userID) {
      emailService.sendTextEmail(authUserRepo.findById(userID).get());
      System.out.println("Sent");
    }

}