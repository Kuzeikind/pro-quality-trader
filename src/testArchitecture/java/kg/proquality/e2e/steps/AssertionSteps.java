package kg.proquality.e2e.steps;

import static kg.proquality.e2e.context.TestContext.CURRENT_USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;

import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import kg.proquality.trader.model.Stock;
import kg.proquality.trader.model.User;
import kg.proquality.trader.producer.UpdateUserBalanceUpdateEvent;
import org.apache.http.HttpStatus;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class AssertionSteps extends BaseSteps {

    @Then("user should have {int} stocks with ticker {string}")
    public void userShouldHaveStocks(Integer amountExpected, String ticker) {
        User currentUser = testContext.get(CURRENT_USER);
        Map<String, Integer> userStocks = postgresClient.findUserStocks(currentUser.getId());

        Integer amountActual = userStocks.get(ticker);
        assertThat(amountActual).isEqualTo(amountExpected);
    }

    @Then("user should receive an error with message saying user has not enough money")
    public void notEnoughMoney() {
        Response response = testContext.getResponse();

        response.then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("errorMessage", containsString("Not enough money to buy stock."));
    }

    @Then("kafka event to update user's balance by {double} moneys should be sent")
    public void shouldSendUpdateBalanceEvent(Double amount) {
        List<ConsumerRecord<Integer, UpdateUserBalanceUpdateEvent>> records = kafkaConsumerClient.poll();
        assertThat(records)
            .as("Did not receive any messages")
            .isNotEmpty();

        Optional<ConsumerRecord<Integer, UpdateUserBalanceUpdateEvent>> record
            = records.stream()
                     .filter(r -> r.value().getBalanceUpdate().equals(amount))
                     .findFirst();

        assertThat(record.isPresent()).isTrue();
    }

    @Then("stock with ticker {string} should cost {double} to sell and {double} to buy")
    public void stockPriceShoulgBeUpdated(String ticker, Double sellPrice, Double buyPrice) {
        Stock stock = postgresClient.findStockByTicker(ticker);
        assertThat(stock.getSellPrice()).isEqualTo(sellPrice);
        assertThat(stock.getBuyPrice()).isEqualTo(buyPrice);
    }
}
