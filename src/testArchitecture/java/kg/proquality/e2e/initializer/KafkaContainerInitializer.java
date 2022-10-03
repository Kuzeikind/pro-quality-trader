package kg.proquality.e2e.initializer;

import static org.testcontainers.utility.DockerImageName.parse;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;

public class KafkaContainerInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static String KAFKA_IMAGE = "confluentinc/cp-kafka:7.0.1";

    @Container
    public static final KafkaContainer KAFKA_CONTAINER = new KafkaContainer(
        parse(KAFKA_IMAGE).asCompatibleSubstituteFor("confluentinc/cp-kafka"));

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        KAFKA_CONTAINER.start();
        String kafkaBootstrapServers = KAFKA_CONTAINER.getBootstrapServers();

        TestPropertyValues.of(
            "KAFKA_BROKER=" + kafkaBootstrapServers
        ).applyTo(applicationContext.getEnvironment());
    }
}
