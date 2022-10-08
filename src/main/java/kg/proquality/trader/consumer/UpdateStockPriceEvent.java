package kg.proquality.trader.consumer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@ToString
public class UpdateStockPriceEvent {

    private String ticker;
    private Double sellPrice;
    private Double buyPrice;

}
