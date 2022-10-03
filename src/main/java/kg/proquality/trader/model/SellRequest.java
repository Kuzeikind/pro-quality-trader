package kg.proquality.trader.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SellRequest {
    private Integer userId;
    private Integer stockId;
    private Integer amount;
}
