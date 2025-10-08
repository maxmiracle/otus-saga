package ru.otus.delivery.service;

import jakarta.validation.Valid;
import ru.otus.delivery.api.dto.ScheduleRequest;
import ru.otus.delivery.api.dto.ScheduleResponse;

public interface DeliveryService {
    ScheduleResponse schedule(@Valid ScheduleRequest reserveRequest);
}
