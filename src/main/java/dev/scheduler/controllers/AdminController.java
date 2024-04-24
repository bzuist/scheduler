package dev.scheduler.controllers;

import dev.scheduler.entities.auth.AuthUserEntity;
import dev.scheduler.repos.AuthUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

//@RestPoint
@RequestMapping("api/admin")
public class AdminController {
    @Autowired
    private AuthUserRepo authUserRepo;

    @GetMapping("users")
    public Set<AuthUserEntity> getAllUsers() {
        return authUserRepo.findAll();
    }

    @PostMapping(value="users", produces =
            MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AuthUserEntity createStudent(@RequestBody AuthUserEntity user) {
        return authUserRepo.save(user);
    }
}
