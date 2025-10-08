package ru.otus.billing.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.billing.api.dto.MakePaymentRequest;
import ru.otus.billing.api.dto.MakePaymentResponse;

@Service
@Slf4j
public class BillingServiceImpl implements BillingService {

    @Override
    public MakePaymentResponse makePayment(MakePaymentRequest makePaymentRequest) {
        log.info("reserve({})", makePaymentRequest);
        return null;
    }
}
