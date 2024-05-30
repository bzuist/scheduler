package dev.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import dev.scheduler.Initializer;

@EnableScheduling
@SpringBootApplication

public class SchedulerApplication {
	private static Initializer initial;
	@Autowired
	public void setInitialLoader (Initializer initializer){
		SchedulerApplication.initial = initializer;
	}

	public static void main(String[] args) {
		SpringApplication.run(SchedulerApplication.class, args);
		initial.initializer();
	}

}
