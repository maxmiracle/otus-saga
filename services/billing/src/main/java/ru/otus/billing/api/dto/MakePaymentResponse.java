package ru.otus.billing.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MakePaymentResponse {
    private UUID orderId;
    private boolean success;
    private String error;
}
