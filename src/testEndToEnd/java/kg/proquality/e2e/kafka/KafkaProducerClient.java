package kg.proquality.e2e.kafka;

import java.util.Properties;
import java.util.concurrent.Future;
import kg.proquality.trader.consumer.UpdateStockPriceEvent;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerClient {

    @Value("${spring.kafka.update-stock-price.topic}")
    private String topic;
    private final KafkaProducer<String, UpdateStockPriceEvent> producer;

    public KafkaProducerClient(Properties producerTestProperties) {
        producer = new KafkaProducer<>(producerTestProperties);
    }

    public Future<RecordMetadata> updateStockPrice(UpdateStockPriceEvent event) {
        ProducerRecord<String, UpdateStockPriceEvent> record = new ProducerRecord<>(topic, event.getTicker(), event);
        return producer.send(record);
    }

}
