package kg.proquality.trader.controller;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import kg.proquality.trader.dto.BuyRequestDto;
import kg.proquality.trader.dto.SellRequestDto;
import kg.proquality.trader.model.Stock;
import kg.proquality.trader.model.User;
import kg.proquality.trader.repository.StockRepository;
import kg.proquality.trader.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/trader")
@RequiredArgsConstructor
public class TraderController {

    private final UserRepository userRepository;
    private final StockRepository stockRepository;

    @GetMapping("{userId}/stocks")
    public Set<Stock> getUserStocks(@PathVariable("userId") Integer userid) {
        return userRepository.findById(userid).map(u -> u.getBoughtStocks().keySet()).orElseThrow();
    }

    @GetMapping("{userId}/balance")
    public void getUserBalance(@PathVariable("userId") Integer userid) {

    }

    @PostMapping("{userId}/stocks/buy")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void buyStock(@PathVariable("userId") Integer userid, @RequestBody BuyRequestDto buyRequestDto) {

    }

    @PostMapping("{userId}/stocks/sell")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void sellStock(@PathVariable("userId") Integer userid, @RequestBody SellRequestDto sellRequestDto) {

    }

    //TODO: TEST

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/stocks")
    public List<Stock> getStocks() {
        return stockRepository.findAll();
    }


}
