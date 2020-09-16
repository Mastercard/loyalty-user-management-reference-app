package com.mastercard.developer.service.impl;

import com.mastercard.developer.exception.ServiceException;
import com.mastercard.developer.service.AccountService;
import com.mastercard.developer.util.GsonProvider;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.AccountManagementApi;
import org.openapitools.client.model.AccountEnrollRequest;
import org.openapitools.client.model.AccountResponse;
import org.openapitools.client.model.AccountSearchRequest;
import org.openapitools.client.model.AccountSearchResponse;
import org.openapitools.client.model.AccountUpdateRequest;
import org.openapitools.client.model.Errors;
import org.openapitools.client.model.PagedResponseOfAccountSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountManagementApi accountApi;

    @Autowired
    public AccountServiceImpl(ApiClient apiClient) {
        log.info("-->> INITIALIZING ACCOUNT API");
        accountApi = new AccountManagementApi(apiClient);
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
            Assertions.assertNotNull(accountEnrollResponse.getId(), "Missing Mastercard generated unique Account's 'id' when calling enrollAccount(Async)");
            log.info("<-- ACCOUNT ENROLLED SUCCESSFULLY -->");
            return accountEnrollResponse;
        } catch (ApiException e) {
            log.error("<<-- ACCOUNT ENROLLMENT FAILED -->>");
            throw new ServiceException(e.getMessage(), deserializeErrors(e.getResponseBody()));
        }
    }

    /**
     * Retrieves a user’s account details by Mastercard generated unique identifier.
     * URL: /accounts/{id}
     * Method: GET
     * Success Response: 200(OK)
     * Error Response: 4XX or 5XX
     *
     * @param id Mastercard generated unique identifier (required)
     * @return An instance of AccountSearchResponse
     * @throws ServiceException If error occurred while calling find account endpoint
     */
    @Override
    public AccountSearchResponse findById(String id) throws ServiceException {
        try {
            log.info("<-- CALLING FIND ACCOUNT ENDPOINT BY MASTERCARD GENERATED UNIQUE ID -->");
            AccountSearchResponse accountFindResponse = accountApi.findAccount(id);
            Assertions.assertNotNull(accountFindResponse, "Missing object 'accountFindResponse' when calling findAccount(Async)");
            Assertions.assertNotNull(accountFindResponse.getId(), "Missing Mastercard generated unique Account's 'id' when calling findAccount(Async)");
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
     * @param offset               The number of items to offset the start of the list from (optional)
     * @param limit                Can be used to limit the amount of results returned from a query (optional)
     * @param accountSearchRequest Account search request (required)
     * @return An instance of PagedResponseOfAccountSearchResponse
     * @throws ServiceException If error occurred while calling account search endpoint
     */
    @Override
    public PagedResponseOfAccountSearchResponse search(Integer offset, Integer limit, AccountSearchRequest accountSearchRequest) throws ServiceException {
        try {
            log.info("<-- CALLING ACCOUNT SEARCH ENDPOINT -->");
            PagedResponseOfAccountSearchResponse pagedAccountSearchResponse = accountApi.searchAccount(offset, limit, accountSearchRequest);
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
     * URL: /accounts/{id}
     * Method: PUT
     * Success Response: 200(OK)
     * Error Response: 4XX or 5XX
     *
     * @param id                   Mastercard generated unique identifier (required)
     * @param accountUpdateRequest Account update request (required)
     * @return An instance of AccountResponse
     * @throws ServiceException If error occurred while calling account update endpoint
     */
    @Override
    public AccountResponse update(String id, AccountUpdateRequest accountUpdateRequest) throws ServiceException {
        try {
            log.info("<-- CALLING ACCOUNT UPDATE ENDPOINT -->");
            AccountResponse accountUpdateResponse = accountApi.updateAccount(id, accountUpdateRequest);
            Assertions.assertNotNull(accountUpdateResponse, "Missing object 'accountUpdateResponse' when calling updateAccount(Async)");
            Assertions.assertNotNull(accountUpdateResponse.getId(), "Missing Mastercard generated unique Account's 'id' when calling updateAccount(Async)");
            log.info("<-- ACCOUNT UPDATED SUCCESSFULLY -->");
            return accountUpdateResponse;
        } catch (ApiException e) {
            log.error("<<-- ACCOUNT UPDATE FAILED -->>");
            throw new ServiceException(e.getMessage(), deserializeErrors(e.getResponseBody()));
        }
    }

    private Errors deserializeErrors(String body) {
        return GsonProvider.gson().deserialize(body, Errors.class);
    }
}
