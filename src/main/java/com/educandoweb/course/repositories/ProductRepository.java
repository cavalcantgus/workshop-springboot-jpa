package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.Product;
/* 
 * Estende JpaRepository, com métodos prontos para operações CRUD. Permite que o Spring injete 
 * automaticamente um objeto ProductRepository  facilitando a interação com o banco de dados.
 */
public interface ProductRepository extends JpaRepository<Product, Long>{
	
}
