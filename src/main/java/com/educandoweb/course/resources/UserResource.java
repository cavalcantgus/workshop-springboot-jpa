package com.educandoweb.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController // A classe é um controlador REST
@RequestMapping(value = "/users") // Define o endpoint /users e mapeia o caminho para operações relacionadas
@Tag(name = "Usuários", description = "Operações relacionadas a usuários")
public class UserResource {
	
	@Autowired // Injeção de dependência automática (Spring)
	private UserService service;
	
	// Endpoint para recuperar todos os usuários
    @Operation(summary = "Recuperar todos os usuários", description = "Este endpoint retorna todos os usuários.")
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	// Endpoint para recuperar usuários com base no ID
    @Operation(summary = "Recuperar usuário por ID", description = "Este endpoint retorna um usuário com base no ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
	@GetMapping(value = "/{id}")	
	public ResponseEntity<User> findById(@PathVariable Long id){
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	// Endpoint para inserir novo usuário
    @Operation(summary = "Inserir novo usuário", description = "Este endpoint insere um novo usuário.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário inserido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Requisião inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj){
		obj = service.insert(obj); // Chama o serviço para inserir um usuário
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj); // Retorna a resposta HTTP 201 Created com o corpo do novo usuário e o cabeçalho 
    
	}
	
    @Operation(summary = "Deletar usuário por ID", description = "Este endpoint deleta um usuário com base no ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id); // Chama o serviço para deletar um usuário
		return ResponseEntity.noContent().build(); // Retorna a resposta HTTP 204 No Content indicando sucesso sem conteúdo no corpo da resposta
	}
	
	// Endpoint para atualizar usuário com base no ID
    @Operation(summary = "Atualizar usuário por ID", description = "Este endpoint atualiza um usuário com base no ID.")
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj){
		obj = service.update(id, obj); // Chama o serviço para atualizar um usuário
		return ResponseEntity.ok().body(obj);  // Retorna a resposta HTTP 200 OK com o corpo do usuário atualizado
	}
}
