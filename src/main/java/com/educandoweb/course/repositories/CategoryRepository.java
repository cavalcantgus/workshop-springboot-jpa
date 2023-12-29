package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.Category;
/* 
 * Estende JpaRepository, com métodos prontos para operações CRUD. Permite que o Spring injete 
 * automaticamente um objeto CategoryRepository  facilitando a interação com o banco de dados.
 */
public interface CategoryRepository extends JpaRepository<Category, Long>{
	
}
