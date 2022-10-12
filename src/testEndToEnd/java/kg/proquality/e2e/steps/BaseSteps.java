package kg.proquality.e2e.steps;

import kg.proquality.e2e.clent.TraderRestClient;
import kg.proquality.e2e.context.TestContext;
import kg.proquality.e2e.db.PostgresClient;
import kg.proquality.e2e.kafka.KafkaConsumerClient;
import kg.proquality.e2e.kafka.KafkaProducerClient;
import kg.proquality.e2e.mock.UserBalanceServiceStub;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseSteps {

    @Autowired
    protected PostgresClient postgresClient;

    @Autowired
    protected TraderRestClient traderRestClient;

    @Autowired
    protected TestContext testContext;

//    @Autowired
//    protected UserBalanceServiceMock userBalanceServiceMock;

    @Autowired
    protected UserBalanceServiceStub userBalanceService;

    @Autowired
    protected KafkaConsumerClient kafkaConsumerClient;

    @Autowired
    protected KafkaProducerClient kafkaProducerClient;

}
