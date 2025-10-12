package ru.otus.order.service;

import ru.otus.order.api.dto.OrderResponse;

import java.math.BigDecimal;
import java.util.UUID;

public interface BillingService {
    OrderResponse makePayment(UUID orderId, UUID accountId, BigDecimal amount);
}
