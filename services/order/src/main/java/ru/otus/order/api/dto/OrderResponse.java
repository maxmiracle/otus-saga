package ru.otus.order.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private UUID orderId;
    private Boolean isSuccess;
    private String errorMessage;

    public static OrderResponse error(UUID orderId, String errorMessage){
        return new OrderResponse(orderId, false, errorMessage);
    }

    public static OrderResponse success(UUID orderId) {
        return new OrderResponse(orderId, true, null);
    }
}
