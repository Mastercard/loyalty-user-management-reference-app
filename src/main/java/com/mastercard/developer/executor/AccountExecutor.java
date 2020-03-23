package com.mastercard.developer.executor;

import com.mastercard.developer.example.AccountExample;
import com.mastercard.developer.exception.ServiceException;
import com.mastercard.developer.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.client.model.AccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AccountExecutor {

    private AccountService accountService;

    @Autowired
    public AccountExecutor(AccountService accountService) {
        this.accountService = accountService;
    }

    public void execute() throws ServiceException {
        log.info("<<<---- ACCOUNT API EXECUTION STARTED ---->>>");
        AccountResponse accountEnrollResponse = accountService.enroll(AccountExample.getAccountEnrollRequest());

        accountService.findById(accountEnrollResponse.getReferenceId());

        accountService.search(AccountExample.getAccountSearchRequest(), 25, 0);

        accountService.update(accountEnrollResponse.getReferenceId(), AccountExample.getAccountUpdateRequest());
        log.info("<<<---- ACCOUNT API EXECUTION COMPLETED ---->>>");
    }
}
