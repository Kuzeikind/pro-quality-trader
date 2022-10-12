package kg.proquality.e2e.mock;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import kg.proquality.trader.client.dto.UserBalanceResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@ConditionalOnProperty(prefix = "user-balance-service", name = "use-mock", havingValue = "true")
public class UserBalanceServiceMock implements UserBalanceServiceStub {

    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final String USER_BALANCE_URL = "/user-balance/%d";

    @Value("${user-balance-service.host}")
    private String host;
    @Value("${user-balance-service.port}")
    private Integer port;

    private final WireMockServer wireMockServer = new WireMockServer();
    private final JsonMapper jsonMapper = new JsonMapper();

    public void init() {
        log.error("Started wiremock server");
        wireMockServer.start();
        configureFor(host, port);
    }

    public void shutDown() {
        wireMockServer.shutdown();
        log.error("Shut down wiremock server");
    }

    @Override
    public void stubForUserBalanceRequest(Integer userId, Double amount) {
        UserBalanceResponseDto response = new UserBalanceResponseDto().setUserBalance(amount);

        try {
            wireMockServer.stubFor(
                get(urlEqualTo(String.format(USER_BALANCE_URL, userId)))
                    .willReturn(aResponse().withStatus(HttpStatus.OK.value())
                                           .withHeader(CONTENT_TYPE_HEADER, MediaType.APPLICATION_JSON_VALUE)
                                           .withBody(jsonMapper.writeValueAsString(response)))
            );
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Could not serialize request");
        }
    }
}
