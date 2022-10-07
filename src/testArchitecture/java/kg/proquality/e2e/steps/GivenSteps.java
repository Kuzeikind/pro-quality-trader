package kg.proquality.e2e.steps;

import io.cucumber.java.en.Given;
import kg.proquality.trader.model.User;

import static kg.proquality.e2e.context.TestContext.CURRENT_USER;

public class GivenSteps extends BaseSteps {

    @Given("user exists")
    public void createUser() {
        User currentUser = new User().setEmail("test.user@testmail.com").setId(1);
        postgresClient.createUser(currentUser);

        testContext.put(CURRENT_USER, currentUser);
    }

    @Given("user has {int} shares of stock with ticker {string}")
    public void userHasStock(Integer numberOfShares, String ticker) {

    }

    @Given("user has {double} moneys")
    public void userHasMoney(Double amount) {
        User currentUser = testContext.get(CURRENT_USER);

        userBalanceServiceMock.stubForUserBalanceRequest(currentUser.getId(), amount);
    }

    @Given("shares with ticker {string} cost {double} moneys to buy")
    public void setStockBuyPrice() {

    }

}
