package com.mastercard.developer.service;

import com.mastercard.developer.exception.ServiceException;
import org.openapitools.client.model.UserEnrollRequest;
import org.openapitools.client.model.UserEnrollResponse;
import org.openapitools.client.model.UserSearchRequest;
import org.openapitools.client.model.UserSearchResponse;
import org.openapitools.client.model.UserUpdateRequest;
import org.openapitools.client.model.UserUpdateResponse;

import java.util.List;

public interface UserService {

    UserEnrollResponse enrollUserOnly(UserEnrollRequest userEnrollRequest) throws ServiceException;

    UserEnrollResponse enrollUserAndAccount(UserEnrollRequest userAndAccountEnrollRequest) throws ServiceException;

    UserSearchResponse findById(String id) throws ServiceException;

    List<UserSearchResponse> search(UserSearchRequest userSearchRequest, Integer limit, Integer offset) throws ServiceException;

    UserUpdateResponse update(String id, UserUpdateRequest userUpdateRequest) throws ServiceException;
}
