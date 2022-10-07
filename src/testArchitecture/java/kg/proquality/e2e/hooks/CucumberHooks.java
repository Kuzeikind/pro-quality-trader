package kg.proquality.e2e.hooks;

import io.cucumber.java.After;
import kg.proquality.e2e.db.PostgresClient;
import kg.proquality.e2e.mock.UserBalanceServiceMock;
import org.springframework.beans.factory.annotation.Autowired;

public class CucumberHooks {

    @Autowired
    private PostgresClient postgresClient;

    @Autowired
    private UserBalanceServiceMock userBalanceServiceMock;

    @After
    public void resetMock() {
        userBalanceServiceMock.reset();
    }

    @After
    public void cleanDatabase() {
        postgresClient.cleanDatabase();
    }

}
