# Project Task Hub API

Esta API foi desenvolvida para gerenciar **projetos** e **times**, permitindo o cadastro de usuários, criação de times, associação a projetos e controle de tarefas com status. O projeto está sendo desenvolvido como parte da prática e aplicação dos conhecimentos adquiridos nos cursos de **Java da Trybe** e da **DIO (Digital Innovation One)**.

---

##  Status do Projeto

🚧 Em desenvolvimento

---

##  Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Security**
- **JWT (JSON Web Token)** – Autenticação
- **JPA (Hibernate)**
- **PostgreSQL** – Banco de dados
- **Swagger/OpenAPI** – Documentação da API
- **Docker** – Containerização
- **Railway** – Deploy da aplicação

---

## Diagrama de Classes

<img src="https://github.com/user-attachments/assets/932aa42f-407a-42d6-9115-56c118fa7e07" alt="diagrama de classes" width=50% />

---

##  Funcionalidades Implementadas

✔️ Cadastro de usuários <br>
✔️ Login com autenticação JWT <br>
✔️ Criação de times <br>
✔️ Criação de projetos <br>
✔️ Associação de times aos projetos <br> 
✔️ Cadastro de tarefas com status (ABERTA, EM_ANDAMENTO, CONCLUIDA) <br>
✔️ Associação de responsáveis às tarefas <br>
✔️ Documentação com Swagger <br>
✔️ Deploy na Railway <br>

---

##  Documentação da API

Acesse a documentação interativa da API com o Swagger:

 [https://task-hub-production.up.railway.app/swagger-ui/index.html](https://task-hub-production.up.railway.app/swagger-ui/index.html)

---

##  Autenticação

Autenticação via **JWT (Bearer Token)**. Após efetuar login, inclua o token no cabeçalho `Authorization`: Bearer seu_token_aqui

---

## 🐳 Executando com Docker

Para rodar o projeto localmente com Docker:

```bash

docker-compose up --build

````

---

## Próximas Etapas
 
 -  Criar testes unitários e de integração

 - Melhorar tratamento de erros e mensagens de retorno

 - Implementar paginação e filtros nas listagens

---
