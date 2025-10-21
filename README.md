# 🎓 Desafio Técnico UNIFOR - Arquitetura Hexagonal com Quarkus e Angular (V2)

<p align="center">
  <b>Desenvolvido por Anderson Fábio — Desenvolvedor Full Stack Java Sênior</b>
</p>

---

[⬅️ Navegar para README_V1](./README_V1.md)

```

---

## 📌 Sobre esta versão

Esta versão atualiza a documentação com as mudanças técnicas feitas após a entrega inicial:

- Banco de dados **MySQL** (substituindo PostgreSQL)
- **Swagger/OpenAPI** para testar a API
- Inclusão de **MapStruct** para mapeamento DTO ↔ Domain ↔ Entity
- Uso de **Lombok** para redução de boilerplate
- Testes unitários e integrados com **JUnit e Mockito**
- **Panache** e **Jakarta Persistence** para persistência e integração com Quarkus

---

## 🧱 Tecnologias e Conceitos Aplicados

- **Backend:** Quarkus (jdk-17, CDI, JAX-RS, Hibernate ORM, Panache, Flyway, Lombok, MapStruct, 
  Swagger/OpenAPI)
- **Frontend:** Angular 15+ (Standalone Components, Nginx containerizado)
- **Segurança:** Keycloak (OpenID Connect / RBAC)
- **Banco de Dados:** MySQL 8.1
- **Arquitetura:** Hexagonal / Clean Architecture
- **Infraestrutura:** Docker Compose
- **Boas Práticas:** Clean Code, SOLID, CQRS, DRY, CI/CD, DESIGN PATTERNS

---

## 🧩 Estrutura do Projeto

```text
desafio-unifor-hexagonal-quarkus-angular/
│
├─ backend/      
│   ├─ adapters/        
│   │   ├─ rest/        → Controllers com endpoints JAX-RS
│   │   ├─ repository/  → Implementações de persistência com Panache
│   │   └─ mapper/      → MapStruct DTO ↔ Domain ↔ Entity
│   ├─ application/    
│   │   └─ port/        → Repository Ports e Service Ports
│   ├─ domain/         
│   │   ├─ entities/    → Entidades e regras de negócio
│   │   └─ service/     → Serviços de domínio (lógica de negócio)
│   └─ infrastructure/  → Configurações de infra, DB, Keycloak, etc.
│
├─ frontend/     
├─ docker/       
├─ docs/         
└─ README.md
  
````

---

## 🔄 Estratégia de Branches (Gitflow)

- **main** → versão final do projeto (entrega oficial)  
- **stage** → branch de integração contínua  
- **feature/** → branches de desenvolvimento incremental  

---

## 🚀 Execução do Projeto

**Pré-requisitos:**
- Docker e Docker Compose instalados.

**Comando único para subir o ambiente:**

```bash
Após fazer clone no projeto, na pasta backend de um mvn clean install
docker-compose up -d
```

**Acessos:**


* **Frontend Angular:** [http://localhost:4200](http://localhost:4200)
* **Backend Quarkus + Swagger:** [http://localhost:8081/q/swagger-ui](http://localhost:8081/q/swagger-ui)
* **Keycloak:** [http://localhost:8080](http://localhost:8080)

> Nenhuma instalação local de Node, Maven ou Java é necessária — todo o ambiente é executado em containers Docker.

---

## 🧠 Objetivo Arquitetural

Fluxo do backend:

```
Controller (adapters/rest)
        ↓
Service / UseCase (adapters)
        ↓
Port (application/ports)
        ↓
Domain (entities)
```

* **Controllers:** Endpoints REST
* **Services/UseCases:** Regras de negócio ** Mantido em Application
* **Ports:** Interfaces para comunicação com repositories
* **Domain:** Entidades e regras puras

---

## ✍️ Autor

**Anderson Fábio**
Desenvolvedor Full Stack Java Sênior
📧 **[andersonfabio.1976@gmail.com](mailto:andersonfabio.1976@gmail.com)**
🔗 [LinkedIn](https://www.linkedin.com/in/andersonfabio1976) • 💻 [GitHub](https://github.com/andersonfabio)

---

[⬅️ Voltar para README_V1](./README_V1.md)

