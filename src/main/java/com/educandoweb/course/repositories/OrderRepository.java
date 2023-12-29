package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.Order;

/* 
 * Estende JpaRepository, com métodos prontos para operações CRUD. Permite que o Spring injete 
 * automaticamente um objeto OrderRepository  facilitando a interação com o banco de dados.
 */
public interface OrderRepository extends JpaRepository<Order, Long>{
	
}
