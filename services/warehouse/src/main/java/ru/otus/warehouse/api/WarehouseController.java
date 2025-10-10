package ru.otus.warehouse.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.warehouse.api.dto.ReserveRequest;
import ru.otus.warehouse.api.dto.ReserveResponse;
import ru.otus.warehouse.api.dto.RollbackReserveRequest;
import ru.otus.warehouse.service.WarehouseService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class WarehouseController implements WarehouseApi {

    private final WarehouseService warehouseService;

    @Override
    public ReserveResponse reserve(@Valid ReserveRequest reserveRequest) {
        return warehouseService.reserve(reserveRequest);
    }

    @Override
    public void rollbackReserve(RollbackReserveRequest rollbackReserveRequest) {
        warehouseService.rollbackReserve(rollbackReserveRequest);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler( IllegalArgumentException.class )
    public String handleException(IllegalArgumentException ex) {
        return ex.getMessage();
    }

}
