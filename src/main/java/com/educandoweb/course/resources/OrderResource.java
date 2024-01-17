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

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController // A classe é um controlador REST
@RequestMapping(value = "/orders") // Define o endpoint /orders e mapeia o caminho para operações relacionadas
public class OrderResource {

	@Autowired // Injeção de dependência automática (Spring)
	private OrderService service;

	// Endpoint para recuperar todos os pedidos
	@Operation(summary = "Recupera todos os pedidos", description = "Obtenha uma lista com todos os pedidos. A resposta será o ID do pedido,"
			+ "o momento em que foi feito, seu status, cliente, itens e pagamentos associados")
	@GetMapping
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Pedidos encontrados", content = {
					@Content(schema = @Schema(implementation = User.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", description = "Nenhum pedido encontrado.", content = {
					@Content(schema = @Schema()) }) })
	public ResponseEntity<List<Order>> findAll() {
		List<Order> list = service.findAll(); // Chama o serviço para buscar todos os pedidos
		return ResponseEntity.ok().body(list); // Retorna a resposta HTTP 200 OK com a lista de pedidos no corpo da
												// resposta
	}

	// Endpoint para recuperar pedidos com base no ID
	@Operation(summary = "Recupera um pedido com base no ID", description = "Obtenha um pedido passando um ID. A resposta será o ID do pedido,"
			+ "o momento em que foi feito, seu status, cliente, itens e pagamentos associados")
	@GetMapping(value = "/{id}")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Pedido encontrado", content = {
					@Content(schema = @Schema(implementation = User.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", description = "Nenhum pedido com esse ID encontrado", content = {
					@Content(schema = @Schema()) }) })
	public ResponseEntity<Order> findById(@PathVariable Long id) {
		try {
			Order obj = service.findById(id); // Chama o serviço para buscar um produto com base no ID
			return ResponseEntity.ok().body(obj); // Retorna a resposta HTTP 200 OK com o produto no corpo da resposta
		}
		catch(NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
