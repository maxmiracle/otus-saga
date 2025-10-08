package ru.otus.delivery.api;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.otus.delivery.api.dto.ReserveRequest;
import ru.otus.delivery.api.dto.ReserveResponse;

@Validated
@RequestMapping(path = "/warehouse")
public interface WarehouseApi {

    @RequestMapping(method = RequestMethod.POST, value = "/reserve", consumes = {"application/json"})
    default ReserveResponse reserve(@Parameter(name = "ReserveRequest", description = "", required = true) @Valid @RequestBody ReserveRequest reserveRequest) {
        return null;
    }

}
