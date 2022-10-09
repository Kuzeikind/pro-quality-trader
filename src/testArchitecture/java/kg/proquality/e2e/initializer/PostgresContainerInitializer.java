package kg.proquality.e2e.initializer;

import static org.testcontainers.utility.DockerImageName.parse;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class PostgresContainerInitializer
    extends BaseContainerInitializer
    implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final String POSTGRES_IMAGE = "postgres:12-alpine";
    private static final Integer POSTGRES_PORT = 5432;
    private static final String INIT_SCRIPT_PATH = "sql/init_schema.sql";
    private static final String DATABASE_NAME = "trader_db";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        if (!runIsolated(applicationContext)) {
            return;
        }

        PostgreSQLContainer<?> POSTGRES_CONTAINER = new PostgreSQLContainer<>(
            parse(POSTGRES_IMAGE).asCompatibleSubstituteFor("postgres"))
            .withDatabaseName(DATABASE_NAME)
            .withInitScript(INIT_SCRIPT_PATH)
            .withUsername(USERNAME)
            .withPassword(PASSWORD)
            .withExposedPorts(POSTGRES_PORT);

        POSTGRES_CONTAINER.start();
        String jdbcUrl = POSTGRES_CONTAINER.getJdbcUrl();
        String username = POSTGRES_CONTAINER.getUsername();
        String password = POSTGRES_CONTAINER.getPassword();

        TestPropertyValues.of(
            "POSTGRES_JDBC_URL=" + jdbcUrl,
            "POSTGRES_USERNAME=" + username,
            "POSTGRES_PASSWORD=" + password
        ).applyTo(applicationContext.getEnvironment());
    }
}
