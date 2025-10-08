package ru.otus.order.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.order.api.dto.MakeOrderRequest;
import ru.otus.order.api.dto.OrderResponse;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Override
    public OrderResponse makeOrder(MakeOrderRequest makeOrderRequest) {
        log.info("makeOrder");
        return null;
    }
}
