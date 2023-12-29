package com.educandoweb.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.services.CategoryService;

@RestController // A classe é um controlador REST
@RequestMapping(value = "/categories") // Define o endpoint /categories e mapeia o caminho para operações relacionadas
public class CategoryResource {
	
	@Autowired // Injeção de dependência automática (Spring)
	private CategoryService service;
	
	// Endpoint para recuperar todas as categorias
	@GetMapping
	public ResponseEntity<List<Category>> findAll(){
		List<Category> list = service.findAll(); // Chama o serviço para buscar todos as categorias
		return ResponseEntity.ok().body(list); // Retorna a resposta HTTP 200 OK com a lista de categorias no corpo da resposta
	}
	
	// Endpoint para recuperar categoria com base no ID
	@GetMapping(value = "/{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id){
		Category obj = service.findById(id); // Chama o serviço para uma categoria com base no ID
		return ResponseEntity.ok().body(obj);  // Retorna a resposta HTTP 200 OK com a categoria no corpo da resposta
	}
}
