package kg.proquality.trader.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class BuyRequest {
    private Integer userId;
    private Integer stockId;
    private Integer amount;
}
