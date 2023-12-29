package com.educandoweb.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity // Indica que a classe é uma entidade JPA
@Table(name = "tb_payment") // Especifica o nome da tabela no banco de dados
public class Payment implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id // Indica que a variável id é a chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Estratégia de geração de valores para a chave primária
	private Long id;
	private Instant moment;
	
	@OneToOne // Indica uma relação um para um com a entidade Order
	@MapsId // Define que o valor desta variável é o id da entidade associada (Order)
	@JsonIgnore
	private Order order;

	// Construtor padrão
	public Payment() {}

	// Construtor com parâmetros
	public Payment(Long id, Instant moment, Order order) {
		super();
		this.id = id;
		this.moment = moment;
		this.order = order;
	}

	// Getters e Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

    // Métodos equals e hashCode para comparar instâncias de Payment com base no id
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		return Objects.equals(id, other.id);
	}
	
}	
