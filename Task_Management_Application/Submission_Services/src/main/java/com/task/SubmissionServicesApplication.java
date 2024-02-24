package com.task;

import jakarta.persistence.Entity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SubmissionServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubmissionServicesApplication.class, args);
	}

}
