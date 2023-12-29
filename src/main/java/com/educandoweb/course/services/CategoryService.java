package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.repositories.CategoryRepository;

@Service // A classe é um componente de serviço gerenciado pelo Spring para operações com a entidade Category
public class CategoryService {
	
	@Autowired // Injeção de dependência automâtica (Spring)
	private CategoryRepository repository; // Responsável por interagir com o banco de dados
	
	// Retorna uma lista de todas as categorias 
	public List<Category> findAll(){
		return repository.findAll();
	}
	
	// Retorna uma categoria com base no ID
	public Category findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		return obj.get();
	}
}
