package kg.proquality.trader.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class BuyRequestDto {
    private Integer stockId;
    private Integer amount;
}
