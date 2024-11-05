package co.com.pgvl.data.dao.impl.sql.sqlserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String CONNECTION_URL = "jdbc:sqlserver://ucobet-server.database.windows.net:1433;"
            + "database=ucobet-db;"
            + "user=ucobetdbuser;"
            + "password=uc0b3tdbus3r!;"
            + "encrypt=true;"
            + "trustServerCertificate=true;"
            + "hostNameInCertificate=*.database.windows.net;"
            + "loginTimeout=30;";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONNECTION_URL);
    }
}
