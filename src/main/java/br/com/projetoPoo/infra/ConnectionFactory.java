package br.com.projetoPoo.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private ConnectionFactory(){}

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5433/ItensDB", "postgres","root");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
