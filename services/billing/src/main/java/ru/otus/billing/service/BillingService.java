package ru.otus.billing.service;

import jakarta.validation.Valid;
import ru.otus.billing.api.dto.MakePaymentRequest;
import ru.otus.billing.api.dto.MakePaymentResponse;

public interface BillingService {
    MakePaymentResponse makePayment(@Valid MakePaymentRequest makePaymentRequest);
}
