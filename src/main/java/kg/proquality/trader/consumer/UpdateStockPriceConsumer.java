package kg.proquality.trader.consumer;

import kg.proquality.trader.service.StockOperationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateStockPriceConsumer {

    private final StockOperationService stockOperationService;

//    @KafkaListener(topicPartitions = {
//        @TopicPartition(
//            topic = "${spring.kafka.update-stock-price.topic}",
//            partitions = "0"
//        )
//    })
    @KafkaListener(
        topics = "${spring.kafka.update-stock-price.topic}",
        groupId = "ms-trader"
    )
    public void listen(@Payload final UpdateStockPriceEvent updateStockPriceEvent) {
        stockOperationService.updateStockPrice(updateStockPriceEvent);
        log.debug("Received updated stock price event: {}", updateStockPriceEvent);
    }
}
