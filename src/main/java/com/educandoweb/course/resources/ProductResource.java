package com.educandoweb.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.Product;
import com.educandoweb.course.services.ProductService;

@RestController // A classe é um controlador REST
@RequestMapping(value = "/products") // Define o endpoint /products e mapeia o caminho para operações relacionadas
public class ProductResource {
	
	@Autowired // Injeção de dependência automática (Spring)
	private ProductService service;
	
	// Endpoint para recuperar todos os produtos
	@GetMapping
	public ResponseEntity<List<Product>> findAll(){
		List<Product> list = service.findAll(); // Chama o serviço para buscar todos os produtos
		return ResponseEntity.ok().body(list); // Retorna a resposta HTTP 200 OK com a lista de produtos no corpo da resposta
	}
	
	// Endpoint para recuperar produtos com base no ID
	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id){
		Product obj = service.findById(id); // Chama o serviço para buscar um produto com base no ID
		return ResponseEntity.ok().body(obj); // Retorna a resposta HTTP 200 OK com o produto no corpo da resposta
	}
}
