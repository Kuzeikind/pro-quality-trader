package kg.proquality.e2e.mock;

public interface UserBalanceServiceStub {

    void init();
    void stubForUserBalanceRequest(Integer userId, Double amount);
    void shutDown();

}
