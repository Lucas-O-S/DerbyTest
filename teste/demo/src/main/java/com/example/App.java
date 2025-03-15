package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class App {
    public static void main(String[] args) {
        try {
            // Carrega o driver do Derby
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

            // Inicia o banco de dados Derby no modo embutido (e cria se não existir)
            String dbURL = "jdbc:derby:FESA;create=true"; // "create=true" cria o banco se não existir
            try (Connection connection = DriverManager.getConnection(dbURL);
                 Statement statement = connection.createStatement()) {

                // Cria a tabela se não existir
                statement.executeUpdate("CREATE TABLE CUSTOMER (ID INT PRIMARY KEY, NAME VARCHAR(255))");

                // Insere um valor de teste
                statement.executeUpdate("INSERT INTO CUSTOMER (ID, NAME) VALUES (1, 'Lucas')");


                // Executa a consulta
                ResultSet resultSet = statement.executeQuery("SELECT * FROM CUSTOMER");

                // Exibe os resultados
                while (resultSet.next()) {
                    System.out.println("Nome: " + resultSet.getString("NAME"));
                }

                System.out.println("Conexão bem-sucedida!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
