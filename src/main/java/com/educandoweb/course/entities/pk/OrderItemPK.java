package com.educandoweb.course.entities.pk;

import java.io.Serializable;
import java.util.Objects;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.Product;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable // A classe pode ser incorporada em outras entidades - permite uma representaçao de chave primária composta
public class OrderItemPK implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ManyToOne // Relação muitos pra um com a entidade Order
	@JoinColumn(name = "order_id") // Indica o nome da coluna a qual se associa a relação
	private Order order;
	
	@ManyToOne // Relação muitos pra um com a entidade Product
	@JoinColumn(name = "product_id") // Indica o nome da coluna a qual se associa a relação
	private Product product;
	
	// Getters e Setters
	public Order getOrder() {
		return order;
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(order, product);
	}
	
    // Métodos equals e hashCode para comparar instâncias de OrderItemPK com base no order e product
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItemPK other = (OrderItemPK) obj;
		return Objects.equals(order, other.order) && Objects.equals(product, other.product);
	}
	
}
