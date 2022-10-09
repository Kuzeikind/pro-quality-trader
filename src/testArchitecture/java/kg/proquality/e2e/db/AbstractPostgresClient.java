package kg.proquality.e2e.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;

public abstract class AbstractPostgresClient {

    protected Connection connection;

    @Value("${POSTGRES_JDBC_URL}")
    private String postgresJdbcUrl;

    @Value("${POSTGRES_PASSWORD}")
    private String postgresPassword;

    @Value("${POSTGRES_USERNAME}")
    private String postgresUsername;

    @PostConstruct
    public void init() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(postgresJdbcUrl, postgresUsername, postgresPassword);
        } catch (SQLException | ClassNotFoundException e) {
            throw new IllegalStateException("Failed to connect to postgres database", e);
        }
    }
}
