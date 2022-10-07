//package kg.proquality.trader.client;
//
//import feign.Response;
//import feign.codec.ErrorDecoder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class UserBalanceDecoder implements ErrorDecoder {
//
//    @Override
//    public RuntimeException decode(final String methodKey, final Response response) {
//        return new RuntimeException(
//                String.format(
//                        "Method key: %s, status: %s, body: %s",
//                        methodKey,
//                        response.status(),
//                        response.body() != null ? response.body().toString() : ""
//                )
//        );
//    }
//}
