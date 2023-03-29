# Descrição do Projeto

Projeto desenvolvido com Spring Boot 3.0.0 e Java 17 utilizando Spring Data JPA, Hibernate e Lombok. A aplicação é containerizada e utiliza o banco de dados Postgres. API documentada com OpenAPI

## API


A API desenvolvida possui os seguintes endpoints:

-   Criar, Excluir, Editar e Ler para as entidades: Equipamento, Estado de equipamento, Modelo de Equipamento, Ganhos por hora por estado, Histórico de posições de um equipamento e Histórico de estados de um equipamento.
-   Endpoint que retorna o estado mais recente dos equipamentos.
-   Endpoint que retorna a posição mais recente dos equipamentos.

## Como executar o projeto

Para executar o projeto, é necessário ter o Docker instalado. Após clonar o repositório, basta executar os seguintes comandos:



`docker-compose build
docker-compose up`

Banco de dados já é restaurado automaticamente assim que o docker for inicializado

## Como acessar a documentação da API

Após executar o projeto, a documentação da API pode ser acessada em `http://localhost:1337/aikoApi/swagger-ui/index.html#/`.

## Autor

Nome: Matheus Victor de Leon Grego

E-mail: matheusdeleongrego@gmail.com