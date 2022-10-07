package kg.proquality.e2e.steps;

import io.cucumber.java.en.When;
import io.restassured.response.Response;
import kg.proquality.trader.model.Stock;
import kg.proquality.trader.model.User;

import static kg.proquality.e2e.context.TestContext.CURRENT_USER;

public class WhenSteps extends BaseSteps {

    @When("user requests to buy {int} shares of stock with ticker {string}")
    public void buyStock(Integer amount, String ticker) {
        User currentUser = testContext.get(CURRENT_USER);
        Stock stockToBuy = postgresClient.findStockByTicker(ticker);

        Response response = traderRestClient.buyStock(currentUser.getId(), stockToBuy.getId(), amount);
        testContext.setResponse(response);
    }

}
