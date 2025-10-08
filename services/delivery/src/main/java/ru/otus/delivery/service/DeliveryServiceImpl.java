package ru.otus.delivery.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.delivery.api.dto.ScheduleRequest;
import ru.otus.delivery.api.dto.ScheduleResponse;

@Service
@Slf4j
public class DeliveryServiceImpl implements ru.otus.delivery.service.DeliveryService {

    @Override
    public ScheduleResponse schedule(ScheduleRequest scheduleRequest) {
        log.info("reserve({})", scheduleRequest);
        return null;
    }
}
