package com.educandoweb.course.resources;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController // A classe é um controlador REST
@RequestMapping(value = "/categories") // Define o endpoint /categories e mapeia o caminho para operações relacionadas
public class CategoryResource {

	@Autowired // Injeção de dependência automática (Spring)
	private CategoryService service;

	// Endpoint para recuperar todas as categorias
	@Operation(summary = "Recupera todos as categorias", description = "Obtenha uma lista com todos as categorias. A resposta será a categoria com ID e nome")
	@GetMapping
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Categorias encontradas", content = {
					@Content(schema = @Schema(implementation = User.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", description = "Nenhuma categoria encontrada.", content = {
					@Content(schema = @Schema()) }) })
	public ResponseEntity<List<Category>> findAll() {
		List<Category> list = service.findAll(); // Chama o serviço para buscar todos as categorias
		return ResponseEntity.ok().body(list); // Retorna a resposta HTTP 200 OK com a lista de categorias no corpo da
												// resposta
	}

	// Endpoint para recuperar categoria com base no ID
	@Operation(summary = "Recupera uma categoria com base no ID", description = "Obtenha uma categoria passando um ID. A resposta será a categoria com ID e nome")
	@GetMapping(value = "/{id}")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Categoria encontrada", content = {
					@Content(schema = @Schema(implementation = User.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", description = "Nenhuma categoria com esse ID encontrada", content = {
					@Content(schema = @Schema()) }) })
	public ResponseEntity<Category> findById(@PathVariable Long id) {
		try {
			Category obj = service.findById(id); // Chama o serviço para buscar um produto com base no ID
			return ResponseEntity.ok().body(obj); // Retorna a resposta HTTP 200 OK com o produto no corpo da resposta
		}
		catch(NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
