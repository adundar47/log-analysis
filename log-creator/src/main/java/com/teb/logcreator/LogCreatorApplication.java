package com.teb.logcreator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LogCreatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogCreatorApplication.class, args);
	}

}
