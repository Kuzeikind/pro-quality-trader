package kg.proquality.e2e.context;

import io.cucumber.spring.ScenarioScope;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * The test context persists data between steps of a scenario. This data is stored in TestContext.data map.
 */
@Component
@ScenarioScope
@Getter
public class TestContext {

    public static final String CURRENT_USER = "currentUser";
    public static final String CURRENT_STOCK = "currentStock";

    private Response response;
    private Map<String, Object> data = new HashMap<>();

    public void setResponse(Response response) {
        this.response = response;
    }

    public void put(String key, Object value) {
        data.put(key, value);
    }

    public <T> T get(String key) {
        return (T) data.get(key);
    }
}
