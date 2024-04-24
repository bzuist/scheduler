package dev.scheduler.controllers;

import dev.scheduler.classes.ShortUser;
import dev.scheduler.entities.auth.BaseRole;
import dev.scheduler.repos.AuthUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/home")

public class HomeController {
    @Autowired
    private AuthUserRepo authUserRepo;

    @GetMapping("users")
    public Set<ShortUser> getAllUsers() {
        return authUserRepo.findAll().stream()
                .filter(el -> el.getRoles().stream().anyMatch(role ->
                        role.getRole().equals(BaseRole.USER)))
                .map(el -> new ShortUser(el.getId(), el.getUsername()))
                .collect(Collectors.toSet());
    }
}