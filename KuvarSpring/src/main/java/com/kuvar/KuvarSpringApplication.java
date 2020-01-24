package com.kuvar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EntityScan("model")
public class KuvarSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(KuvarSpringApplication.class, args);
	}

}
