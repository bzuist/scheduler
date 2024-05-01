package dev.scheduler;

import dev.scheduler.entities.TaskEntity;
import dev.scheduler.entities.auth.AuthUserEntity;
import dev.scheduler.entities.auth.BaseRole;
import dev.scheduler.entities.auth.RoleUserEntity;
import dev.scheduler.repos.AuthUserRepo;
import dev.scheduler.repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Collections;

public class Initializer {
    @Autowired
    private TaskRepo taskRepository;
    @Autowired
    private AuthUserRepo authUserRepo;
    public void initial() {

        AuthUserEntity admin = new AuthUserEntity(true, "admin", "1234",
                Collections.singleton(new RoleUserEntity("admin", BaseRole.SUPER_USER )),"bzuist@gmail.com"
        );

        authUserRepo.save(admin);

        AuthUserEntity user1 = new AuthUserEntity(true, "user1", "1209",
                Collections.singleton(new RoleUserEntity("user1", BaseRole.USER)), "bzuist@gmail.com"
        );
        authUserRepo.save(user1);

        TaskEntity test = new TaskEntity(1L, "test", "test", LocalDate.now(), false);
        taskRepository.save(test);
}
}
