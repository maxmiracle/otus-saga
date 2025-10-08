package ru.otus.billing.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.billing.api.dto.MakePaymentRequest;
import ru.otus.billing.api.dto.MakePaymentResponse;
import ru.otus.billing.service.BillingService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BillingController implements BillingApi {

    private final BillingService billingService;

    @Override
    public MakePaymentResponse makePayment(@Valid MakePaymentRequest makePaymentRequest) {
        return billingService.makePayment(makePaymentRequest);
    }
}
