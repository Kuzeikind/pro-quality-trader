package kg.proquality.trader.controller;

import kg.proquality.trader.dto.AppError;
import kg.proquality.trader.exception.NeedMoreMoneyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "kg.proquality.trader")
public class TraderControllerAdvice {

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(NeedMoreMoneyException.class)
//    public AppError handleNeedMoreMoneyException(NeedMoreMoneyException needMoreMoneyException) {
//        return new AppError()
//            .setErrorMessage(needMoreMoneyException.getMessage());
//    }

}
