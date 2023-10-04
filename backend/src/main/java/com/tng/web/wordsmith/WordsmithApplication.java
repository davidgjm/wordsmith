package com.tng.web.wordsmith;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WordsmithApplication {

	public static void main(String[] args) {
		SpringApplication.run(WordsmithApplication.class, args);
	}

}
