package dev.scheduler.services;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

import dev.scheduler.entities.TaskEntity;
import dev.scheduler.repos.AuthUserRepo;
import dev.scheduler.repos.TaskRepo;
import dev.scheduler.entities.auth.AuthUserEntity;
import dev.scheduler.services.EmailService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor

public class ScheduledTask {

    private final TaskRepo repository;
    private EmailService emailService;
    private AuthUserEntity authUser;

    private AuthUserRepo authUserRepo;

    @Scheduled(fixedRate = 5000)
    public void checkAndSend(AuthUserEntity authUser) {

        //todo jpql
        List<TaskEntity> entities = repository.findAllByConditionAndUserID(false, authUser);
        entities.forEach(taskEntity -> {


            if (LocalDate.now().isAfter(taskEntity.getDate().toLocalDate()) || LocalDate.now().isEqual(taskEntity.getDate().toLocalDate())) {
                emailService.sendTextEmail(authUser);
            }

        });
    }







//    @Scheduled(fixedRate = 5000)
//    public void reportCurrentTime(AuthUserEntity authUser) {
//        List<TaskEntity> entities = repository.findAllByConditionAndUserID(false, authUser);
//        entities.forEach(taskEntity -> {
//
//            Set<AuthUserEntity> allUsers = authUserRepo.findAll();
//
//
//            if (LocalDate.now().isAfter(taskEntity.getDate()) || LocalDate.now().isEqual(taskEntity.getDate())) {
//            emailService.sendTextEmail(authUser);
//            }
//
//        });
//    }
}