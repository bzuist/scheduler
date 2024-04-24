package dev.scheduler.repos;

import dev.scheduler.entities.TaskEntity;
import dev.scheduler.entities.auth.AuthUserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.config.Task;

import java.util.List;

public interface TaskRepo extends CrudRepository<TaskEntity, Long> {
    public List<TaskEntity> findAllByConditionAndUserID(boolean condition, AuthUserEntity authUser);
}
