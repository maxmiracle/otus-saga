package ru.otus.order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.order.api.dto.MakeOrderRequest;
import ru.otus.order.api.dto.OrderResponse;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final BillingService billingService;
    private final WarehouseService warehouseService;
    private final DeliveryService deliveryService;

    @Override
    public OrderResponse makeOrder(MakeOrderRequest makeOrderRequest) {
        UUID orderId = UUID.randomUUID();
        log.info("makeOrder orderId = {}, {}", orderId,  makeOrderRequest);
        OrderResponse reservationResponse = warehouseService.reserve(orderId, makeOrderRequest.getProductId(), makeOrderRequest.getQuantity());
        if (!reservationResponse.getIsSuccess()){
            log.info("Reservation fail: {}", reservationResponse.getErrorMessage());
            return reservationResponse;
        }
        OrderResponse scheduleResponse = deliveryService.schedule(orderId, makeOrderRequest.getProductId(),
                makeOrderRequest.getQuantity(), makeOrderRequest.getAccountId(), makeOrderRequest.getDeliveryDate(),
                makeOrderRequest.getDeliverySlot());
        if (!scheduleResponse.getIsSuccess()){
            log.info("Schedule fail: {}", scheduleResponse.getErrorMessage());
            //rollback reservation
            if (warehouseService.rollbackReserve(orderId)){
                log.info("Reservation rollback success {}", orderId);
            }
            return scheduleResponse;
        }
        OrderResponse paymentResponse = billingService.makePayment(orderId, makeOrderRequest.getAccountId(), makeOrderRequest.getAmount());
        if (!paymentResponse.getIsSuccess()){
            log.info("Schedule fail: {}", paymentResponse.getErrorMessage());
            //rollback reservation
            if (warehouseService.rollbackReserve(orderId)){
                log.info("Reservation rollback success for order {}", orderId);
            }
            if (deliveryService.cancelShipment(orderId, makeOrderRequest.getDeliveryDate(), makeOrderRequest.getDeliverySlot())) {
                log.info("Shipment cancel success for order {}", orderId);
            }
            return new OrderResponse(orderId, false, paymentResponse.getErrorMessage());
        }
        return new OrderResponse(orderId, true, null);
    }
}
