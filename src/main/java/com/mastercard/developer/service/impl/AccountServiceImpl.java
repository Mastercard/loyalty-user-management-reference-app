package com.mastercard.developer.service.impl;

import com.mastercard.developer.exception.ServiceException;
import com.mastercard.developer.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.JSON;
import org.openapitools.client.api.AccountApi;
import org.openapitools.client.model.AccountEnrollRequest;
import org.openapitools.client.model.AccountResponse;
import org.openapitools.client.model.AccountSearchRequest;
import org.openapitools.client.model.AccountSearchResponse;
import org.openapitools.client.model.AccountUpdateRequest;
import org.openapitools.client.model.Errors;
import org.openapitools.client.model.PagedAccountSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    private AccountApi accountApi;

    private JSON json;

    @Autowired
    public AccountServiceImpl(ApiClient apiClient) {
        log.info("-->> INITIALIZING ACCOUNT API");
        accountApi = new AccountApi(apiClient);
        json = new JSON();
    }

    /**
     * Enrolls a new account of an existing user for company
     * URL: /accounts
     * Method: POST
     * Success Response: 201(CREATED)
     * Error Response: 4XX or 5XX
     *
     * @param accountEnrollRequest Account enrollment request (required)
     * @return An instance of AccountResponse
     * @throws ServiceException If error occurred while calling account enroll endpoint
     */
    @Override
    public AccountResponse enroll(AccountEnrollRequest accountEnrollRequest) throws ServiceException {
        try {
            log.info("<-- CALLING ACCOUNT ENROLLMENT ENDPOINT -->");
            AccountResponse accountEnrollResponse = accountApi.enrollAccount(accountEnrollRequest);
            Assertions.assertNotNull(accountEnrollResponse, "Missing object 'accountEnrollResponse' when calling enrollAccount(Async)");
            Assertions.assertNotNull(accountEnrollResponse.getReferenceId(), "Missing Mastercard generated unique Account's 'id' when calling enrollAccount(Async)");
            log.info("<-- ACCOUNT ENROLLED SUCCESSFULLY -->");
            return accountEnrollResponse;
        } catch (ApiException e) {
            log.error("<<-- ACCOUNT ENROLLMENT FAILED -->>");
            throw new ServiceException(e.getMessage(), deserializeErrors(e.getResponseBody()));
        }
    }

    /**
     * Retrieves a user’s account details by Mastercard generated unique identifier.
     * URL: /accounts/{reference_id}
     * Method: GET
     * Success Response: 200(OK)
     * Error Response: 4XX or 5XX
     *
     * @param referenceId Mastercard generated unique identifier (required)
     * @return An instance of AccountSearchResponse
     * @throws ServiceException If error occurred while calling find account endpoint
     */
    @Override
    public AccountSearchResponse findById(String referenceId) throws ServiceException {
        try {
            log.info("<-- CALLING FIND ACCOUNT ENDPOINT BY MASTERCARD GENERATED UNIQUE ID -->");
            AccountSearchResponse accountFindResponse = accountApi.findAccount(referenceId);
            Assertions.assertNotNull(accountFindResponse, "Missing object 'accountFindResponse' when calling findAccount(Async)");
            Assertions.assertNotNull(accountFindResponse.getReferenceId(), "Missing Mastercard generated unique Account's 'id' when calling findAccount(Async)");
            log.info("<-- ACCOUNT FOUND SUCCESSFULLY -->");
            return accountFindResponse;
        } catch (ApiException e) {
            log.error("<<-- ACCOUNT FIND FAILED -->>");
            throw new ServiceException(e.getMessage(), deserializeErrors(e.getResponseBody()));
        }
    }

    /**
     * Retrieves a user’s account details based on search criteria.
     * URL: /accounts/searches
     * Method: POST
     * Success Response: 200(OK)
     * Error Response: 4XX or 5XX
     *
     * @param accountSearchRequest Account search request (required)
     * @param limit                Number of records per page. (optional)
     * @param offset               Result page you want to retrieve (0..N) (optional)
     * @return An instance of PagedAccountSearchResponse
     * @throws ServiceException If error occurred while calling account search endpoint
     */
    @Override
    public PagedAccountSearchResponse search(AccountSearchRequest accountSearchRequest, Integer limit, Integer offset) throws ServiceException {
        try {
            log.info("<-- CALLING ACCOUNT SEARCH ENDPOINT -->");
            PagedAccountSearchResponse pagedAccountSearchResponse = accountApi.searchAccount(accountSearchRequest, limit, offset);
            Assertions.assertNotNull(pagedAccountSearchResponse, "Missing object 'pagedAccountSearchResponse' when calling searchAccount(Async)");
            List<AccountSearchResponse> searchList = pagedAccountSearchResponse.getItems();
            Assertions.assertNotNull(searchList, "Missing Account search response items");
            log.info("<-- ACCOUNT FOUND SUCCESSFULLY -->");
            return pagedAccountSearchResponse;
        } catch (ApiException e) {
            log.error("<<-- ACCOUNT SEARCH FAILED -->>");
            throw new ServiceException(e.getMessage(), deserializeErrors(e.getResponseBody()));
        }
    }

    /**
     * Updates existing user’s account details.
     * URL: /accounts/{reference_id}
     * Method: PUT
     * Success Response: 200(OK)
     * Error Response: 4XX or 5XX
     *
     * @param referenceId          Mastercard generated unique identifier (required)
     * @param accountUpdateRequest Account update request (required)
     * @return An instance of AccountResponse
     * @throws ServiceException If error occurred while calling account update endpoint
     */
    @Override
    public AccountResponse update(String referenceId, AccountUpdateRequest accountUpdateRequest) throws ServiceException {
        try {
            log.info("<-- CALLING ACCOUNT UPDATE ENDPOINT -->");
            AccountResponse accountUpdateResponse = accountApi.updateAccount(referenceId, accountUpdateRequest);
            Assertions.assertNotNull(accountUpdateResponse, "Missing object 'accountUpdateResponse' when calling updateAccount(Async)");
            Assertions.assertNotNull(accountUpdateResponse.getReferenceId(), "Missing Mastercard generated unique Account's 'id' when calling updateAccount(Async)");
            log.info("<-- ACCOUNT UPDATED SUCCESSFULLY -->");
            return accountUpdateResponse;
        } catch (ApiException e) {
            log.error("<<-- ACCOUNT UPDATE FAILED -->>");
            throw new ServiceException(e.getMessage(), deserializeErrors(e.getResponseBody()));
        }
    }

    private Errors deserializeErrors(String body) {
        return json.deserialize(body, Errors.class);
    }
}
