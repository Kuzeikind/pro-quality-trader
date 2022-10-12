package kg.proquality.e2e.initializer;

import static org.testcontainers.utility.DockerImageName.parse;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class KafkaContainerInitializer
    extends BaseContainerInitializer
    implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static String KAFKA_IMAGE = "confluentinc/cp-kafka:7.0.1";

    private static final KafkaContainer KAFKA_CONTAINER = new KafkaContainer(
        parse(KAFKA_IMAGE).asCompatibleSubstituteFor("confluentinc/cp-kafka"));


    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        if (!runIsolated(applicationContext)) {
            return;
        }

        KAFKA_CONTAINER.start();
        String kafkaBootstrapServers = KAFKA_CONTAINER.getBootstrapServers();

        TestPropertyValues.of(
            "KAFKA_BROKER=" + kafkaBootstrapServers
        ).applyTo(applicationContext.getEnvironment());
    }

}
