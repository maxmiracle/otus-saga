package ru.otus.delivery.api;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.otus.delivery.api.dto.ScheduleRequest;
import ru.otus.delivery.api.dto.ScheduleResponse;

@Validated
@RequestMapping(path = "/delivery")
public interface DeliveryApi {

    @RequestMapping(method = RequestMethod.POST, value = "/reserve", consumes = {"application/json"})
    default ScheduleResponse schedule(@Parameter(name = "ReserveRequest", description = "", required = true) @Valid @RequestBody ScheduleRequest scheduleRequest) {
        return null;
    }

}
