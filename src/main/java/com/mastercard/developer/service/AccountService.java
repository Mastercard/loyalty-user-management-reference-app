package com.mastercard.developer.service;

import com.mastercard.developer.exception.ServiceException;
import org.openapitools.client.model.AccountEnrollRequest;
import org.openapitools.client.model.AccountResponse;
import org.openapitools.client.model.AccountSearchRequest;
import org.openapitools.client.model.AccountSearchResponse;
import org.openapitools.client.model.AccountUpdateRequest;
import org.openapitools.client.model.PagedAccountSearchResponse;

public interface AccountService {

    AccountResponse enroll(AccountEnrollRequest accountEnrollRequest) throws ServiceException;

    AccountSearchResponse findById(String referenceId) throws ServiceException;

    PagedAccountSearchResponse search(AccountSearchRequest accountSearchRequest, Integer limit, Integer offset) throws ServiceException;

    AccountResponse update(String referenceId, AccountUpdateRequest accountUpdateRequest) throws ServiceException;
}
