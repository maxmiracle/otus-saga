package ru.otus.delivery.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReserveRequest {
    private UUID orderId;
    private UUID productId;
    private Integer quantity;
}
