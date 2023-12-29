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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity // Indica que a classe é uma entidade JPA
@Table(name = "tb_category") // Especifica o nome da tabela no banco de dados
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id // Indica que a variável id é a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Estratégia de geração de valores para a chave primária
    private Long id;

    private String name;

    @JsonIgnore // Evita a recursão infinita entre Category e Product ao serializar para JSON 
    @ManyToMany(mappedBy = "categories") // Relação muitos para muitos com a entidade Product - mappedBy especifica o nome do atributo na classe Product que mantém a relação
    private Set<Product> products = new HashSet<>();
    
    // Construtor Padrão
    public Category() {
    }

    // Construtor com parâmetros 
    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters e Setters dos atributos

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

    // Método para obter a lista de produtos associados a esta categoria
    public Set<Product> getProducts() {
        return products;
    }

    // Métodos equals e hashCode para comparar instâncias de Category com base no id
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Category other = (Category) obj;
        return Objects.equals(id, other.id);
    }
}
