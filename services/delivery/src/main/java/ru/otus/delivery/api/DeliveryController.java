package ru.otus.delivery.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.delivery.api.dto.ScheduleRequest;
import ru.otus.delivery.api.dto.ScheduleResponse;
import ru.otus.delivery.service.DeliveryService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DeliveryController implements DeliveryApi {

    private final DeliveryService deliveryService;

    @Override
    public ScheduleResponse schedule(@Valid ScheduleRequest scheduleRequest) {
        return deliveryService.schedule(scheduleRequest);
    }
}
