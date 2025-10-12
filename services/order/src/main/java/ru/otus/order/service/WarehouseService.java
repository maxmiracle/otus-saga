package ru.otus.order.service;

import ru.otus.order.api.dto.OrderResponse;

import java.util.UUID;

public interface WarehouseService {
    OrderResponse reserve(UUID orderId, UUID productId, Integer quantity);

    boolean rollbackReserve(UUID orderId);
}
