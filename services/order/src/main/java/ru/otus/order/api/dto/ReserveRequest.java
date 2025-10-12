package ru.otus.order.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReserveRequest {

    @NotNull
    @Schema(description = "OrderId", example = "123e4567-e89b-12d3-a456-426655440000")
    private UUID orderId;

    @NotNull
    @Schema(description = "ProductId", example = "11112222-aaaa-bbbb-cccc-dddd00001111")
    private UUID productId;

    @NotNull
    @Schema(description = "Quantity", example = "1")
    private Integer quantity;
}
