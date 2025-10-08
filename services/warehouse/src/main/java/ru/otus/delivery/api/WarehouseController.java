package ru.otus.delivery.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.delivery.api.dto.ReserveRequest;
import ru.otus.delivery.api.dto.ReserveResponse;
import ru.otus.delivery.service.WarehouseService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class WarehouseController implements WarehouseApi {

    private final WarehouseService warehouseService;

    @Override
    public ReserveResponse reserve(@Valid ReserveRequest reserveRequest) {
        return warehouseService.reserve(reserveRequest);
    }
}
