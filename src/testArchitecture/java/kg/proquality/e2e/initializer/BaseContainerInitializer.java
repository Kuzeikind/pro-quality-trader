package kg.proquality.e2e.initializer;

import org.springframework.context.ConfigurableApplicationContext;

public abstract class BaseContainerInitializer {

    private static final String RUN_ISOLATED = "RUN_ISOLATED";

    protected static boolean runIsolated(ConfigurableApplicationContext applicationContext) {
        String runIsolated = (String) applicationContext
            .getEnvironment()
            .getSystemEnvironment()
            .getOrDefault(RUN_ISOLATED, "false");

        return Boolean.parseBoolean(runIsolated);
    }
}
