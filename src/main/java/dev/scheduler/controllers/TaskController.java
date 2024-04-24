package dev.scheduler.controllers;

import dev.scheduler.entities.TaskEntity;
import dev.scheduler.entities.auth.AuthUserEntity;
import dev.scheduler.repos.AuthUserRepo;
import dev.scheduler.repos.TaskRepo;
import javafx.concurrent.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("api/task")
public class TaskController {
    private Long counter = 0L;
    private Long generateId(){return counter++;}
    @Autowired
    private TaskRepo taskrepository;
    @Autowired
    private AuthUserRepo authUserRepo;

    @PostMapping(value = "task", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public TaskEntity createTask(@RequestBody TaskEntity task){return addTask(task);}
    private TaskEntity addTask(TaskEntity task) {
        task.setId(generateId());
        taskrepository.save(task);
        return task;
    }

    @PutMapping(value = "task/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public TaskEntity changeTask(@RequestBody TaskEntity changingTask){
        return updateTask(changingTask);
    }
    private TaskEntity updateTask(TaskEntity task){
        if(task.getId()==null){
            throw new RuntimeException("id of changing task cannot be null");
        }
        taskrepository.save(task);
        return task;
    }

    @DeleteMapping(value = "task/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Long deleteTask(@PathVariable("id") Long id){
        return removeTask(id);
    }
    private Long removeTask(Long id){
        taskrepository.deleteById(id);
        return id;
    }

    @GetMapping(value = "task/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public TaskEntity findTask(@PathVariable("id") Long id){
        return getTaskById(id);
    }

    private TaskEntity getTaskById(Long id){
        return taskrepository.findById(id).get();
    }

    @GetMapping("tasks")
    @Query("")
    public Iterable<TaskEntity> getAllTasks(){
        return taskrepository.findAll();
    }

    @GetMapping(value = "untask/{userID}")
    public List<TaskEntity> unfinishedTasks(@PathVariable("userID") Long id) {
        AuthUserEntity authUser = authUserRepo.findById(id).get();
        return taskrepository.findAllByConditionAndUserID(false, authUser);
    }
}
//