# 🎓 Desafio Técnico UNIFOR - Arquitetura Hexagonal com Quarkus e Angular (V1)

<p align="center">
  <b>Desenvolvido por Anderson Fábio — Desenvolvedor Full Stack Java Sênior</b>
</p>

---

## 🧭 Sobre o Projeto

Este projeto foi desenvolvido como parte de um **desafio técnico proposto pela Universidade de Fortaleza (UNIFOR)** durante o processo de **contratação para a posição de Desenvolvedor Full Stack Java Sênior**.

O objetivo é demonstrar **proficiência em arquitetura hexagonal**, **boas práticas de engenharia de software** e o uso de **tecnologias modernas** em um ambiente corporativo **full stack**.

📄 [Clique aqui para acessar o documento oficial do desafio técnico da UNIFOR](./docs/DESAFIO_TECNICO_UNIFOR.pdf)

---

## 🧱 Tecnologias e Conceitos Aplicados

- **Backend:** Quarkus (jdk-17, CDI, JAX-RS, Hibernate ORM, Flyway, Lombok, RabbitMQ — *avaliação de uso*)  
- **Frontend:** Angular 15+ (Standalone Components, Nginx containerizado)  
- **Segurança:** Keycloak (OpenID Connect / RBAC)  
- **Banco de Dados:** PostgreSQL  
- **Arquitetura:** Hexagonal / Clean Architecture  
- **Infraestrutura:** Docker Compose  
- **Boas Práticas:** Clean Code, SOLID, CQRS, DRY, CI/CD, DESIGN PATTERNS

---

## 🧩 Estrutura do Projeto

```

desafio-unifor-hexagonal-quarkus-angular/
│
├─ backend/      → Projeto Quarkus (arquitetura hexagonal/clean, módulos Maven)
├─ frontend/     → Aplicação Angular (Standalone Components, servida via Nginx)
├─ docker/       → Orquestração com PostgreSQL, Keycloak e Aplicações
├─ docs/         → Documentação e PDF do desafio técnico
└─ README.md     → Este documento

````

---

## 🔄 Estratégia de Branches (Gitflow)

- **main** → versão final do projeto (entrega oficial)  
- **stage** → branch de integração contínua  
- **feature/** → branches de desenvolvimento incremental  

---

## 🚀 Execução do Projeto

**Pré-requisitos:**

- Após fazer clone no projeto, na pasta backend de um mvn clean install
- Docker e Docker Compose instalados.

**Comando único para subir o ambiente:**
```bash
docker-compose up -d
````

**Acessos:**


* **Frontend Angular:** [http://localhost:4200](http://localhost:4200)
* **Backend Quarkus:** [http://localhost:8080](http://localhost:8080)
* **Keycloak:** [http://localhost:8080](http://localhost:8080)

> Nenhuma instalação local de Node, Maven ou Java é necessária — todo o ambiente é executado em containers Docker.

---

## ✍️ Autor

**Anderson Fábio**
Desenvolvedor Full Stack Java Sênior
📧 **[andersonfabio.1976@gmail.com](mailto:andersonfabio.1976@gmail.com)**
🔗 [LinkedIn](https://www.linkedin.com/in/andersonfabio1976) • 💻 [GitHub](https://github.com/andersonfabio)

---

[➡️ Ir para README_V2](./README.md)
