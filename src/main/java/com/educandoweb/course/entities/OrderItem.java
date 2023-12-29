package com.educandoweb.course.entities;

import java.io.Serializable;
import java.util.Objects;

import com.educandoweb.course.entities.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity // Indica que a classe é uma entidade JPA
@Table(name = "tb_order_item") // Especifica o nome da tabela no banco de dados
public class OrderItem implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId // Define id como chave composta da minha entidade
	private OrderItemPK id = new OrderItemPK();
	private Integer quantity;
	private Double price;
	
	// Construtor padrão
	public OrderItem() {}

	// Construtor com parâmetros
	public OrderItem(Order order, Product product, Integer quantity, Double price) {
		super();
		id.setOrder(order);
		id.setProduct(product);
		this.quantity = quantity;
		this.price = price;
	}
	
	// Método para obter o pedido associado ao item
	@JsonIgnore
	public Order getOrder() {
		return id.getOrder();
	}

	// Método para definir o pedido associado ao item
	public void setOrder(Order order) {
		id.setOrder(order);
	}
	
	// Método para obter o produto associado ao item
	@JsonIgnore
	public Product getProduct() {
	    return id.getProduct();
	}

	// Método para definir o produto associado ao item
	public void setProduct(Product product) {
	    id.setProduct(product);
	}

	// Getters e Setter
	public Integer getQuantity() {
	    return quantity;
	}

	public void setQuantity(Integer quantity) {
	    this.quantity = quantity;
	}

	// Método para obter o preço do produto
	public Double getPrice() {
	    return price;
	}

	public void setPrice(Double price) {
	    this.price = price;
	}

	// Método para calcular o subtotal do item
	public Double getSubTotal() {
	    return price * quantity;
	}
	
    // Métodos equals e hashCode para comparar instâncias de OrderItem com base no id
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		return Objects.equals(id, other.id);
	}

}
