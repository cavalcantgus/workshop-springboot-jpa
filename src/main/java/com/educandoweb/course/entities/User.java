package com.educandoweb.course.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity // Indica que a classe é uma entidade JPA
@Table(name = "tb_user") // Especifica o nome da tabela no banco de dados
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id // Indica que a variável id é a chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Estratégia de geração de valores para a chave primária
	@Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Tutorial ID", example = "123")
	private Long id;
	
	@Schema(description = "Tutorial nome", example = "Marcelo")
	private String name;
	
	@Schema(description = "Tutorial email", example = "marcelo@gmail.com")
	private String email;
	
	@Schema(description = "Tutorial telefone", example = "98974585")
	private String phone;
	
	@Schema(description = "Tutorial senha", example = "124576")
	private String password;

	@JsonIgnore // Evita a recursão ao buscar pedidos associados
	/*
	 * Relacionamento um para muitos com a entidade Order - mappedBy especifica o
	 * nome do atributo na entidade Order que mantém a relação
	 */
	@OneToMany(mappedBy = "client") 
	private List<Order> orders = new ArrayList<>();

	// Construtor padrão
	public User() {
	}

	// Construtor com parâmetros
	public User(Long id, String name, String email, String phone, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	// Getters e Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Order> getOrders() {
		return orders;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	// Métodos equals e hashCode para comparar instâncias de User com base no id
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

}
