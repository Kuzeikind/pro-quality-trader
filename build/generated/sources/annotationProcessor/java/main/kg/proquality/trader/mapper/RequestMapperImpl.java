package kg.proquality.trader.mapper;

import javax.annotation.processing.Generated;
import kg.proquality.trader.dto.BuyRequestDto;
import kg.proquality.trader.dto.SellRequestDto;
import kg.proquality.trader.model.BuyRequest;
import kg.proquality.trader.model.SellRequest;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-08T22:59:56+0600",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.1 (Oracle Corporation)"
)
@Component
public class RequestMapperImpl extends RequestMapper {

    @Override
    public BuyRequest map(BuyRequestDto buyRequestDto) {
        if ( buyRequestDto == null ) {
            return null;
        }

        BuyRequest buyRequest = new BuyRequest();

        buyRequest.setStockId( buyRequestDto.getStockId() );
        buyRequest.setAmount( buyRequestDto.getAmount() );

        return buyRequest;
    }

    @Override
    public SellRequest map(SellRequestDto sellRequestDto) {
        if ( sellRequestDto == null ) {
            return null;
        }

        SellRequest sellRequest = new SellRequest();

        sellRequest.setStockId( sellRequestDto.getStockId() );
        sellRequest.setAmount( sellRequestDto.getAmount() );

        return sellRequest;
    }
}
