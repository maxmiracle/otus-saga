package ru.otus.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.delivery.entity.Delivery;
import ru.otus.delivery.entity.DeliverySlot;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, UUID> {
    Optional<Delivery> findByOrderIdAndDeliverySlotAndDeliveryDate(UUID orderId, DeliverySlot deliverySlot, LocalDate deliveryDate);
    Optional<Delivery>  findByDeliverySlotAndDeliveryDate(DeliverySlot deliverySlot, LocalDate deliveryDate);
    Optional<Delivery>  findByOrderId(UUID orderId);
}
