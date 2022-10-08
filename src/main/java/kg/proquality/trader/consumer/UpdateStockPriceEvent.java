package kg.proquality.trader.consumer;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UpdateStockPriceEvent {

    private String ticker;
    private Double sellPrice;
    private Double buyPrice;

}
