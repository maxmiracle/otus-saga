package ru.otus.warehouse.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RollbackReserveRequest {
    @NotNull
    @Schema(description = "Order id", example = "123e4567-e89b-12d3-a456-426655440000")
    private UUID orderId;
}

