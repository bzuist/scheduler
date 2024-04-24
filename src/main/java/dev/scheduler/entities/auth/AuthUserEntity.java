package dev.scheduler.entities.auth;

import dev.scheduler.entities.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor

public class AuthUserEntity extends BaseEntity {
    static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private String username;
    private boolean enabled;
    private String password;
    private String email;
    @OneToMany(cascade={CascadeType.ALL},
            orphanRemoval=true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name="user_id", updatable=true)
    private Set<RoleUserEntity> roles;
    public AuthUserEntity(boolean enabled, String username, String password, Set roles, String email )
    {
        this.password = passwordEncoder.encode(password);
        this.enabled = enabled;
        this.username = username;
        this.roles = roles;
        this.email = email;
    }

}
