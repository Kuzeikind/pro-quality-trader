package kg.proquality.trader.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuyRequest {
    private Integer userId;
    private Integer stockId;
    private Integer amount;
}
