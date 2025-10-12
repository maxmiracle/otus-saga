package ru.otus.delivery.api;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.otus.delivery.api.dto.CancelShipmentRequest;
import ru.otus.delivery.api.dto.ScheduleRequest;
import ru.otus.delivery.api.dto.ScheduleResponse;

@Validated
@RequestMapping(path = "/delivery")
public interface DeliveryApi {

    @RequestMapping(method = RequestMethod.POST, value = "/schedule", consumes = {"application/json"})
    default ScheduleResponse schedule(@Parameter(name = "scheduleRequest", description = "scheduleRequest", required = true) @Valid @RequestBody ScheduleRequest scheduleRequest) {
        return null;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/cancelShipment", consumes = {"application/json"})
    default void cancelShipment(@RequestBody @Valid CancelShipmentRequest cancelShipmentRequest) {
        return;
    }

}
