package ru.otus.order.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private UUID accountId;
    private UUID productId;
    private Integer quantity;
    private LocalDate date;
    private DeliverySlot deliverySlot;
    private Double amount;
}
