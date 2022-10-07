package kg.proquality.trader.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class SellRequestDto {
    private Integer stockId;
    private Integer amount;
}
