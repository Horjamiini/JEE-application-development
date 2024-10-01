package com.JEET4.springbootcrudrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringT4Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringT4Application.class, args);
	}

}
