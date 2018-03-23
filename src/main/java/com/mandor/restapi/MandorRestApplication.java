package com.mandor.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MandorRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MandorRestApplication.class, args);
	}
}
