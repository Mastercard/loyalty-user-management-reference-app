package com.mastercard.developer.executor;

import com.mastercard.developer.example.AccountExample;
import com.mastercard.developer.exception.ServiceException;
import com.mastercard.developer.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.openapitools.client.model.AccountEnrollRequest;
import org.openapitools.client.model.AccountResponse;
import org.openapitools.client.model.AccountSearchResponse;
import org.openapitools.client.model.Error;
import org.openapitools.client.model.PagedAccountSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This is demo executor class to represent how the Account operations can be perform.
 * You can have your own implementation logic to call Account API.
 */

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

        AccountResponse accountEnrollResponse = enrollAccount();

        searchAccountById(accountEnrollResponse.getReferenceId());

        searchAccount();

        updateAccountStatus(accountEnrollResponse.getReferenceId());

        errorHandling();

        log.info("<<<---- ACCOUNT API EXECUTION COMPLETED ---->>>");
    }

    /**
     * USE CASE 1: ACCOUNT ENROLLMENT
     * It will enroll User's account details into Mastercard Rewards Platform
     *
     * @return An instance of AccountResponse
     */
    private AccountResponse enrollAccount() throws ServiceException {
        return accountService.enroll(AccountExample.getAccountEnrollRequest());
    }

    /**
     * USE CASE 2: ACCOUNT SEARCH
     * It will retrieves User’s account details based on provided search criteria
     *
     * @return An instance of PagedAccountSearchResponse
     */
    private PagedAccountSearchResponse searchAccount() throws ServiceException {
        return accountService.search(AccountExample.getAccountSearchRequest(), 25, 0);
    }

    /**
     * USE CASE 3: ACCOUNT SEARCH BY MASTERCARD GENERATED UNIQUE ID
     * It will retrieves User’s account details by Mastercard generated unique Id
     *
     * @return An instance of AccountSearchResponse
     */
    private AccountSearchResponse searchAccountById(String id) throws ServiceException {
        return accountService.findById(id);
    }

    /**
     * USE CASE 4: ACCOUNT STATUS UPDATE
     * It will updates User's account status into Mastercard Rewards Platform
     *
     * @return An instance of AccountResponse
     */
    private AccountResponse updateAccountStatus(String id) throws ServiceException {
        return accountService.update(id, AccountExample.getAccountUpdateRequest());
    }

    /**
     * USE CASE 5: ERROR HANDLING
     * Enrollment can fail for various reasons, this scenario is added so that you should know what to expect where there is a failure.
     */
    private void errorHandling() {
        AccountEnrollRequest enrollRequest = AccountExample.getAccountEnrollRequest();
        enrollRequest.setAccountId(null);

        try {
            accountService.enroll(enrollRequest);
        } catch (ServiceException e) {
            List<Error> errorList = e.getServiceErrors().getErrors().getError();
            Assertions.assertFalse(errorList.isEmpty());
            errorList.forEach(error -> {
                Assertions.assertEquals("Loyalty-Enrollment", error.getSource());
                Assertions.assertEquals("MISSING_REQUIRED_FIELD", error.getReasonCode());
                log.error("Failed to enroll Account in Error Handling Scenario - {}", error.getDescription());
            });
        }
    }
}
