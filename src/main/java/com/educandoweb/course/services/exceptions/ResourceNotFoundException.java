package com.educandoweb.course.services.exceptions;

// Exceção personalizada para Recurso não encontrado
public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	// Construtor recebendo ID do recurso ausente
	public ResourceNotFoundException(Object id) {
		super("Resource not found. Id " + id);
	}
}
