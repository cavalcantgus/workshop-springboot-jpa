package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Product;
import com.educandoweb.course.repositories.ProductRepository;

@Service // A classe é um componente de serviço gerenciado pelo Spring para operações com a entidade Product
public class ProductService {

	@Autowired // Injeção de dependência automâtica (Spring)
	private ProductRepository repository; // Responsável por interagir com o banco de dados

	// Retorna uma lista de todos os produtos
	public List<Product> findAll() {
		return repository.findAll();
	}

	// Retorna um produto com base no ID
	public Product findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		return obj.get();
	}
}
