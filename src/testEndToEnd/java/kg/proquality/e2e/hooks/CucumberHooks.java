package kg.proquality.e2e.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import kg.proquality.e2e.db.PostgresClient;
import kg.proquality.e2e.mock.UserBalanceServiceStub;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CucumberHooks {

    @Autowired
    private PostgresClient postgresClient;

    @Autowired
    private UserBalanceServiceStub userBalanceService;

    @Before("@EnableWireMock")
    public void initUserBalanceStub() {
        userBalanceService.init();
    }

    @After("@EnableWireMock")
    public void shutDownUserBalanceStub() {
        userBalanceService.shutDown();
    }

    @After
    public void cleanDatabase() {
        postgresClient.cleanDatabase();
    }

}
