package ru.otus.delivery.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity(name = "delivery")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    /**
     * Account ID customer
     */
    @Version
    private Integer version;
    private UUID orderId;
    private UUID productId;
    private Integer quantity;
    private UUID accountId;
    private LocalDate deliveryDate;
    @Enumerated(EnumType.STRING)
    private DeliverySlot deliverySlot;
}
