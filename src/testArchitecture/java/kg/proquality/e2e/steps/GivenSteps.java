package kg.proquality.e2e.steps;

import io.cucumber.java.en.Given;
import kg.proquality.trader.model.Stock;
import kg.proquality.trader.model.User;

import static kg.proquality.e2e.context.TestContext.CURRENT_USER;

public class GivenSteps extends BaseSteps {

    @Given("user exists")
    public void createUser() {
        User currentUser = new User().setEmail("test.user@testmail.com").setId(1234);
        postgresClient.createUser(currentUser);

        testContext.put(CURRENT_USER, currentUser);
    }

    @Given("user has {int} shares of stock with ticker {string}")
    public void userHasStock(Integer numberOfShares, String ticker) {
        User currentUser = testContext.get(CURRENT_USER);
        Stock stock = postgresClient.findStockByTicker(ticker);
        postgresClient.userHasStock(currentUser.getId(), stock.getId(), numberOfShares);
    }

    @Given("user has {double} moneys")
    public void userHasMoney(Double amount) {
        User currentUser = testContext.get(CURRENT_USER);

        userBalanceServiceMock.stubForUserBalanceRequest(currentUser.getId(), amount);
    }

    @Given("stock with ticker {string} exists and costs {double} moneys to sell and {double} moneys to buy")
    public void createStock(String ticker, Double sellPrice, Double buyPrice) {
        Stock stock = new Stock()
            .setTicker(ticker)
            .setSellPrice(sellPrice)
            .setBuyPrice(buyPrice);

        postgresClient.createStock(stock);
    }
}
