package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection createConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/postgres"; //Nome da base de dados
        String user = "postgres"; //nome do usuário do MySQL
        String password = "123456"; //senha do MySQL

        return DriverManager.getConnection(url, user, password);
    }
}