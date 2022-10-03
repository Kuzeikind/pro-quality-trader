package kg.proquality.trader.client;

import java.util.Random;
import kg.proquality.trader.client.dto.UserBalanceRequestDto;
import kg.proquality.trader.client.dto.UserBalanceResponseDto;
import org.springframework.stereotype.Service;

@Service
public class UserBalanceClient {

    public UserBalanceResponseDto getUserBalance(UserBalanceRequestDto userBalanceRequestDto) {
        return new UserBalanceResponseDto().setUserBalance(new Random().nextDouble());
    }

}
