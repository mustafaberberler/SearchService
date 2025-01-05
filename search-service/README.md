
# Search Service

The **Search Service** is a core component of the E-commerce platform, responsible for managing search operations of the products.


## Features

- Search and filter products by name, category, description and price range or search all products.

## Technologies Used

- **Java** 17
- **Spring Boot** 3.x
- **Spring Data JPA**
- **PostgreSQL** as the database
- **Docker** for containerization
- **Lombok** for reducing boilerplate code

## Architecture

The microservice follows a **RESTful API** design pattern and communicates with other services via HTTP. 

- **Controller Layer:** Handles HTTP requests.
- **Service Layer:** Contains business logic.
- **Repository Layer:** Interfaces with the database.

## Setup

### Prerequisites

- Docker
- Docker Compose

### Steps

- Build the Docker images:

        docker-compose build

- Start the Docker containers:

        docker-compose up

- Restore the dockerized PostgreSQL database (if you have already cloned **Product Service** from my repository and followed the instructions, it is not needed to restore the database again)

        docker exec -i product-postgres psql -U postgres -d products < database_dump.sql

## API Reference

### APIs of the **Product**

#### Get product by ID

```http
  GET http://localhost:8085/api/product/get/id/{productId}
```
#### Get product by description

```http
  GET http://localhost:8085/api/product/get/description/{productDescription}
```
#### Get product by name

```http
  GET http://localhost:8085/api/product/get/name/{productName}
```
#### Get product by price

```http
  GET http://localhost:8085/api/product/get/price?minPrice=100&maxPrice=1000
```
#### Get all products

```http
  GET http://localhost:8085/api/product/getAll
```
---

### APIs of the **Category**


#### Get category by name

```http
  GET http://localhost:8085/api/category/get/name/{categoryName}
```
#### Get all categories

```http
  GET http://localhost:8085/api/category/getAll
```



