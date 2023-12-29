package com.educandoweb.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.educandoweb.course.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity // Indica que a classe é uma entidade JPA
@Table(name = "tb_order") // Especifica o nome da tabela no banco de dados
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id // Indica que a variável id é a chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Estratégia de geração de valores para a chave primária
	private Long id;

	// Define o formato da data exibida nas requisições Get e buscas no banco de dados
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT") 
	private Instant moment;
	private Integer orderStatus;
	
	@ManyToOne // Relação muitos para um com User
	@JoinColumn(name = "client_id") // Especifica coluna da tabela que mantém a relação, referenciando por 'client_id'
	private User client;

	@OneToMany(mappedBy = "id.order") /* Relação um para muitos com OrderItem mappedBy especifica o
									  nome do atributo na classe OrderItem que mantém a relação*/
	
	private Set<OrderItem> itens = new HashSet<>();
	
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL) // Indica uma relação um para um com a entidade Payment
	// mappedBy especifica o nome do atributo na classe Payment que mantém a relação
	
	private Payment payment;
	
	// Construtor padrão
	public Order() {
	}
	
	// Construtor com parâmetros
	public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
		super();
		this.id = id;
		this.moment = moment;
		setOrderStatus(orderStatus);
		this.client = client;
	}

	// Getter e Setter dos atributos
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

	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);
	}

	// Retorna o valor do code associado aos valores do tipo Enum OrderStatus
	public void setOrderStatus(OrderStatus orderStatus) {
		if(orderStatus != null) {
			this.orderStatus = orderStatus.getCode();

		}
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public Set<OrderItem> getItens(){
		return itens;
	}
	
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
    // Métodos equals e hashCode para comparar instâncias de Order com base no id
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	// Retorna o valor do cálculo do total de itens na classe OrderItem
	public Double getTotal() {
		double sum = 0;
		for(OrderItem x : itens) {
			sum += x.getSubTotal();
		}
		return sum;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}

}
