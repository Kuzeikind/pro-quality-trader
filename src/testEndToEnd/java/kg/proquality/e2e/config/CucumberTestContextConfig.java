package kg.proquality.e2e.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;

@TestConfiguration
@ComponentScan(basePackages = {"kg.proquality.e2e"})
public class CucumberTestContextConfig {
}
