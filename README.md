# HRManagementSystem API

## Project Description

The HRManagementSystem API is a human resources management system developed in Java using the Spring framework. This API provides essential functionalities for managing employee information, departments, and other HR-related operations.

## Development Team Group 2

- [Dogan Kilaz]
- [Vinicius Lalli]
- [Regina Pashkevych]
- [Pravin Suresh]

## Prerequisites

Make sure to have the following tools installed before running the project:

- Java 8 or higher
- Maven
- PostgreSQL database
- [Spring Boot](https://spring.io/projects/spring-boot)

## Run following SQL insert statements
```
INSERT INTO userroles(name) VALUES('ROLE_MODERATOR');
INSERT INTO userroles(name) VALUES('ROLE_ADMIN');
```

## Configuration

1. Clone the repository:

    ```bash
    git clone https://github.com/your-username/HRManagementSystem.git
    ```

2. Configure database properties in `src/main/resources/application.properties`:

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    ```

3. Run the application:

    ```bash
    mvn spring-boot:run
    ```

The application will be available at [http://localhost:8080](http://localhost:8080).

## API Endpoints

The API exposes the following endpoints:

Contract
- `POST api/contract/add`
- `GET api/contract/getAll`
- `GET api/contract/get/{id}`
- `PUT api/contract/update/{id}`
- `DEL api/contract/deleteById/{id}`

Department
- `POST /api/department/add`
- `GET /api/department/get/{id}`
- `GET /api/department/getAll`
- `PUT /api/department/update/{id}`
- `DEL /api/department/deleteById/{id}`
- `DEL /api/department/deleteByName/{name}`

Employee
- `POST /api/employee/add`
- `GET /api/employee/getAll`
- `GET /api/employee/getEmployee/{id}`
- `PUT /api/employee/update/{id}`
- `DEL /api/employee/deleteById/{id}`
- `DEL /api/employee/deleteByEmail/{email}`

AuthController
- `POST api/auth/signup`
- `POST api/auth/signin`




