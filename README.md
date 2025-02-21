# TechManage - API de Gerenciamento de Usuários

Este projeto consiste em uma API RESTful desenvolvida com Spring Boot para gerenciar usuários, atendendo aos requisitos do desafio de desenvolvimento back-end proposto.

## Contexto do desafio

Você foi contratado para desenvolver a API de backend do sistema TechManage, uma aplicação para gerenciar usuários. O objetivo é criar uma API RESTful utilizando Spring Boot que permita realizar operações básicas de gerenciamento de usuários, conectando a aplicação a um banco de dados relacional.

## Objetivos do Desafio

- Criar uma API RESTful com Spring Boot que implemente as seguintes operações:
    - Criar um novo usuário.
    - Buscar todos os usuários.
    - Buscar um usuário por ID.
    - Atualizar as informações de um usuário existente.
    - Excluir um usuário.
- Conectar a aplicação a um banco de dados relacional de sua preferência (ex.: MySQL, PostgreSQL, H2, etc.).
- Implementar validações para os dados recebidos na API.
- Configurar o projeto para ser executado e testado localmente, com instruções neste README.md.
- Publicar o projeto em um repositório público no GitHub.

## Requisitos Técnicos Necessários

### Entidade `User`

- Atributos obrigatórios:
    - `id` (Long): Gerado automaticamente.
    - `fullName` (String): Nome completo do usuário.
    - `email` (String): Único, validado como um e-mail.
    - `phone` (String): Número de telefone no formato internacional (ex.: +55 11 99999-9999).
    - `birthDate` (Date): Data de nascimento.
    - `userType` (String): Enum com valores possíveis: `ADMIN`, `EDITOR`, `VIEWER`.

### Operações requisitadas REST

- `POST /api/users`: Adiciona um novo usuário.
    - O corpo da requisição deve conter os dados do usuário.
    - Deve retornar o usuário criado, incluindo o ID gerado.
- `GET /api/users`: Retorna todos os usuários.
- `GET /api/users/{id}`: Retorna os dados de um usuário específico.
    - Deve retornar erro HTTP 404 caso o ID não exista.
- `PUT /api/users/{id}`: Atualiza os dados de um usuário.
    - Deve validar os campos enviados.
- `DELETE /api/users/{id}`: Exclui um usuário.
    - Deve retornar erro HTTP 404 caso o ID não exista.

## Aplicação do Desafio

### Banco de Dados utilizado

**Bando de Dados relacional: Postgresql**
- Banco: desafio_ibm
- Usuário: user_desafio
- Senha: senha_desafio
- Script de banco é gerenciado pelo flyway migrations

### Testes unitários

- Foram criados testes unitário para as classes de serviços e um testes de integração para uma API.
- Crie ao menos um teste de integração para os endpoints da API.


    -- **Biblioteca de Testes:**
        - Utilizado banco de integração
        - Ferramentas:
            - mockito/junit5

## Como Executar o Projeto Localmente

1.  **Pré-requisitos:**
    -   Java 17 ou superior
    -   Maven
    -   Banco de dados relacional (configurado no `application.properties`)

2.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/mluizsa/desafio-ibm.git](https://github.com/mluizsa/desafio-ibm.git)
    cd desafio-ibm
    ```
3.  **Configure o banco de dados:**
    -   Crie um banco de dados com o nome `desafio_ibm`.
    -   Crie um banco de dados com o nome `desafio_ibm_integracao`.

4.  **Execute a aplicação:**
    -   Com Maven: `mvn spring-boot:run`
    

5.  **Acesse a API:**
    -   A API estará disponível em `http://localhost:8080/`.
    -   Utilizei insominia durante o processo de desenvolvimento
    **links**
      - GET: http://localhost:8080/api/users/search (param="param")
      - GET: http://localhost:8080/api/users/{id}
      - GET: http://localhost:8080/api/users
      - POST: http://localhost:8080/api/users 
         ```bash{
        
          "fullName": "string",
          "email": "string",
          "phone": "string",
          "birthDate": "yyyy-mm-dd",
          "userType": "string"
          }
        ````
      - PUT: http://localhost:8080/api/users/{id}
        ```bash{
        
        "fullName": "string",
        "email": "string",
        "phone": "string",
        "birthDate": "yyyy-mm-dd",
        "userType": "string"
        }
        ````
      - DELETE: http://localhost:8080/api/users/{id}