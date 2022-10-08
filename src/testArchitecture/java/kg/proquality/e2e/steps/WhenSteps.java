package kg.proquality.e2e.steps;

import io.cucumber.java.en.When;
import io.restassured.response.Response;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import kg.proquality.trader.consumer.UpdateStockPriceEvent;
import kg.proquality.trader.model.Stock;
import kg.proquality.trader.model.User;
import org.apache.kafka.clients.producer.RecordMetadata;

import static kg.proquality.e2e.context.TestContext.CURRENT_USER;

public class WhenSteps extends BaseSteps {

    @When("user requests to buy {int} shares of stock with ticker {string}")
    public void buyStock(Integer amount, String ticker) {
        User currentUser = testContext.get(CURRENT_USER);
        Stock stockToBuy = postgresClient.findStockByTicker(ticker);

        Response response = traderRestClient.buyStock(currentUser.getId(), stockToBuy.getId(), amount);
        testContext.setResponse(response);
    }

    @When("service receives event to update sell price to {double} and buy price to {double} for stock with ticker {string}")
    public void updateStockPrice(Double sellPrice, Double buyPrice, String ticker)
        throws ExecutionException, InterruptedException {
        UpdateStockPriceEvent event = new UpdateStockPriceEvent()
            .setTicker(ticker)
            .setSellPrice(sellPrice)
            .setBuyPrice(buyPrice);

        Future<RecordMetadata> recordMetadataFuture = kafkaProducerClient.updateStockPrice(event);
        recordMetadataFuture.get();
    }

}
