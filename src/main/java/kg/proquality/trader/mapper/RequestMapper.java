package kg.proquality.trader.mapper;

import kg.proquality.trader.dto.BuyRequestDto;
import kg.proquality.trader.dto.SellRequestDto;
import kg.proquality.trader.model.BuyRequest;
import kg.proquality.trader.model.SellRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class RequestMapper {

    public abstract BuyRequest map(BuyRequestDto buyRequestDto);

    public abstract SellRequest map(SellRequestDto sellRequestDto);

}
