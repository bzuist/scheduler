package dev.scheduler;

import dev.scheduler.entities.TaskEntity;
import dev.scheduler.entities.auth.AuthUserEntity;
import dev.scheduler.entities.auth.BaseRole;
import dev.scheduler.entities.auth.RoleUserEntity;
import dev.scheduler.repos.AuthUserRepo;
import dev.scheduler.repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class Initializer {
    @Autowired
    private TaskRepo taskRepository;
    @Autowired
    private AuthUserRepo authUserRepo;
    public void initializer() {

        AuthUserEntity admin = new AuthUserEntity(true, "admin", "1234",
                Collections.singleton(new RoleUserEntity("admin", BaseRole.SUPER_USER )),"bzuist@gmail.com"
        );

        AuthUserEntity save = authUserRepo.save(admin);


        AuthUserEntity user1 = new AuthUserEntity(true, "user1", "1209",
                Collections.singleton(new RoleUserEntity("user1", BaseRole.USER)), "bzuist@gmail.com"
        );
        authUserRepo.save(user1);
        long id1 = user1.getId();
        LocalDateTime date1 = LocalDateTime.of(2014, 9, 19, 14, 5, 20);

        TaskEntity task1 = new TaskEntity(1L, "test", "test", date1, false, user1);
        taskRepository.save(task1);

        List<TaskEntity> list = StreamSupport.stream(taskRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        System.out.println(authUserRepo.findAll());

}
}
