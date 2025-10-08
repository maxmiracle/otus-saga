package ru.otus.delivery.service;

import jakarta.validation.Valid;
import ru.otus.delivery.api.dto.ReserveRequest;
import ru.otus.delivery.api.dto.ReserveResponse;

public interface WarehouseService {
    ReserveResponse reserve(@Valid ReserveRequest reserveRequest);
}
