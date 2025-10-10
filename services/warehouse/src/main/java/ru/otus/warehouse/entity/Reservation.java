package ru.otus.warehouse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Data
@Entity(name = "reservation")
@NoArgsConstructor
public class Reservation {

    public Reservation(UUID orderId, UUID productId, Integer quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.isActive = true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private UUID orderId;
    private UUID productId;
    private Integer quantity;
    @CreationTimestamp
    private Instant createdAt;
    private Boolean isActive;
    private String note;
}
