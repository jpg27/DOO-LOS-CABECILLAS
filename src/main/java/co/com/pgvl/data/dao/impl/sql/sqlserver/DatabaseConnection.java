package co.com.pgvl.data.dao.impl.sql.sqlserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String CONNECTION_URL = "jdbc:postgresql://localhost:5432/clientes"
            + "?user=postgres&password=1234";
    
    static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONNECTION_URL);
    }
}
