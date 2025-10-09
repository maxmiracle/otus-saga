package ru.otus.billing.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MakePaymentRequest {

    @Schema(example = "11111111-1111-1111-1111-000000000001", required = true)
    private UUID orderId;

    @Schema(example = "10.00", required = true)
    @DecimalMin(value = "0.01")
    private BigDecimal amount;

    @Schema(example = "2d2ede68-68fd-456c-9861-c45f46f3be1d", required = true)
    private UUID accountId;
}
