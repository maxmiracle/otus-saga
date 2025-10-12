package ru.otus.order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import ru.otus.order.api.dto.*;

import java.time.LocalDate;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeliveryServiceImpl  implements DeliveryService {

    private final RestClient restClient;

    @Value("${schedule-url}")
    private String scheduleUrl;

    @Value("${cancel-shipment-url}")
    private String cancelShipmentUrl;

    public OrderResponse schedule(UUID orderId, UUID productId, Integer quantity, UUID accountId, LocalDate deliveryDate, DeliverySlot deliverySlot){
        try {
            var request = new ScheduleRequest(orderId, productId, quantity, accountId, deliveryDate, deliverySlot);
            log.info("schedule request: {}", request);
            var result = restClient.post()
                    .uri(scheduleUrl)
                    .body(request)
                    .retrieve()
                    .body(ScheduleResponse.class);
            if (result.isScheduled()) {
                log.info("Order {} delivery ", orderId);
                return OrderResponse.success(orderId);
            } else {
                log.info("Order {} not reserved", orderId);
                return OrderResponse.error(orderId, result.getError());
            }
        } catch (HttpClientErrorException ex) {
            log.error("Schedule request failed: {}", ex.getResponseBodyAsString());
            return OrderResponse.error(orderId, ex.getResponseBodyAsString());
        } catch (Exception ex) {
            log.error("Schedule request failed: {}", ex.getMessage());
            return OrderResponse.error(orderId, ex.getMessage());
        }
    }

    public boolean cancelShipment(UUID orderId, LocalDate deliveryDate, DeliverySlot deliverySlot) {
        try {
            var request = new CancelShipmentRequest(orderId, deliveryDate, deliverySlot);

            log.info("Cancel Shipment request: {}", request);
            var result = restClient.post()
                    .uri(cancelShipmentUrl)
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
            log.error("Cancel Shipment failed: {}", ex.getMessage());
            return false;
        }
    }
}
