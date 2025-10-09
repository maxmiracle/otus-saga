package ru.otus.billing.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.billing.api.dto.MakePaymentRequest;
import ru.otus.billing.api.dto.MakePaymentResponse;
import ru.otus.billing.entity.Account;
import ru.otus.billing.exception.MakePaymentException;
import ru.otus.billing.repository.AccountRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class BillingServiceImpl implements BillingService {

    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public MakePaymentResponse makePayment(MakePaymentRequest makePaymentRequest) {
        log.info("makePayment({})", makePaymentRequest);
        Account userAccount = accountRepository.findById(makePaymentRequest.getAccountId())
                .orElseThrow(() -> new MakePaymentException("Account not found", makePaymentRequest.getOrderId(), makePaymentRequest.getAccountId()));
        if (userAccount.getBalance().compareTo(makePaymentRequest.getAmount()) < 0) {
            throw new MakePaymentException("Not enough money", makePaymentRequest.getOrderId(), makePaymentRequest.getAccountId());
        }
        userAccount.setBalance(userAccount.getBalance().subtract(makePaymentRequest.getAmount()));
        accountRepository.save(userAccount);
        return new MakePaymentResponse(makePaymentRequest.getOrderId(), true, null);
    }
}
