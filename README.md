# 🏡 Property Service API

> **Modern Spring Boot microservice for property listings — clean APIs, Dockerized PostgreSQL, and portfolio‑ready unit tests.**

---

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.0-green?logo=springboot)
![Java](https://img.shields.io/badge/Java-25-orange?logo=coffeescript)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-18-blue?logo=postgresql)
![Docker](https://img.shields.io/badge/Docker-Compose-lightblue?logo=docker)
![JUnit](https://img.shields.io/badge/JUnit-5-red?logo=junit5)
![Mockito](https://img.shields.io/badge/Mockito-Unit%20Tests-yellow)

---

A backend service built for **portfolio demonstration** and **interview readiness**.  
It showcases:
- **[CRUD endpoints](ca://s?q=Spring_Boot_CRUD_API)** with DTOs and validation
- **[Global exception handling](ca://s?q=Spring_Boot_validation_and_error_handling)**
- **[PostgreSQL persistence](ca://s?q=Spring_Boot_PostgreSQL_integration)** via Docker Compose
- **[Unit tests](ca://s?q=Spring_Boot_unit_testing)** with JUnit 5 + Mockito
- **[Logging](ca://s?q=Spring_Boot_logging_with_Lombok)** using Lombok `@Slf4j`
- **[Postman collection](ca://s?q=Postman_collection_for_testing)** for API testing

---



## Architecture and tech stack

The application follows a conventional Spring layered architecture:

```text
HTTP client
    |
PropertyController  (/properties)
    |
PropertyService     (business operations)
    |
PropertyRepository  (Spring Data JPA)
    |
PostgreSQL
```

| Area | Technology |
| --- | --- |
| Language | Java 25 |
| Application framework | Spring Boot 4.1.0 / Spring MVC |
| Persistence | Spring Data JPA and Hibernate |
| Database | PostgreSQL |
| Validation | Jakarta Bean Validation |
| Build and test | Maven Wrapper, JUnit 5, Mockito, AssertJ |
| Boilerplate reduction | Lombok |
| Containers | Docker and Docker Compose |

The source code is organised under `src/main/java/com/norhaak/property_service` into `controller`, `service`, `repository`, `model`, `dto`, `mapper`, and `exception` packages. The `Property` entity contains a title, location, positive price, optional description (up to 1,000 characters), and optional image URL. Validation errors return HTTP 400 and unknown property IDs return HTTP 404.

## Prerequisites

Choose one of the following ways to run the service:

- **Docker:** Docker Engine with Docker Compose.
- **Local Java:** JDK 25, plus a running PostgreSQL instance. Maven itself is not required because the repository includes `mvnw`/`mvnw.cmd`.

For trying the supplied API requests, install Postman and import the included collection and environment files.

## Installation and setup

Clone the repository and enter it:

```bash
git clone <repository-url>
cd property-service
```

### Run with Docker Compose

Docker Compose starts PostgreSQL and the application together. The application is available at `http://localhost:8080`.

```bash
docker compose up --build
```

The Compose configuration creates these database settings:

| Variable | Value |
| --- | --- |
| `POSTGRES_DB` | `propertydb` |
| `POSTGRES_USER` | `property_user` |
| `POSTGRES_PASSWORD` | `property_pass` |

Stop the stack with `docker compose down`. Add `-v` only when you deliberately want to remove the persisted `property-db-data` volume.

### Run locally

Create a PostgreSQL database and user matching your chosen credentials, then supply Spring's datasource environment variables. The checked-in default datasource host is `db`, which is the Docker Compose service name; use `localhost` (or your database host) for a local database.

```bash
export SPRING_DATASOURCE_URL='jdbc:postgresql://localhost:5432/propertydb'
export SPRING_DATASOURCE_USERNAME='property_user'
export SPRING_DATASOURCE_PASSWORD='property_pass'
./mvnw spring-boot:run
```

On Windows, use `mvnw.cmd spring-boot:run` and set the same variables with your shell's environment-variable syntax. Hibernate is configured with `ddl-auto: update`, so it creates or updates the `properties` table at startup.

To build an executable JAR instead:

```bash
./mvnw package
java -jar target/property-service-0.0.1-SNAPSHOT.jar
```

## Usage

The API listens on port `8080` and exposes these endpoints:

| Method | Endpoint | Description | Success response |
| --- | --- | --- | --- |
| `POST` | `/properties` | Create a property | `200 OK` |
| `GET` | `/properties` | List all properties | `200 OK` |
| `GET` | `/properties/{id}` | Get one property | `200 OK` |
| `PUT` | `/properties/{id}` | Replace mutable property fields | `200 OK` |
| `DELETE` | `/properties/{id}` | Delete a property | `204 No Content` |

Create a property:

```bash
curl -X POST http://localhost:8080/properties \
  -H 'Content-Type: application/json' \
  -d '{
    "title": "Cozy Studio",
    "location": "Casablanca",
    "price": 50000,
    "description": "Nice place near downtown",
    "imageUrl": "https://example.com/studio.jpg"
  }'
```

List properties:

```bash
curl http://localhost:8080/properties
```

Update property `1`:

```bash
curl -X PUT http://localhost:8080/properties/1 \
  -H 'Content-Type: application/json' \
  -d '{
    "title": "Updated Studio",
    "location": "Casablanca",
    "price": 60000,
    "description": "Renovated and modern",
    "imageUrl": "https://example.com/studio.jpg"
  }'
```

The request body requires non-blank `title` and `location` fields and a `price` greater than zero. `description` is optional but cannot exceed 1,000 characters. API responses use the same fields plus an `id` assigned on creation.

For Postman, import [PropertyService.postman_collection.json](PropertyService.postman_collection.json) and [PropertyService.postman_environment.json](PropertyService.postman_environment.json). The collection includes the full CRUD sequence and saves the ID returned by the create request for subsequent requests.

## Testing

Run the local unit test suite with:

```bash
./mvnw test
```

The suite currently exercises `PropertyService` with Mockito; it does not require PostgreSQL. Verify a production-style package build with:

```bash
./mvnw package
```
---

## 👨‍💻 Built by Norhaak

Crafted with ❤️ using **Spring Boot**, **PostgreSQL**, and **Docker Compose**.  
Unit tested with **JUnit 5 + Mockito** for reliability.

🔗 Connect with me:
- [GitHub](https://github.com/norhaak)
- [LinkedIn](https://www.linkedin.com/in/norhaak)
- [Portfolio](https://norhaak.dev) *(optional if you have one)*

---
