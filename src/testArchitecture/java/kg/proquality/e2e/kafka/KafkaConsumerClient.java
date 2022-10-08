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
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerClient {

    @Value("${spring.kafka.update-user-balance.topic}")
    private String topic;
    private KafkaConsumer<Integer, UpdateUserBalanceUpdateEvent> kafkaConsumer;
//    private TopicPartition topicPartition;

    public KafkaConsumerClient(Properties consumerTestProperties) {
        kafkaConsumer = new KafkaConsumer<>(consumerTestProperties);
//        topicPartition = new TopicPartition(topic, 0);
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
