# Demo Spring Boot Application

This is a demo Spring Boot application that demonstrates the use of JWT authentication and basic CRUD operations.





## Introduction

This project is a simple Spring Boot application that includes JWT-based authentication and basic CRUD operations for managing messages and groups.

## Features

- JWT Authentication
- CRUD operations for messages and groups
- RESTful API endpoints

## Prerequisites

- Java 11 or higher
- Maven 3.6.0 or higher
- MongoDB

## Installation

1. Clone the repository:

    ```sh
    git clone https://github.com/ZiadSheriif/SpringShield
    cd demo-spring-boot
    ```

2. Install the dependencies:

    ```sh
    mvnw clean install
    ```

3. Configure MongoDB connection in `application.properties`:

    ```properties
    spring.data.mongodb.uri=mongodb://localhost:27017/demo
    ```

## Running the Application

1. Run the Spring Boot application:

    ```sh
    mvnw spring-boot:run
    ```

2. The application will be available at `http://localhost:5000`.




### Explanation of Folder Structure

Here’s a breakdown of why this structure is used:

- **com**: The top-level domain of your organization or project.
- **example**: The domain name or project name.
- **demo**: The specific application or module name.

## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any changes.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
