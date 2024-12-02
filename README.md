# TodoList Application

This project is a simple task management application (To-Do List) developed in **Java 21**. It uses **JDBC** for database interaction with a **PostgreSQL** database running in a **Docker** container.

## Features
- Add tasks
- List tasks
- Update tasks
- mark task as completed
- Delete tasks


## Prerequisites

Make sure you have the following software installed on your machine:

- [Java 21](https://openjdk.org/projects/jdk/21/)
- [Docker](https://www.docker.com/)
- [Maven](https://maven.apache.org/) (for dependency management)

## Database Setup

The PostgreSQL database will run in a Docker container. Use the following command to create and start the container:

```bash
docker run --name todolist-postgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=123456 -e POSTGRES_DB=postgres -p 5432:5432 -d postgres
```

After starting the container, the application will be configured to connect to the database using the following parameters that you can find in the ConnectionFactory class

## Project Setup

1. Clone this repository:

   ```bash
   git clone https://github.com/your-username/todo.git
   cd todolist-java-jdbc
   ```

2. Build the project using Maven:

   ```bash
   mvn clean install
   ```

3. Run the application:

   ```bash
   java -jar target/ToDoApp1-1.0-SNAPSHOT.jar
   ```
   
## Technologies Used

- **Java 21**
- **JDBC**
- **PostgreSQL**
- **Docker**
- **Maven**

## Contribution

Feel free to open issues or submit pull requests to improve the project.

## License

This project is licensed under the [MIT License](LICENSE).
