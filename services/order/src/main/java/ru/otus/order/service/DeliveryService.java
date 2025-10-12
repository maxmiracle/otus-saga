package ru.otus.order.service;

import ru.otus.order.api.dto.DeliverySlot;
import ru.otus.order.api.dto.OrderResponse;

import java.time.LocalDate;
import java.util.UUID;

public interface DeliveryService {
    OrderResponse schedule(UUID orderId, UUID productId, Integer quantity, UUID accountId, LocalDate deliveryDate, DeliverySlot deliverySlot);

    boolean cancelShipment(UUID orderId, LocalDate deliveryDate, DeliverySlot deliverySlot);
}
