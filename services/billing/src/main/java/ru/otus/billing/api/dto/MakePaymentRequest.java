package ru.otus.billing.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MakePaymentRequest {
    private UUID orderId;
    private BigDecimal amount;
}
