package ru.otus.delivery.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.delivery.api.dto.CancelShipmentRequest;
import ru.otus.delivery.api.dto.ScheduleRequest;
import ru.otus.delivery.api.dto.ScheduleResponse;
import ru.otus.delivery.entity.Delivery;
import ru.otus.delivery.exception.DeliveryProcessException;
import ru.otus.delivery.repository.DeliveryRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    final private DeliveryRepository deliveryRepository;

    @Override
    @Transactional
    public ScheduleResponse schedule(ScheduleRequest scheduleRequest) {
        log.info("schedule({})", scheduleRequest);
        if (deliveryRepository.findByOrderId(scheduleRequest.getOrderId()).isPresent()) {
            throw new DeliveryProcessException("Delivery already exists");
        }
        if (deliveryRepository.findByDeliverySlotAndDeliveryDate(scheduleRequest.getDeliverySlot(), scheduleRequest.getDeliveryDate()).isPresent()) {
            throw new DeliveryProcessException("Delivery slot is busy");
        }
        Delivery delivery = Delivery.builder()
                .orderId(scheduleRequest.getOrderId())
                .productId(scheduleRequest.getProductId())
                .accountId(scheduleRequest.getAccountId())
                .quantity(scheduleRequest.getQuantity())
                .deliverySlot(scheduleRequest.getDeliverySlot())
                .deliveryDate(scheduleRequest.getDeliveryDate())
                .build();
        var saved = deliveryRepository.save(delivery);
        return ScheduleResponse.builder()
                .orderId(saved.getOrderId())
                .deliveryId(saved.getId())
                .scheduled(true)
                .build();
    }

    @Override
    @Transactional
    public void cancelShipment(CancelShipmentRequest cancelShipmentRequest) {
        log.info("cancelShipment({})", cancelShipmentRequest);
        Delivery delivery = deliveryRepository.findByOrderIdAndDeliverySlotAndDeliveryDate(cancelShipmentRequest.getOrderId(),
                cancelShipmentRequest.getDeliverySlot(),
                cancelShipmentRequest.getDeliveryDate())
                    .orElseThrow(
                        () -> new DeliveryProcessException("Delivery order not found")
        );
        deliveryRepository.delete(delivery);
    }
}
