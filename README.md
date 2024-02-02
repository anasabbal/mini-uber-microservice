[![Build Status](https://travis-ci.com/coma123/Spring-Boot-Blog-REST-API.svg?branch=development)](https://travis-ci.com/coma123/Spring-Boot-Blog-REST-API) [![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=coma123_Spring-Boot-Blog-REST-API&metric=alert_status)](https://sonarcloud.io/dashboard?id=coma123_Spring-Boot-Blog-REST-API) [![CII Best Practices](https://bestpractices.coreinfrastructure.org/projects/3706/badge)](https://bestpractices.coreinfrastructure.org/projects/3706)

# Mini Uber
![logo](./img/back.png)

Mini Uber Microservice is a microservices-based architecture for a ride-sharing application similar to Uber. It consists of various microservices responsible for different functionalities, such as user authentication, ride management, driver location tracking, payment processing, and more.

## Project Structure

The project structure follows a microservices architecture pattern, with each microservice responsible for a specific domain or functionality. Here's an overview of the project structure:

- **api-gateway**: Microservice responsible for routing requests to the appropriate backend services.
- **config-server**: Microservice for managing configuration settings for all other microservices.
- **core-comm**: Core communication module providing communication utilities for other microservices.
- **customer-service**: Microservice handling customer-related operations, such as user registration, authentication, and ride requests.
- **driver-location-service**: Microservice managing real-time tracking of driver locations.
- **driver-service**: Microservice handling driver-related operations, such as driver registration, availability, and ride assignments.
- **eureka-server**: Service discovery server for registering and discovering microservices within the architecture.
- **location-service**: Microservice managing location-related operations, such as geocoding and distance calculations.
- **oauth2-service**: Microservice responsible for OAuth2-based authentication and authorization.
- **order-service**: Microservice managing ride orders and ride lifecycle management.
- **payment-service**: Microservice handling payment processing for completed rides.
- **rating-service**: Microservice managing rating and review functionalities for both passengers and drivers.
- **wallet-service**: Microservice managing digital wallets for users and processing wallet transactions.

## Technologies Used

- **Java**: Backend development using Java programming language.
- **Spring Boot**: Framework for building microservices-based applications.
- **Spring Cloud**: Toolkit for building microservices architectures with Spring Boot.
- **Docker**: Containerization platform for packaging and deploying microservices.
- **Terraform**: Infrastructure as code tool for managing cloud resources.
- **OAuth2**: Authentication and authorization framework for securing microservices.
- **Netflix Eureka**: Service discovery and registration tool for microservices.
- **RabbitMQ**: Message broker for asynchronous communication between microservices.
- **MySQL**: Relational database management system for storing application data.

## Getting Started

To run the Mini Uber Microservice project locally, follow these steps:

1. Clone the repository: `git clone https://github.com/anasabbal/mini-uber-microservice.git`
2. Navigate to the project directory: `cd mini-uber-microservice`
3. Install dependencies: `mvn clean install`
4. Start each microservice individually using Spring Boot or Docker.
5. Access the API endpoints of each microservice as needed.

For detailed instructions on setting up and configuring each microservice, refer to the respective README.md files in their directories.

## License

This project is licensed under the [MIT License](LICENSE).
If you would like to contribute to Mini Uber Microservice, please contact the project owner at [anas.abbal10@gmail.com](mailto:anas.abbal10@gmail.com).

