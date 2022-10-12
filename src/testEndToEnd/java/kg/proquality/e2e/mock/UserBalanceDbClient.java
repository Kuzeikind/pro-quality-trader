package kg.proquality.e2e.mock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@ConditionalOnProperty(prefix = "user-balance-service", name = "use-mock", havingValue = "false")
public class UserBalanceDbClient implements UserBalanceServiceStub {

    @Override
    public void init() {
        log.error("Init db stub");
    }

    @Override
    public void stubForUserBalanceRequest(Integer userId, Double amount) {
        log.error("Put some data to the DB");
    }

    @Override
    public void shutDown() {
        log.error("Shutdown db stub");
    }
}
