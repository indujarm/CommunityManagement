package com.example.module2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient  // Enables Module 2 to register with Eureka// Enables the module to register with Eureka
public class Module2Application {
	public static void main(String[] args) {
		SpringApplication.run(Module2Application.class, args);
		System.out.println("Module2 Application started...");
	}
}
