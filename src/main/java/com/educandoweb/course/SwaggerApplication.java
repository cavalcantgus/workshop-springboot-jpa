package com.educandoweb.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Swagger OpenAPI", version = "1", description = "API desenvolvida para testes de OpenAPI"))
@EnableScheduling
public class SwaggerApplication {

	// Metódo de inicialização da aplicação 
	public static void main(String[] args) {
		SpringApplication.run(SwaggerApplication.class, args);
	}

}
