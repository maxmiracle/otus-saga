package ru.otus.order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import ru.otus.order.api.dto.*;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class BillingServiceImpl implements BillingService {

    @Value("${make-payment-url}")
    private String makePaymentUrl;

    private final RestClient restClient;

    public OrderResponse makePayment(UUID orderId, UUID accountId, BigDecimal amount){
        try {
            var request = new MakePaymentRequest(orderId, amount, accountId);
            log.info("makePayment: {}", request);
            var result = restClient.post()
                    .uri(makePaymentUrl)
                    .body(request)
                    .retrieve()
                    .body(MakePaymentResponse.class);
            if (result.isSuccess()) {
                log.info("Order {} reserved", orderId);
                return OrderResponse.success(orderId);
            } else {
                log.info("Order {} not reserved", orderId);
                return OrderResponse.error(orderId, result.getError());
            }
        } catch (Exception ex) {
            log.error("Make order request failed: {}", ex.getMessage());
            return OrderResponse.error(orderId, ex.getMessage());
        }
    }
}
