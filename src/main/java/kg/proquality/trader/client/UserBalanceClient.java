package kg.proquality.trader.client;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import org.springframework.stereotype.Service;

@Service
public class UserBalanceClient {

    private static final String GET_USER_BALANCE_URL = "/user-balance/{userId}";

    public Response getUserBalance(Integer userId) {
        return given()
            .pathParam("userId", userId)
            .when()
            .get(GET_USER_BALANCE_URL);
    }
}
