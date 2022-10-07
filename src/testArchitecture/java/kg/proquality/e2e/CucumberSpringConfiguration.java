package kg.proquality.e2e;

import io.cucumber.spring.CucumberContextConfiguration;
import kg.proquality.e2e.initializer.KafkaContainerInitializer;
import kg.proquality.e2e.initializer.PostgresContainerInitializer;
import kg.proquality.trader.Application;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(
        initializers = {
            KafkaContainerInitializer.class,
            PostgresContainerInitializer.class
        },
        classes = {
                CucumberTestContextConfig.class,
                Application.class
        }
)
public class CucumberSpringConfiguration {
}
