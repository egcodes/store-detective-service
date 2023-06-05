# Store Detective Service
Store Detective Service is a microservice-based application crafted to aid users in discovering the nearest 'N' stores from a given location. Providing the latitude and longitude coordinates lets the service calculate the distance to nearby stores and returns the top 'N' closest options. Plus, it even suggests the nearest stores considering their current open or closed status.

* Locate the 'N' nearest stores from a given location, along with their distances.
* Discover the 'N' nearest stores that are open at the current time.

## Tech. Capabilities
* API documentation via Swagger: [http://localhost:8080/swagger-ui/index.html].
* Centralized exception handling for consistent error responses.
* Custom defined error types and codes for improved troubleshooting.
* Multi-language support for API error and response messages.
* Tracing capabilities with correlation-id for easier debugging.
* Monitoring of method execution times.
* Logging of long-running endpoints for performance management.
* Robust data validation for better security and reliability.

## Used libraries 
The application is built using Java 11 and Spring Boot 2.7.x. It utilizes the following libraries:

* spring-boot-starter-web
* spring-boot-starter-data-jpa
* spring-boot-starter-validation
* spring-boot-starter-aop
* spring-boot-starter-test
* rest-assured
* h2-database
* springfox-swagger
* mapstruct
* project-lombok

## Follow the below steps to containerize the application

```shell
# Build the project
$ mvn clean package

# Build Docker image
$ docker build --tag=store-detective-service:latest .

# Run Docker container
$ docker run -p8080 --name store-detective-service store-detective-service:latest
```