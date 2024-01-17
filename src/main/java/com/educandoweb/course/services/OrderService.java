package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.repositories.OrderRepository;

@Service // A classe é um componente de serviço gerenciado pelo Spring para operações com a entidade Order
public class OrderService {

	@Autowired // Injeção de dependência automâtica (Spring)
	private OrderRepository repository; // Responsável por interagir com o banco de dados

	// Retorna uma lista de todos os pedidos
	public List<Order> findAll() {
		return repository.findAll();
	}

	// Retorna um pedido com base no ID
	public Order findById(Long id) {
		Optional<Order> obj = repository.findById(id);
		return obj.get();
	}
}
