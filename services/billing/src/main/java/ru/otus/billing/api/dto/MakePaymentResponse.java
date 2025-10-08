package ru.otus.billing.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MakePaymentResponse {
    private UUID orderId;
    private Instant reservedAt;
    private UUID reservationId;
    private boolean reserved;
    private String error;
}
