package br.com.projetoPoo.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    static final String host = "jdbc:postgresql://localhost:5433/ItensDB";
    static final String user = "postgres";
    static final String password = "root";

    private ConnectionFactory(){}

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(host,user,password);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
