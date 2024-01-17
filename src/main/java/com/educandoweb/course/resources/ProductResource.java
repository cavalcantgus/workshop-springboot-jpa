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

import com.educandoweb.course.entities.Product;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController // A classe é um controlador REST
@RequestMapping(value = "/products") // Define o endpoint /products e mapeia o caminho para operações relacionadas
public class ProductResource {

	@Autowired // Injeção de dependência automática (Spring)
	private ProductService service;

	// Endpoint para recuperar todos os produtos
	@Operation(summary = "Recupera todos os produtos", description = "Obtenha uma lista com todos os produtos. A resposta será o produto"
			+ " com ID, nome, descrição, preço, Url da imagem, pedidos, ordem de itens e pagamentos associados")
	@GetMapping
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Produtos encontrados", content = {
					@Content(schema = @Schema(implementation = User.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", description = "Nenhum produto encontrado.", content = {
					@Content(schema = @Schema()) }) })
	public ResponseEntity<List<Product>> findAll() {
		List<Product> list = service.findAll(); // Chama o serviço para buscar todos os produtos
		return ResponseEntity.ok().body(list); // Retorna a resposta HTTP 200 OK com a lista de produtos no corpo da
												// resposta
	}

	// Endpoint para recuperar produtos com base no ID
	@Operation(summary = "Recupera um produto com base no ID", description = "Obtenha um produto passando um ID. A resposta será o produto"
			+ " com ID, nome, descrição, preço, Url da imagem, pedidos, ordem de itens e pagamentos associados")
	@GetMapping(value = "/{id}")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Produto encontrado", content = {
					@Content(schema = @Schema(implementation = User.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", description = "Nenhum produto com esse ID encontrado", content = {
					@Content(schema = @Schema()) }) })
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		try {
			Product obj = service.findById(id); // Chama o serviço para buscar um produto com base no ID
			return ResponseEntity.ok().body(obj); // Retorna a resposta HTTP 200 OK com o produto no corpo da resposta
		}
		catch(NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
