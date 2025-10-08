package ru.otus.billing.api;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.otus.billing.api.dto.MakePaymentRequest;
import ru.otus.billing.api.dto.MakePaymentResponse;

@Validated
@RequestMapping(path = "/billing")
public interface BillingApi {

    @RequestMapping(method = RequestMethod.POST, value = "/reserve", consumes = {"application/json"})
    default MakePaymentResponse makePayment(@Parameter(name = "ReserveRequest", description = "", required = true) @Valid @RequestBody MakePaymentRequest makePaymentRequest) {
        return null;
    }

}
