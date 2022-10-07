package kg.proquality.trader.client;

import kg.proquality.trader.client.dto.UserBalanceResponseDto;
//import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
//@FeignClient(value = "UserBalance", url = "${user-balance-service.host}:${user-balance-service.port}", configuration = UserBalanceDecoder.class)
public interface UserBalanceClient {

    @GetMapping(value = "/user-balance/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserBalanceResponseDto> getUserBalance(@PathVariable("userId") Integer userId);

}
