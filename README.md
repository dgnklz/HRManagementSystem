# HRManagementSystem API

## Project Description

The HRManagementSystem API is a human resources management system developed in Java using the Spring framework. This API provides essential functionalities for managing employee information, departments, and other HR-related operations.

## Development Team Group 2

- [Vinicius Lalli]
- [Dogan Kilaz]
- [Regina Pashkevych]
- [Pravin Suresh]

## Prerequisites

Make sure to have the following tools installed before running the project:

- Java 8 or higher
- Maven
- PostgreSQL database
- [Spring Boot](https://spring.io/projects/spring-boot)

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

- `GET /api/employees`: Returns the list of all employees.
- `GET /api/employees/{id}`: Returns details of a specific employee.
- `POST /api/employees`: Creates a new employee.
- `PUT /api/employees/{id}`: Updates details of an existing employee.
- `DELETE /api/employees/{id}`: Deletes an employee.

Add more details about the endpoints as needed.

## Contributing

Feel free to contribute improvements. Open an issue to discuss the changes you would like to make.

## License

This project is licensed under the [MIT License](LICENSE).
