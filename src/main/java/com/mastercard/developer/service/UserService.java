package com.mastercard.developer.service;

import com.mastercard.developer.exception.ServiceException;
import org.openapitools.client.model.PagedUserSearchResponse;
import org.openapitools.client.model.UserEnrollRequest;
import org.openapitools.client.model.UserEnrollResponse;
import org.openapitools.client.model.UserSearchRequest;
import org.openapitools.client.model.UserSearchResponse;
import org.openapitools.client.model.UserUpdateRequest;
import org.openapitools.client.model.UserUpdateResponse;

public interface UserService {

    UserEnrollResponse enrollUserOnly(UserEnrollRequest userEnrollRequest) throws ServiceException;

    UserEnrollResponse enrollUserAndAccount(UserEnrollRequest userAndAccountEnrollRequest) throws ServiceException;

    UserSearchResponse findById(String referenceId) throws ServiceException;

    PagedUserSearchResponse search(UserSearchRequest userSearchRequest, Integer limit, Integer offset) throws ServiceException;

    UserUpdateResponse update(String referenceId, UserUpdateRequest userUpdateRequest) throws ServiceException;
}
