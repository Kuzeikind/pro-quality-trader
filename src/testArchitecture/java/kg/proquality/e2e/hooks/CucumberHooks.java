package kg.proquality.e2e.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import kg.proquality.e2e.db.PostgresClient;
import kg.proquality.e2e.mock.UserBalanceServiceMock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CucumberHooks {

    @Autowired
    private PostgresClient postgresClient;

    @Autowired
    private UserBalanceServiceMock userBalanceServiceMock;

    @Before("@EnableWireMock")
    public void startWireMock() {
        log.info("Started wiremock server");
        userBalanceServiceMock.start();
    }

    @After("@EnableWireMock")
    public void shutDownWireMock() {
        userBalanceServiceMock.shutDown();
        log.info("Shut down wiremock server");
    }

    @After
    public void cleanDatabase() {
        postgresClient.cleanDatabase();
    }

}
