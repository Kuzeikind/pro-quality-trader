package kg.proquality.trader.service;

import java.util.Optional;
import kg.proquality.trader.client.UserBalanceClient;
import kg.proquality.trader.consumer.UpdateStockPriceEvent;
import kg.proquality.trader.exception.NeedMoreMoneyException;
import kg.proquality.trader.exception.NotEnoughSharesToSellException;
import kg.proquality.trader.model.BuyRequest;
import kg.proquality.trader.model.SellRequest;
import kg.proquality.trader.model.Stock;
import kg.proquality.trader.model.User;
import kg.proquality.trader.producer.UpdateUserBalanceProducer;
import kg.proquality.trader.producer.UpdateUserBalanceUpdateEvent;
import kg.proquality.trader.repository.StockRepository;
import kg.proquality.trader.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockOperationService {

    private final UserBalanceClient userBalanceClient;
    private final StockRepository stockRepository;
    private final UserRepository userRepository;
    private final UpdateUserBalanceProducer producer;

    public void buyStock(BuyRequest buyRequest) {
        User user = userRepository.findById(buyRequest.getUserId()).orElseThrow();
        Stock stock = stockRepository.findById(buyRequest.getStockId()).orElseThrow();

        Double requiredCash = checkIfUserHasMoney(user, stock, buyRequest.getAmount());
        Integer boughtShares = user.getBoughtStocks().getOrDefault(stock, 0);
        user.getBoughtStocks().put(stock, boughtShares + buyRequest.getAmount());

        //TODO: should probably be an API call and should check the response
        producer.updateUserBalance(
            new UpdateUserBalanceUpdateEvent().setBalanceUpdate(-requiredCash).setUserId(user.getId())
        );

        userRepository.save(user);
    }

    public void sellStock(SellRequest sellRequest) {
        User user = userRepository.findById(sellRequest.getUserId()).orElseThrow();
        Stock stock = stockRepository.findById(sellRequest.getStockId()).orElseThrow();

        checkIfHasEnoughShares(user, stock, sellRequest.getAmount());
        user.getBoughtStocks().compute(stock, (k, v) -> v - sellRequest.getAmount());

        Double spentCash = stock.getSellPrice() * sellRequest.getAmount();
        producer.updateUserBalance(
            new UpdateUserBalanceUpdateEvent().setBalanceUpdate(spentCash).setUserId(user.getId())
        );

        userRepository.save(user);
    }

    public void updateStockPrice(UpdateStockPriceEvent event) {
        stockRepository.findByTicker(event.getTicker())
                       .ifPresentOrElse(
                           s -> stockRepository.save(
                               s.setSellPrice(event.getSellPrice())
                                .setBuyPrice(event.getBuyPrice())
                           ),
                           () -> log.warn("Did not find stock with ticker: {} ", event.getTicker())
                       );
    }

    private Double checkIfUserHasMoney(User user, Stock stock, Integer amountToBuy) {
        Double userBalance =
            userBalanceClient.getUserBalance(user.getId()).jsonPath().getDouble("userBalance");

        Double requiredCash = stock.getBuyPrice() * amountToBuy;

        if (Double.compare(userBalance, requiredCash) < 0) {
            throw new NeedMoreMoneyException(requiredCash);
        }
        return requiredCash;
    }

    private Integer checkIfHasEnoughShares(User user, Stock stock, Integer amountToSell) {
        Integer boughtShares = user.getBoughtStocks().getOrDefault(stock, 0);
        if (boughtShares < amountToSell) {
            throw new NotEnoughSharesToSellException(amountToSell, boughtShares);
        }
        return boughtShares;
    }
}
