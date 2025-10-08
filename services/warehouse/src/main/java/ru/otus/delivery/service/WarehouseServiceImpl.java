package ru.otus.delivery.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.delivery.api.dto.ReserveRequest;
import ru.otus.delivery.api.dto.ReserveResponse;

@Service
@Slf4j
public class WarehouseServiceImpl implements WarehouseService {

    @Override
    public ReserveResponse reserve(ReserveRequest reserveRequest) {
        log.info("reserve({})", reserveRequest);
        return null;
    }
}
