# E-Commerce Microservices

A simple e-commerce backend project built with Spring Boot and Maven multi-module architecture.

This repository focuses on the core backend flow of a basic e-commerce system:
- product management
- stock tracking
- buying/order creation
- shipment information
- buying status management

The project is split into independent modules to keep responsibilities separated and make the codebase easier to extend.

---

## Project Overview

This repository contains a small microservice-style backend for an e-commerce domain.

At a high level:

- **product-server** manages product data and stock information
- **product-client** provides shared DTO and exception classes used while communicating with the product domain
- **buying-service** manages buyings/orders, shipment data, item quantities, and buying status changes

The services are designed so that the buying side can communicate with the product side to:
- fetch product information
- validate stock availability
- update stock after buying operations

---

## Architecture

```text
e-commerce
├── pom.xml
├── buying-service
│   ├── src/main/java/com/siq/ecommerce/buyingservice
│   │   ├── controller
│   │   ├── dto
│   │   ├── enums
│   │   ├── model
│   │   ├── repository
│   │   ├── service
│   │   └── BuyingServiceApplication.java
│   ├── src/main/resources/application.properties
│   ├── Dockerfile
│   └── docker-compose.yml
└── product-service
    ├── pom.xml
    ├── product-client
    │   ├── src/main/java/com/siq/ecommerce/productclient
    │   │   ├── dto
    │   │   └── exception
    │   └── src/main/resources/application.properties
    └── product-server
        ├── src
        ├── Dockerfile
        └── src/main/resources/application.properties
```

## Modules

1. buying-service

This service is responsible for buying/order operations.

Main responsibilities:

create a new buying
create a buying from an existing shipment
list all buyings
get buying details and total cost
update product quantity in a buying
remove product from a buying
complete a buying
reject a buying
update shipment information
fetch shipment information by buying id

Base path:

```http
/v1/buying
```

Examples of supported routes from the source code:

```http
POST   /v1/buying
POST   /v1/buying/shipment/{shipmentId}
GET    /v1/buying
GET    /v1/buying/{id}
PUT    /v1/buying/{id}
DELETE /v1/buying/{id}
PUT    /v1/buying/status/{id}
DELETE /v1/buying/status/{id}
PUT    /v1/buying/shipment/{id}
GET    /v1/buying/shipment/{id}
```

The service also calculates total buying cost by summing:
product price x quantity

When a buying is rejected, product quantities are returned to stock.

Default port: 9090

---

2. product-server

This module is the product-side service.

Based on the code references from buying-service, it provides product endpoints that allow:

fetching a product by id
updating product stock

Referenced endpoints:

```http
GET /v1/product/{id}
PUT /v1/product/stock/{id}
```

Default port: 9291

---

3. product-client

This module contains shared structures used by other modules when communicating with the product domain.

It includes packages such as:

dto
exception

This keeps cross-module communication cleaner and avoids duplicating transport classes.

---

## Domain Model

**Buying**

Represents a buying/order record.

Important fields:

id
status
createdDate
shipment

Default status starts as: PENDING

**BuyingDetail**

Represents a product line inside a buying.

Important fields:

* id
* buyingId
* productId
* quantity

**Shipment**

Represents shipment information.

Important fields:

* id
* cityCode
* location

---

## Technology Stack

* Java 11
* Spring Boot 2.5.3
* Spring Web
* Spring WebFlux
* Spring Data JPA
* Spring Validation
* H2 Database
* Lombok
* Maven
* Docker

---

## How Services Communicate

buying-service uses a WebClient-based wrapper service to communicate with product-server.

This flow is used to:

fetch product details
check whether requested quantity is available
update stock after buying changes

Configured product service base URL in source:

http://localhost:9291

This means product-server should be running before or alongside buying-service for full flow testing.

---

## Getting Started

**Prerequisites**

Make sure you have installed:

Java 11
Maven
Docker (optional)

---

## Build the Project

From the root project directory:

```bash
./mvnw clean install
```
On Windows:

```bash
mvnw.cmd clean install
```

---

## Run the Services

Because this repository is modular, you will typically run the services separately.

Run product-server
```bash
cd e-commerce/product-service/product-server
../../mvnw spring-boot:run
```

On Windows:
```bash
cd e-commerce\product-service\product-server
..\..\mvnw.cmd spring-boot:run
```

Run buying-service
```bash
cd e-commerce/buying-service
../mvnw spring-boot:run
```

On Windows:
```bash
cd e-commerce\buying-service
..\mvnw.cmd spring-boot:run
```

---

## Default Ports

buying-service -> 9090
product-server -> 9291

---

## Docker Support

The repository includes Dockerfiles for at least:

buying-service
product-server

Example Dockerfile pattern used in the project:

```dockerfile
FROM openjdk:11
WORKDIR /app
ADD target/<jar-file>.jar <jar-file>.jar
ENTRYPOINT ["java", "-jar", "<jar-file>.jar"]
```

This makes the services easy to containerize after packaging.

Typical flow:

```bash
mvn clean package
docker build -t product-server .
docker build -t buying-service .
```

---

## Example Workflow

A typical business flow in this project looks like this:

A product exists with price and stock information
A buying is created
Shipment information is attached
Product quantities are validated against stock
Buying details are updated
Total cost is calculated
Buying is either:
completed
rejected

If rejected, product stock is restored.

---

## Why This Project Is Useful

This repository is a good learning project for:

Spring Boot multi-module structure
separating service responsibilities
basic microservice communication
DTO-based inter-module contracts
stock validation and order flow modeling
REST API design for simple commerce operations

It is especially useful as a backend portfolio project because it shows more than plain CRUD:

business state handling
inter-service communication
stock-sensitive operations
shipment/order relationships

---

## Possible Improvements

This project can be extended in many ways:

add API documentation with Swagger / OpenAPI
add integration tests
replace H2 with PostgreSQL or MySQL
introduce service discovery or API gateway
add authentication and authorization
add customer entity and payment flow
add product categories and search
add order history and event-driven updates
containerize all modules with a root docker-compose setup
add CI/CD pipeline

---

## Notes

This project is currently a backend-focused implementation and is best understood as a technical demonstration of a simple e-commerce service design rather than a production-ready full commerce platform.

---

## Author

Developed by [junkar](https://github.com/junkar)

If you found this project useful, consider starring the repository.
