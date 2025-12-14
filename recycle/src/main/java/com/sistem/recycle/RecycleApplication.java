package com.sistem.recycle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.sistem.recycle.Repositories")
public class RecycleApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecycleApplication.class, args);
	}

}
