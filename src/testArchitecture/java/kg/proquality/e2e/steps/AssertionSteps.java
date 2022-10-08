package kg.proquality.e2e.steps;

import static kg.proquality.e2e.context.TestContext.CURRENT_USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import java.util.Map;
import kg.proquality.trader.model.User;
import org.apache.http.HttpStatus;

public class AssertionSteps extends BaseSteps {

    @Then("user should have {int} stocks with ticker {string}")
    public void userShouldHaveStocks(Integer amountExpected, String ticker) {
        User currentUser = testContext.get(CURRENT_USER);
        Map<String, Integer> userStocks = postgresClient.findUserStocks(currentUser.getId());

        Integer amountActual = userStocks.get(ticker);
        assertThat(amountActual).isEqualTo(amountExpected);
    }

    @Then("user should receive an error with message saying user has not enough money")
    public void notEnoughtMoney() {
        Response response = testContext.getResponse();

        response.then()
            .statusCode(HttpStatus.SC_BAD_REQUEST)
            .body("errorMessage", containsString("Not enough money to buy stock."));
    }

}
