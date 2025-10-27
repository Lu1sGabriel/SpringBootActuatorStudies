# Spring Boot Actuator Studies

Projeto de estudos desenvolvido para explorar o uso do **Spring Boot Actuator** em conjunto com o **Prometheus** e o **Grafana** para monitoramento de aplicações Java.  
O projeto também utiliza **PostgreSQL** como banco de dados e é totalmente **containerizado com Docker Compose**.

---

## 🧩 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.5.7**
  - Spring Web
  - Spring Data JPA
  - Spring Validation
  - Spring Boot Actuator
- **Micrometer + Prometheus Registry**
- **PostgreSQL**
- **Grafana**
- **Docker & Docker Compose**
- **Lombok**

---

## ⚙️ Funcionalidades Principais

### 🔹 API de Produtos e Avaliações
A aplicação implementa duas entidades principais para fins de teste:

- **Products**  
  - Criar produto  
  - Buscar produto por ID  
  - Listar todos os produtos (incluindo avaliações e média das notas)

- **Ratings**  
  - Criar avaliação  
  - Listar todas as avaliações

Essas rotas servem como base para coleta de métricas via Actuator.

---

### 🔹 Monitoramento e Observabilidade

O projeto utiliza o **Spring Boot Actuator** para expor métricas da aplicação em `/actuator/prometheus`.  
Esses dados são coletados pelo **Prometheus** e exibidos graficamente no **Grafana**.

Endpoints habilitados:
- `/actuator/health`
- `/actuator/httpexchanges`
- `/actuator/prometheus`

#### Indicadores customizados

- **`InternetHealthIndicator`**  
  Verifica a conectividade com a internet (por exemplo, testando acesso ao domínio do Google).

- **`HttpTraceRequestFilter`**  
  Registra as requisições HTTP, ignorando rotas como Swagger, API Docs e o próprio Actuator.

---

## 🐳 Arquitetura com Docker Compose

O ambiente é composto por quatro serviços principais:

| Serviço     | Descrição | Porta |
|--------------|------------|--------|
| **postgres** | Banco de dados PostgreSQL | 5432 |
| **api** | Aplicação Spring Boot | 8080 |
| **prometheus** | Coleta e armazena métricas da aplicação | 9090 |
| **grafana** | Dashboard para visualização das métricas | 3000 |

Volumes persistentes são utilizados para manter dados de cada serviço.

---

## 📊 Configuração do Prometheus

O arquivo `prometheus.yml` define a coleta das métricas da aplicação:

```yaml
global:
  scrape_interval: 5s

scrape_configs:
  - job_name: "products-api-job"
    metrics_path: "/actuator/prometheus"
    static_configs:
      - targets: ["api:8080"]
        labels:
          application: "product-api"
````

---

## 🚀 Execução do Projeto

### Pré-requisitos

* **Docker** e **Docker Compose** instalados
* (Opcional) **Maven** e **JDK 21**, caso queira rodar localmente sem containers

### Passos

1. Clone o repositório:

   ```bash
   git clone https://github.com/Lu1sGabriel/SpringBootActuatorStudies.git
   cd SpringBootActuatorStudies
   ```

2. Suba os containers:

   ```bash
   docker compose up --build
   ```

3. Acesse os serviços:

    * API: [http://localhost:8080](http://localhost:8080)
    * Prometheus: [http://localhost:9090](http://localhost:9090)
    * Grafana: [http://localhost:3000](http://localhost:3000)

---

## 🧠 Observações Técnicas

* O **Prometheus** coleta métricas diretamente da API via endpoint `/actuator/prometheus`.
* O **Grafana** pode ser configurado para visualizar essas métricas adicionando o Prometheus como **Data Source**.
* A configuração do banco de dados é feita automaticamente via variáveis de ambiente no `docker-compose.yml`.
* O projeto utiliza **`InMemoryHttpExchangeRepository`** para armazenar temporariamente as trocas HTTP monitoradas.

---

## 🧾 Estrutura do Projeto

```
SpringBootActuatorStudies/
├── src/
│   ├── main/
│   │   ├── java/dev/luis/goes/spring/actuator/studies/
│   │   │   ├── monitoring/
│   │   │   │   ├── config/
│   │   │   │   │   ├── HttpTraceConfiguration.java
│   │   │   │   │   └── HttpTraceRequestFilter.java
│   │   │   │   └── indicator/
│   │   │   │       └── InternetHealthIndicator.java
│   │   │   ├── product/
│   │   │   │   ├── application/
│   │   │   │   ├── domain/
│   │   │   │   ├── infrastructure/
│   │   │   │   └── presentation/
│   │   │   └── rating/
│   │   │       ├── application/
│   │   │       ├── domain/
│   │   │       ├── infrastructure/
│   │   │       └── presentation/
│   │   └── resources/
│   │       ├── application.yml
│   │       └── templates/
├── config/
│   └── prometheus.yml
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md
```

---

## 📈 Possíveis Extensões Futuras

* Adicionar **Dashboards customizados** no Grafana para análise de performance e consumo de recursos.
* Incluir **alertas do Prometheus** (Alertmanager) para notificações.
* Adicionar **Swagger/OpenAPI** para documentação das rotas.
* Incluir testes automatizados de integração com **Testcontainers**.

---

## 👨‍💻 Autor

**Luis Goes** 

Estudante de Engenharia de Software  
Projeto criado para fins de aprendizado e experimentação com ferramentas de observabilidade em aplicações Spring Boot.