package ru.otus.order.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import ru.otus.order.api.dto.MakeOrderRequest;
import ru.otus.order.api.dto.OrderResponse;
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
        return orderService.makeOrder(makeOrderRequest);
    }


    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(HttpClientErrorException.UnprocessableEntity.class)
    public String handleException(HttpClientErrorException.UnprocessableEntity ex) {
        return ex.getMessage();
    }
}
