package kg.proquality.trader.client.dto;

import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Accessors(chain = true)
public class UserBalanceRequestDto {

    private Integer userId;

}
