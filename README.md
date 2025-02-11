# E-Commerce Microservice Application

This is a fully implemented **e-commerce application** built with a **microservices architecture** using **Spring Boot 3
** and various modern technologies. The project follows best practices for scalability, maintainability, and
cloud-native deployment.

## 🚀 Technologies Used

- **Spring Boot 3** - Core framework for building microservices
- **JDK 21** - Java Development Kit for development
- **Spring Cloud** - Tools for service discovery, centralized configuration, and API gateway
- **Spring Cloud Config Server** - Centralized configuration management
- **Eureka Server** - Service discovery for registering microservices
- **API Gateway** - A single entry point for handling requests to microservices
- **Resilience4j** - Circuit breaker and fault tolerance
- **Docker** - Containerization of microservices
- **MongoDB** - NoSQL database for non-relational data
- **PostgreSQL** - Relational database for structured data
- **Kafka** - Message broker for asynchronous inter-service communication
- **RabbitMQ** - Optional alternative message broker (NOT IMPLEMENTED)
- **Redis** - Caching mechanism to optimize performance (NOT IMPLEMENTED)
- **OAuth2 / JWT** - Authentication and authorization for secure access
- **Grafana & Prometheus** - Monitoring and observability tools (NOT IMPLEMENTED)

## 📁 Project Structure

The application follows a **microservices architecture**, with the following core components:

- **Config Server** - Manages configuration for all microservices.
- **Eureka Server** - Handles service discovery and registration.
- **API Gateway** - Routes requests to respective microservices.
- **Auth Service** - Handles authentication and user management.
- **Product Service** - Manages product catalog and inventory.
- **Order Service** - Handles order creation, processing, and tracking.
- **Payment Service** - Integrates with payment gateways like RazorPay etc. (NOT INTEGRATED WITH ACTUAL GATEWAYS)
- **Notification Service** - Sends email/SMS notifications using Kafka.

## 🛠 Getting Started

### Prerequisites

Ensure you have the following tools installed:

- **Java 21** - Required for building and running the application.
- **Docker & Docker Compose** - For containerized database and services.
- **MongoDB** - Can be run via Docker or installed locally.
- **PostgreSQL** - Can also be containerized using Docker.
- **Kafka** - Can be run locally or via Docker.
- **Redis** - Optional caching layer for performance optimization.

### 📥 Clone the Repository

```bash
git clone https://github.com/fvrvz/e-commerce-app.git
cd e-commerce-app
```

### 🌍 Environment Setup

1. **Start Required Services** (MongoDB, PostgreSQL, Kafka, Redis) using Docker:

```bash
docker-compose up -d
```

### 🚀 Running the Application

1. **Start Config Server**

```bash
cd config-server
./mvnw spring-boot:run
```

2. **Start Eureka Server**

```bash
cd eureka-server
./mvnw spring-boot:run
```

3. **Start API Gateway**

```bash
cd api-gateway
./mvnw spring-boot:run
```

4. **Start Microservices**

Navigate to each microservice folder and run:

```bash
./mvnw spring-boot:run
```

Or start all services at once using Docker Compose:

```bash
docker-compose up
```

### 🔗 Accessing the Application

| Service                 | URL                                            |
|-------------------------|------------------------------------------------|
| **Eureka Dashboard**    | [http://localhost:8761](http://localhost:8761) |
| **API Gateway**         | [http://localhost:8080](http://localhost:8080) |
| **Mongo Express**       | [http://localhost:8081](http://localhost:8081) |
| **pgAdmin4**            | [http://localhost:5050](http://localhost:5050) |
| **Kafka UI (Optional)** | [http://localhost:9000](http://localhost:9000) |

## 📌 Features Implemented

✅ **Service Discovery with Eureka**  
✅ **Centralized Configuration with Config Server**  
✅ **API Gateway for Routing and Authentication**  
✅ **MongoDB for NoSQL Data and PostgreSQL for Relational Data**  
✅ **Kafka Integration for Event-Driven Architecture**  
✅ **JWT-based Authentication for Secure Access**  
✅ **Circuit Breaker with Resilience4j**  
✅ **Caching with Redis for Performance Optimization**  
✅ **Docker & Kubernetes for Deployment**  
✅ **Logging & Monitoring with Grafana & Prometheus**

## 🛠 Future Enhancements

🔜 **RazorPay/PayTM Integration for Payment Processing**  
🔜 **Kubernetes Deployment for Production-Grade Scaling**  
🔜 **GraphQL Support for Flexible API Queries**  
🔜 **CI/CD Pipeline for Automated Deployments**

## 🤝 Contributing

We welcome contributions! Follow these steps:

1. **Fork the repository**
2. **Create a new branch** (`git checkout -b feature-name`)
3. **Make changes & commit** (`git commit -m 'Add new feature'`)
4. **Push to your branch** (`git push origin feature-name`)
5. **Open a pull request**

## 📝 License

This project is licensed under the **MIT License** - see the LICENSE file for details.

## 📬 Contact Information

- **Author:** Faraaz Khan
- **Email:** [codefaraaz@gmail.com](mailto:codefaraaz@gmail.com)
- **GitHub:** [github.com/fvrvz](https://github.com/fvrvz)

