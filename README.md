# E-Commerce Microservice Application

This is an e-commerce application built with a microservices architecture using Spring Boot 3 and various other
technologies. The application is in the initial stages of development, and more features and technologies will be added
as the project evolves.

## Technologies Used

- **Spring Boot 3**: The core framework used to build the microservices.
- **JDK 21**: The Java Development Kit used for development.
- **Spring Cloud**: Provides tools for building cloud-native applications with Spring, including service discovery,
  configuration management, etc.
- **Config Server**: Centralized configuration management to store all microservices' configurations.
- **Eureka**: Service discovery tool to register and discover microservices.
- **Docker**: For containerization of the application and its microservices.
- **MongoDB**: NoSQL database used for storing non-relational data.
- **Postgres**: Relational database used for structured data.
- **pgAdmin4**: Web interface for managing the Postgres database.
- **mongo-express**: Web-based MongoDB admin interface.

## Project Structure

The project follows a microservices-based architecture with the following key components:

- **Config Server**: A Spring Cloud Config Server that manages configuration properties for all microservices.
- **Eureka Server**: Service discovery tool for registering and discovering all services in the system.
- **Microservices**: Various independent microservices, each handling a specific domain of the e-commerce application (
  e.g., product service, order service, user service, etc.).

## Getting Started

### Prerequisites

Before you begin, make sure you have the following tools installed on your local machine:

- **Java 21**: JDK 21 is required for building and running the application.
- **Docker**: Docker is required for containerizing and running the databases.
- **MongoDB**: You can run MongoDB via Docker or install it locally.
- **PostgreSQL**: PostgreSQL database, which can also be containerized using Docker.
- **pgAdmin4**: Optional, for managing PostgreSQL via a web interface.
- **mongo-express**: Optional, for managing MongoDB via a web interface.

### Clone the Repository

Start by cloning the repository to your local machine:

```bash
git clone https://github.com/fvrvz/e-commerce-app.git
cd e-commerce-app
```

### Set Up Environment

1. **Configure Docker Containers:** The application uses Docker for MongoDB, PostgreSQL, and pgAdmin4. Ensure that
   Docker is
   installed and running.

2. **Build Docker Images:** You can use `docker-compose.yml` to automatically build and run the required services (
   MongoDB,
   PostgreSQL, pgAdmin4).

```bash
docker-compose up -d
```

This will start the necessary databases and admin interfaces for MongoDB and PostgreSQL in Docker containers.

### Running the Application

1. **Config Server:**
   Start the Config Server, which will provide centralized configuration management for all microservices. Navigate to
   the config-server directory and run:

```bash
./mvnw spring-boot:run
```

2. **Eureka Server:**
   Start the Eureka Server in the eureka-server directory:

```bash
./mvnw spring-boot:run
```

3. **Microservices:**
   Each microservice (e.g., product-service, order-service, etc.) can be started individually. Navigate to the
   microservice directory and run:

```bash
./mvnw spring-boot:run
```

Repeat for each microservice.

**Docker Compose (Optional)**
Alternatively, you can use docker-compose.yml to bring up all services (including the databases) in one go:

```bash
docker-compose up
```

This will start all your services within Docker containers. By default, it will expose the services at:

- **Eureka Dashboard:** http://localhost:8761
- **Mongo Express:** http://localhost:8081
- **pgAdmin4:** http://localhost:5050

### Accessing the Application

- **Eureka Dashboard:** You can view all the registered services at http://localhost:8761.
- **Mongo Express:** Access MongoDB data via http://localhost:8081 (optional).
- **pgAdmin4:** Manage PostgreSQL database through the web interface at http://localhost:5050 (optional).

### Future Work

As development progresses, the following features and technologies will be added:

- **Payment Gateway:** Integrating with payment services like Stripe or PayPal.
- **User Authentication:** Implementing OAuth or JWT-based authentication for users.
- **API Gateway:** Introducing an API Gateway for routing requests, handling authentication, and applying rate-limiting.
- **Additional Microservices:** Building additional microservices (e.g., inventory management, shipping, cart service)
  as
  needed.
- **Message Broker:** Introducing a message broker (like Kafka or RabbitMQ) for inter-service communication.

### Contributing

Feel free to fork the repository, submit issues, or open pull requests. Contributions are welcome!

**How to Contribute**

1. Fork the repository.
2. Create a new branch: git checkout -b feature-name.
3. Make changes, commit them: git commit -m 'Add new feature'.
4. Push to the branch: git push origin feature-name.
5. Open a pull request.

### License

This project is licensed under the MIT License - see the LICENSE file for details.

### Contact Information

- Author: Faraaz Khan
- Email: [codefaraaz@gmail.com](mailto:codefaraaz@gmail.com)