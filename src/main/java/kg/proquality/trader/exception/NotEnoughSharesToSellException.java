package kg.proquality.trader.exception;

public class NotEnoughSharesToSellException extends RuntimeException {

    private static final String errorMessage = "Tried to sell %d shares, but only has %d";

    public NotEnoughSharesToSellException(Integer amountToSell, Integer boughtShares) {
        super(String.format(errorMessage, amountToSell, boughtShares));
    }

}
