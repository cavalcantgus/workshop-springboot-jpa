package com.educandoweb.course.services.exceptions;

// Exceção personalizada para erros com operações no banco de dados
public class DatabaseException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	// Construtor recebendo uma mensagem de erro
	public DatabaseException(String msg) {
		super(msg);
	}
}
