package dev.scheduler.entities;
import dev.scheduler.entities.auth.AuthUserEntity;
import javafx.concurrent.Task;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Tasks")
@Getter
@Setter
public class TaskEntity {
    public TaskEntity(){}
    public TaskEntity(Long id, String name, String text, LocalDateTime date, Boolean condition, AuthUserEntity userID)
    {
        this.id = id;
        this.name = name;
        this.text = text;
        this.date = date;
        this.condition = condition;
        this.userID = userID;
    }
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="user_id", updatable=true, referencedColumnName = "id")
    private AuthUserEntity userID;

    private String description;

    private String text;

    private LocalDateTime date;

    private Boolean condition;

}
