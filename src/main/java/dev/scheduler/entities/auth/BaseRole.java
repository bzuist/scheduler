package dev.scheduler.entities.auth;

import lombok.Getter;

@Getter
public enum BaseRole {
    SUPER_USER("ROLE_SUPER_USER", "SUPER_USER"),
    USER("ROLE_USER", "USER");
    private final String value;
    private final String role;
    private BaseRole(String value, String role) {
        this.value = value;
        this.role = role;
    }
}
