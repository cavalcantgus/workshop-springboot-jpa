package com.educandoweb.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.services.OrderService;

@RestController // A classe é um controlador REST
@RequestMapping(value = "/orders") // Define o endpoint /orders e mapeia o caminho para operações relacionadas
public class OrderResource {
	
	@Autowired // Injeção de dependência automática (Spring)
	private OrderService service;
	
	// Endpoint para recuperar todos os pedidos
	@GetMapping
	public ResponseEntity<List<Order>> findAll(){
		List<Order> list = service.findAll(); // Chama o serviço para buscar todos os pedidos
		return ResponseEntity.ok().body(list); // Retorna a resposta HTTP 200 OK com a lista de pedidos no corpo da resposta
	}
	
	// Endpoint para recuperar pedidos com base no ID
	@GetMapping(value = "/{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id){
		Order obj = service.findById(id); // Chama o serviço para buscar um pedido com base no ID
		return ResponseEntity.ok().body(obj); // Retorna a resposta HTTP 200 OK com o pedido no corpo da resposta
	}
}
