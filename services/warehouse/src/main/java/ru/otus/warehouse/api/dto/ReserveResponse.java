package ru.otus.warehouse.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReserveResponse {
    private UUID orderId;
    private Instant reservedAt;
    private UUID reservationId;
    private boolean reserved;
    private String error;
}
