package ru.otus.delivery.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.delivery.api.dto.CancelShipmentRequest;
import ru.otus.delivery.api.dto.ScheduleRequest;
import ru.otus.delivery.api.dto.ScheduleResponse;
import ru.otus.delivery.exception.DeliveryProcessException;
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

    @Override
    public void cancelShipment(@Valid CancelShipmentRequest cancelShipmentRequest) {
        deliveryService.cancelShipment(cancelShipmentRequest);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler( IllegalArgumentException.class )
    public String handleException(IllegalArgumentException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler( DeliveryProcessException.class )
    public String handleException(DeliveryProcessException ex) {
        return ex.getMessage();
    }
}
