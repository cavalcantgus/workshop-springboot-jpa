<h1 align="center">Workshop Spring Boot with JPA / Hiberante</h1>

<h3 align="center">Um projeto de controle de pedidos com propósito de estudo</h3>

## Passo a passo para a implementação deste projeto
- Crie um projeto Spring Boot
- Implemente o modelo de domínio fornecido no tópico 'Modelo de Domínio'
- Estruture da forma correta as camadas lógicas e de negócios
- Configure e use um banco de dados em memória H2
- Enriqueça seu banco de dados em um profile de testes
- Instale e configure o PostmanAPI para o CRUD da aplicação - Create, Read, Update, Delete

## Modelo de domínio
<div align="center"> 
  
  ![image]([https://github.com/vinicius-je/workshop-spring-boot/assets/67986109/96aea3a5-d4b0-475c-8af1-6d2777ce9070](https://domainmodel.s3.amazonaws.com/256351112-96aea3a5-d4b0-475c-8af1-6d2777ce9070.png))
</div>

## Próximo passo
- Documentação com SwaggerAPI

## Stacks
- Java 17
- SpringBoot
- JPA
- H2 (test database)
- PostgreSQL
- Bean Validation
- Swagger
- SpringToolSuite4

## Como rodar este projeto
- Clone este repositório em um diretório de sua preferência e rode na sua máquina

  Funcionamento padrão da API - [localhost:8080](http://localhost:8080)
  Endpoints:
  [localhost:8080](http://localhost:8080/users) - User class
  [localhost:8080](http://localhost:8080/orders) - Order class
  [localhost:8080](http://localhost:8080/products) - Product class
  [localhost:8080](http://localhost:8080/categories) - Category class

  Link local do Swagerr [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)
