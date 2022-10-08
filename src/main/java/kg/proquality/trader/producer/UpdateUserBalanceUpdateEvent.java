package kg.proquality.trader.producer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@ToString
public class UpdateUserBalanceUpdateEvent {

    private Integer userId;
    private Double balanceUpdate;

}
