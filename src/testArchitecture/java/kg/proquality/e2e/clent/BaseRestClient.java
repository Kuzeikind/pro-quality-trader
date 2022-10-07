package kg.proquality.e2e.clent;

import static java.nio.charset.StandardCharsets.UTF_8;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;

public abstract class BaseRestClient {
    protected static final String HOST = "localhost";

    @Value("${server.port}")
    protected int port;
    protected String baseUrl;

    @PostConstruct
    public void createBaseUrl() {
        this.baseUrl = String.format("http://%s:%d", HOST, port);
    }

    protected RequestSpecification given() {
        RestAssuredConfig config = RestAssured.config()
                                              .encoderConfig(EncoderConfig.encoderConfig().defaultContentCharset(UTF_8))
                                              .objectMapperConfig(ObjectMapperConfig.objectMapperConfig()
                                                                                    .defaultObjectMapperType(
                                                                                        ObjectMapperType.JACKSON_2))
                                              .httpClient(
                                                  new HttpClientConfig().setParam(
                                                      "CONNECTION_MANAGER_TIMEOUT",
                                                      5000)
                                              );

        return RestAssured.given()
                          .config(config)
                          .baseUri(baseUrl)
                          .contentType(ContentType.JSON)
                          .filters(new RequestLoggingFilter(), new ResponseLoggingFilter(), new ErrorLoggingFilter());
    }
}
