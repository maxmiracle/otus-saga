package ru.otus.billing.exception;

import lombok.Getter;

import java.util.UUID;

public class MakePaymentException extends RuntimeException {
    @Getter
    UUID orderId;

    @Getter
    UUID accountId;

    public MakePaymentException(String message, UUID orderId, UUID accountId) {
        super(message);
        this.orderId = orderId;
        this.accountId = accountId;

    }
}
