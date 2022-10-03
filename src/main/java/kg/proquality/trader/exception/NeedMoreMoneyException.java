package kg.proquality.trader.exception;

public class NeedMoreMoneyException extends RuntimeException {

    private static final String errorMessage = "Not enough money to buy stock. Required sum: %f.2";

    public NeedMoreMoneyException(Double requiredCash) {
        super(String.format(errorMessage, requiredCash));
    }

}
