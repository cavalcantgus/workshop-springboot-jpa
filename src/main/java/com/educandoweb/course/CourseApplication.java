package com.educandoweb.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(title = "Swagger OpenAPI", version = "1.0.0", description = "Documentação do projeto Web-Services com SpringBoot e JPA / Hibernate"))
@EnableScheduling
@SpringBootApplication
public class CourseApplication {

	// Metódo de inicialização da aplicação 
	public static void main(String[] args) {
		SpringApplication.run(CourseApplication.class, args);
	}

}
