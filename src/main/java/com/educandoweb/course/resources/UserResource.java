package com.educandoweb.course.resources;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Usuários", description = "Controlador de usuários")
@RestController // A classe é um controlador REST
@RequestMapping(value = "/users") // Define o endpoint /users e mapeia o caminho para operações relacionadas
public class UserResource {

	@Autowired // Injeção de dependência automática (Spring)
	private UserService service;

	// Endpoint para recuperar todos os usuários
	@Operation(summary = "Recupera todos os usuários", description = "Obtenha uma lista com todos os usuários. A resposta será o usuário com ID, nome, email, telefone e senha")
	@GetMapping
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Usuários encontrados", content = {
					@Content(schema = @Schema(implementation = User.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", description = "Nenhum usuário encontrado.", content = {
					@Content(schema = @Schema()) }) })
	public ResponseEntity<List<User>> findAll() {
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	// Endpoint para recuperar usuários com base no ID
	@Operation(summary = "Recupera um usuário com base no ID", description = "Obtenha um usuário passando um ID. A resposta será o usuário com ID, nome, email, telefone e senha")
	@GetMapping(value = "/{id}")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Usuário encontrado", content = {
					@Content(schema = @Schema(implementation = User.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", description = "Nenhum usuário com esse ID encontrado", content = {
					@Content(schema = @Schema()) }) })
	public ResponseEntity<User> findById(@PathVariable Long id) {
		try {
			User obj = service.findById(id); // Chama o serviço para buscar um produto com base no ID
			return ResponseEntity.ok().body(obj); // Retorna a resposta HTTP 200 OK com o produto no corpo da resposta
		}
		catch(NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	// Endpoint para inserir novo usuário
	@Operation(summary = "Insere um novo usuário no banco de dados", description = "Insira um novo usuário passando seu nome, email telefone e senha")
	@PostMapping
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Usuário criado com sucesso", content = {
					@Content(schema = @Schema(implementation = User.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "Requisição inválida", content = {
					@Content(schema = @Schema()) }) })
	public ResponseEntity<User> insert(@RequestBody User obj) {
		obj = service.insert(obj); // Chama o serviço para inserir um usuário
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj); // Retorna a resposta HTTP 201 Created com o corpo do novo usuário
														// e o cabeçalho

	}

	// Endpoint para deletar usuário com base no ID
	@Operation(summary = "Deletar um usuário com base em seu ID", description = "Delete um usuário passando um ID como parâmetro")
	@DeleteMapping(value = "/{id}")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Usuário removido com sucesso"),
			@ApiResponse(responseCode = "404", description = "Nenhum usuário com esse ID encontrado", content = {
					@Content(schema = @Schema()) }) })
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id); // Chama o serviço para deletar um usuário
		return ResponseEntity.noContent().build(); // Retorna a resposta HTTP 204 No Content indicando sucesso sem
													// conteúdo no corpo da resposta
	}

	// Endpoint para atualizar usuário com base no ID
	@Operation(summary = "Atualiza um usuário com base em seu ID", description = "Atualize um usuário passando um ID como parâmetro")
	@PutMapping(value = "/{id}")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso", content = {
					@Content(schema = @Schema(implementation = User.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", description = "Nenhum usuário com esse ID encontrado", content = {
					@Content(schema = @Schema()) }) })
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj) {
		obj = service.update(id, obj); // Chama o serviço para atualizar um usuário
		return ResponseEntity.ok().body(obj); // Retorna a resposta HTTP 200 OK com o corpo do usuário atualizado
	}
}
