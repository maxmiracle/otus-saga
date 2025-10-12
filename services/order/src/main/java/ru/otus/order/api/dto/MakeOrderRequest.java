package ru.otus.order.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MakeOrderRequest {
    @Schema(description = "AccountId", example = "00000000-aaaa-bbbb-cccc-dddd00001111")
    private UUID accountId;
    @Schema(description = "ProductId", example = "11112222-aaaa-bbbb-cccc-dddd00001111")
    private UUID productId;
    @Schema(description = "Quantity", example = "1")
    private Integer quantity;
    @Schema(description = "DeliveryDate", example = "2025-10-31")
    private LocalDate deliveryDate;
    @Schema(description = "DeliverySlot", example = "MORNING")
    private DeliverySlot deliverySlot;
    @Schema(description = "Amount", example = "100.00")
    private BigDecimal amount;
}
