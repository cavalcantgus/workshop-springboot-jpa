package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.OrderItem;
/* 
 * Estende JpaRepository, com métodos prontos para operações CRUD. Permite que o Spring injete 
 * automaticamente um objeto OrderItemRepository  facilitando a interação com o banco de dados.
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{
	
}
