package com.educandoweb.course.resources.exceptions;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

// Classe padrão para mensagens de erro em respostas HTTP
public class StandardError implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// Define o formato da data exibida no timestamp das requisições que resultam em erro
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant timestamp; // Momento do erro ocorrido
	private Integer status; // Status code HTTP do erro
	private String error; // Tipo do erro
	private String message; // Mensagem do erro
	private String path; // Caminho da requisição que resultou no erro
	
	// Construtor padrão
	public StandardError() {}

	// Construtor com parâmetros
	public StandardError(Instant timestamp, Integer status, String error, String message, String path) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	// Getters e Setters
	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
