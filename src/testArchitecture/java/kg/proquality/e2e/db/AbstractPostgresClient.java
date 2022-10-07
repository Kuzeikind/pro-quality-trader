package kg.proquality.e2e.db;

import static kg.proquality.e2e.initializer.PostgresContainerInitializer.getJdbcUrl;
import static kg.proquality.e2e.initializer.PostgresContainerInitializer.getPostgresPassword;
import static kg.proquality.e2e.initializer.PostgresContainerInitializer.getPostgresUsername;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.annotation.PostConstruct;

public abstract class AbstractPostgresClient {

    protected Connection connection;

    @PostConstruct
    public void init() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                getJdbcUrl(),
                getPostgresUsername(),
                getPostgresPassword()
            );
        } catch (SQLException | ClassNotFoundException e) {
            throw new IllegalStateException("Failed to connect to postgres database", e);
        }
    }
}
