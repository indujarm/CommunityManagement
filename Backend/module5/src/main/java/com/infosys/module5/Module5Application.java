package com.infosys.module5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableFeignClients
public class Module5Application {
	public static void main(String[] args) {
		SpringApplication.run(Module5Application.class, args);
		System.out.println("Module 5 Started...");
	}
}
