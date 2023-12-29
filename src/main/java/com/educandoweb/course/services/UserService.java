package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service // A classe é um componente de serviço gerenciado pelo Spring para operações com a entidade Category
public class UserService {
	
	@Autowired // Injeção de dependência automâtica (Spring)
	private UserRepository repository; // Responsável por interagir com o banco de dados
	
	// Retorna uma lista de todos os usuários 
	public List<User> findAll(){
		return repository.findAll();
	}
	
	// Retorna um usuário com base no ID
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	// Deleta um usuário com base no ID
	public void delete(Long id) {
		try {
			// Verifica a existência de um determinado id no banco de dados, caso exista, é feita a deleção
			if (repository.existsById(id)) {
	            repository.deleteById(id); 		
	            
	        } else { 
	        	
	        	/* Se não existir, captura uma exceção e lança um ResourceNotFound, retornando a 
	        	resposta HTTP com o Status code 404*/
	        	
	            throw new ResourceNotFoundException(id);			
	        }
			
		} catch(DataIntegrityViolationException e) {
			
			/* Captura exceções relacionadas a violação de integridade do banco de dados, como deletar um usuário
			referenciado em uma chave estrangeira, e lança um DatabaseExcpetion*/
			
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public User update(Long id, User obj) {
		try {
			User entity = repository.getReferenceById(id); // Recupera uma referência User pelo ID
			updateData(entity, obj); // Atualiza o objeto User com base nos dados fornecidos em 'obj'
			return repository.save(entity); // Salva as alterações no banco de dados

		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id); // Lança uma exceção caso o objeto User em questão não seja encontrado

		}
	}

	// Método para atualizar os dados do usuário
	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
}
