package ru.otus.order.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import ru.otus.order.api.dto.MakeOrderRequest;
import ru.otus.order.api.dto.OrderResponse;
import ru.otus.order.exception.OrderProcessException;
import ru.otus.order.service.OrderService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MakeOrderController implements MakeOrderApi {

    private final OrderService orderService;
    @Value("${make-payment-url}")
    private String makePaymentUrl;

    @Override
    public OrderResponse makeOrder(@Valid MakeOrderRequest makeOrderRequest) {
        OrderResponse orderResponse = orderService.makeOrder(makeOrderRequest);
        if (orderResponse.getIsSuccess()) {
            return orderResponse;
        } else {
            throw new OrderProcessException(orderResponse.getErrorMessage());
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OrderProcessException.class)
    public String handleException(OrderProcessException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(HttpClientErrorException.UnprocessableEntity.class)
    public String handleException(HttpClientErrorException.UnprocessableEntity ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> handleException(HttpClientErrorException ex) {
        return new ResponseEntity<>(ex.getMessage(), ex.getStatusCode());
    }
}
