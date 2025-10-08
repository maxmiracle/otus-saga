package ru.otus.order.service;

import jakarta.validation.Valid;

import ru.otus.order.api.dto.MakeOrderRequest;
import ru.otus.order.api.dto.OrderResponse;

public interface OrderService {
    OrderResponse makeOrder(@Valid MakeOrderRequest makeOrderRequest);
}
