package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class ConnectionFactory {
    private static final Dotenv dotenv = Dotenv.load();

    public static final String URL = "jdbc:mysql://localhost:3306/casa_caiu";
    public static final String USER = "root";
    public static final String PASSWORD = "password";

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("MySQL connection running!");
            return connection;
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco: " + e.getMessage());
            return null;
        }
    }
}

