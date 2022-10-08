package kg.proquality.trader.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
@Slf4j
@RequiredArgsConstructor
public class UpdateUserBalanceProducer {

    @Value("${spring.kafka.update-user-balance.topic}")
    private String topic;

    private final KafkaTemplate<String, UpdateUserBalanceUpdateRequest> kafkaTemplate;

    public ListenableFuture<SendResult<String, UpdateUserBalanceUpdateRequest>> updateUserBalance(UpdateUserBalanceUpdateRequest request) {
        ListenableFuture<SendResult<String, UpdateUserBalanceUpdateRequest>> sent =
            kafkaTemplate.send(topic, request.getUserId().toString(), request);

        sent.addCallback(
            result -> log.info("Sent message with payload: {}", request),
            throwable -> log.error("Failed to send message:", throwable)
        );
        return sent;
    }

}
