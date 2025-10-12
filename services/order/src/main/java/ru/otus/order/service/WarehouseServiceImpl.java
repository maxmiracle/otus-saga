package ru.otus.order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import ru.otus.order.api.dto.OrderResponse;
import ru.otus.order.api.dto.ReserveRequest;
import ru.otus.order.api.dto.ReserveResponse;
import ru.otus.order.api.dto.RollbackReserveRequest;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {

    private final RestClient restClient;

    @Value("${reserve-url}")
    private String reserveUrl;

    @Value("${rollback-reserve-url}")
    private String rollbackReserveUrl;

    public OrderResponse reserve(UUID orderId, UUID productId, Integer quantity){
        try {
            var request = new ReserveRequest(orderId, productId, quantity);
            log.info("url: {}", reserveUrl);
            var result = restClient.post()
                    .uri(reserveUrl)
                    .body(request)
                    .retrieve()
                    .body(ReserveResponse.class);
            if (result.isReserved()) {
                    log.info("Order {} reserved", orderId);
                    return OrderResponse.success(orderId);
                } else {
                    log.info("Order {} not reserved", orderId);
                    return OrderResponse.error(orderId, result.getError());
                }
        } catch (HttpClientErrorException ex) {
            log.error("Make order request failed: {}", ex.getResponseBodyAsString());
            return OrderResponse.error(orderId, ex.getResponseBodyAsString());
        } catch (Exception ex) {
            log.error("Make order request failed: {}", ex.getMessage());
            return OrderResponse.error(orderId, ex.getMessage());
        }
    }

    public boolean rollbackReserve(UUID orderId){
        try {
            var request = new RollbackReserveRequest(orderId);
            var result = restClient.post()
                    .uri(rollbackReserveUrl)
                    .body(request)
                    .retrieve()
                    .toBodilessEntity();
            var resultCode = result.getStatusCode();
            if (resultCode.is2xxSuccessful()) {
                return true;
            } else {
                return false;
            }
        } catch (HttpClientErrorException ex) {
            log.error("Make order request failed: {}", ex.getMessage());
            return false;
        }
    }
}
