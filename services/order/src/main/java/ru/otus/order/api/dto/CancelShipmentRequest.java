package ru.otus.order.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CancelShipmentRequest {
    @Schema(example = "11111111-1111-1111-1111-000000000001")
    private UUID orderId;
    @Schema(example = "2025-10-31")
    private LocalDate deliveryDate;
    @Schema(example = "MORNING")
    private DeliverySlot deliverySlot;
}
