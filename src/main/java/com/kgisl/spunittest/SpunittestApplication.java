package com.kgisl.spunittest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@OpenAPIDefinition
public class SpunittestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpunittestApplication.class, args);
	}

}
