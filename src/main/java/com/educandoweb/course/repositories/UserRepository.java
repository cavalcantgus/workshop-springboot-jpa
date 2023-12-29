package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.User;
/* 
 * Estende JpaRepository, com métodos prontos para operações CRUD. Permite que o Spring injete 
 * automaticamente um objeto UserRepository  facilitando a interação com o banco de dados.
 */
public interface UserRepository extends JpaRepository<User, Long>{
	
}
