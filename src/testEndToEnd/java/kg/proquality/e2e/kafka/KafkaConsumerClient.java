package kg.proquality.e2e.kafka;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.annotation.PostConstruct;
import kg.proquality.trader.producer.UpdateUserBalanceUpdateEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerClient {

    @Value("${spring.kafka.update-user-balance.topic}")
    private String topic;
    private KafkaConsumer<Integer, UpdateUserBalanceUpdateEvent> kafkaConsumer;

    public KafkaConsumerClient(Properties consumerTestProperties) {
        kafkaConsumer = new KafkaConsumer<>(consumerTestProperties);
    }

    @PostConstruct
    private void init() {
        kafkaConsumer.subscribe(List.of(topic));
    }

    public List<ConsumerRecord<Integer, UpdateUserBalanceUpdateEvent>> poll() {
        List<ConsumerRecord<Integer, UpdateUserBalanceUpdateEvent>> records = new ArrayList<>();
        for (var r : kafkaConsumer.poll(Duration.of(5, ChronoUnit.SECONDS)).records(topic)) {
            records.add(r);
        }
        return records;
    }

}
