package com.educandoweb.course.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity // Indica que a classe é uma entidade JPA
@Table(name = "tb_product") // Especifica o nome da tabela no banco de dados
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id // Indica que a variável id é a chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Estratégia de geração de valores para a chave primária
	private Long id;
	private String name;
	private String description;
	private Double price;
	private String imgUrl;
 
	@ManyToMany // Relação muitos para muitos entre Product e Category
	
	// Especifica a tabela de associação entre Product e Category que será criada no banco de dados
	@JoinTable(name = "tb_product_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories = new HashSet<>();
	
	@OneToMany(mappedBy = "id.product") /* Relação um para muitos entre Product e OrderItem - mappedBy define
	o nome do atributo na entidade OrderItem que se relaciona com Product, criando a chave estrangeira "product_id" */
	
	private Set<OrderItem> itens = new HashSet<>();
	
	// Construtor padrão
	public Product() {
	}

	// Construtor com parâmetros
	public Product(Long id, String name, String description, Double price, String imgUrl) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	// Retorna um conjunto de pedidos associado a um produto
	public Set<Order> getOrders(){
		Set<Order> set = new HashSet<>();
		for(OrderItem x : itens) {
			set.add(x.getOrder());
		}
		return set;
	}
	
    // Métodos equals e hashCode para comparar instâncias de Product com base no id
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
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}

}
