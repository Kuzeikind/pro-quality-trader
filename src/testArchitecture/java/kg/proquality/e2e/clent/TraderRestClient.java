package kg.proquality.e2e.clent;

import io.restassured.response.Response;
import kg.proquality.trader.dto.BuyRequestDto;
import kg.proquality.trader.dto.SellRequestDto;
import org.springframework.stereotype.Service;

@Service
public class TraderRestClient extends BaseRestClient {

    private static final String API_BASE_URL = "/api/v1/trader";
    private static final String BUY_STOCK_URL = API_BASE_URL + "/{userId}/stocks/buy";
    private static final String SELL_STOCK_URL = API_BASE_URL + "/{userId}/stocks/sell";

    public Response buyStock(Integer userId, Integer stockId, Integer amount) {
        BuyRequestDto buyRequestDto = new BuyRequestDto()
            .setStockId(stockId)
            .setAmount(amount);
        return given()
            .pathParam("userId", userId)
            .body(buyRequestDto)
            .when()
            .post(BUY_STOCK_URL);
    }

    public Response sellStock(Integer userId, Integer stockId, Integer amount) {
        SellRequestDto sellRequestDto = new SellRequestDto()
            .setStockId(stockId)
            .setAmount(amount);
        return given()
            .pathParam("userId", userId)
            .body(sellRequestDto)
            .when()
            .post(SELL_STOCK_URL);
    }

}
