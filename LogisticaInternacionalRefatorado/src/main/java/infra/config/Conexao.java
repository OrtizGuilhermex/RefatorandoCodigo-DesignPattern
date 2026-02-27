package infra.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    /**
     * Configuração de baixo nível.
     * Centraliza os detalhes técnicos da conexão JDBC.
     */

    private static String URL = "jdbc:mysql://localhost:3306/logistica?useSSL=false&serverTimezone=UTC";
    private static String USER = "root";
    private static String PASSWORD = "";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }
}