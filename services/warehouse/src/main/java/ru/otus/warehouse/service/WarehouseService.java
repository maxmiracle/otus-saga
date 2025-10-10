package ru.otus.warehouse.service;

import jakarta.validation.Valid;
import ru.otus.warehouse.api.dto.ReserveRequest;
import ru.otus.warehouse.api.dto.ReserveResponse;
import ru.otus.warehouse.api.dto.RollbackReserveRequest;

public interface WarehouseService {
    ReserveResponse reserve(@Valid ReserveRequest reserveRequest);

    void rollbackReserve(RollbackReserveRequest rollbackReserveRequest);
}
