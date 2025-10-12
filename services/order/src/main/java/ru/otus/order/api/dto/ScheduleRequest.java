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
public class ScheduleRequest {
    @Schema(example = "11111111-1111-1111-1111-000000000001")
    private UUID orderId;
    @Schema(example = "11112222-aaaa-bbbb-cccc-dddd00001111")
    private UUID productId;
    @Schema(example = "1")
    private Integer quantity;
    @Schema(example = "2d2ede68-68fd-456c-9861-c45f46f3be1d")
    private UUID accountId;
    @Schema(example = "2025-10-31")
    private LocalDate deliveryDate;
    @Schema(example = "MORNING")
    private DeliverySlot deliverySlot;
}
