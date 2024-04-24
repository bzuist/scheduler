package dev.scheduler.repos;

import dev.scheduler.entities.auth.AuthUserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository

public interface AuthUserRepo extends CrudRepository<AuthUserEntity, Long> {
    public Set<AuthUserEntity> findAll();
}
