package com.mastercard.developer.service.impl;

import com.mastercard.developer.exception.ServiceException;
import com.mastercard.developer.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.JSON;
import org.openapitools.client.api.UserApi;
import org.openapitools.client.model.Errors;
import org.openapitools.client.model.PagedUserSearchResponse;
import org.openapitools.client.model.UserEnrollRequest;
import org.openapitools.client.model.UserEnrollResponse;
import org.openapitools.client.model.UserSearchRequest;
import org.openapitools.client.model.UserSearchResponse;
import org.openapitools.client.model.UserUpdateRequest;
import org.openapitools.client.model.UserUpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private UserApi userApi;

    private JSON json;

    @Autowired
    public UserServiceImpl(ApiClient apiClient) {
        log.info("-->> INITIALIZING USER API");
        userApi = new UserApi(apiClient);
        json = new JSON();
    }

    /**
     * Enrolls a new user for a Company. Account details may or may not be provided.
     * URL: /users
     * Method: POST
     * Success Response: 201(CREATED)
     * Error Response: 4XX or 5XX
     *
     * @param userEnrollRequest User enrollment request (required)
     * @return An instance of UserEnrollResponse
     * @throws ServiceException If error occurred while calling user enroll endpoint
     */
    @Override
    public UserEnrollResponse enrollUserOnly(UserEnrollRequest userEnrollRequest) throws ServiceException {
        try {
            log.info("<-- CALLING USER ENROLLMENT ENDPOINT -->");
            UserEnrollResponse userEnrollResponse = userApi.enrollUser(userEnrollRequest);
            Assertions.assertNotNull(userEnrollResponse, "Missing object 'userEnrollResponse' when calling enrollUser(Async)");
            Assertions.assertNotNull(userEnrollResponse.getReferenceId(), "Missing Mastercard generated unique User's 'id' when calling enrollUser(Async)");
            log.info("<-- USER ENROLLED SUCCESSFULLY -->");
            return userEnrollResponse;
        } catch (ApiException e) {
            log.error("<<-- USER ENROLLMENT FAILED -->>");
            throw new ServiceException(e.getMessage(), deserializeErrors(e.getResponseBody()));
        }
    }

    /**
     * Enrolls a new user and account for a Company. To enrolls account alongside, create an instance of Account object and populate all required fields.
     * URL: /users
     * Method: POST
     * Success Response: 201(CREATED)
     * Error Response: 4XX or 5XX
     *
     * @param userAndAccountEnrollRequest User and account enrollment request (required)
     * @return An instance of UserEnrollResponse
     * @throws ServiceException If error occurred while calling user and account enroll endpoint
     */
    @Override
    public UserEnrollResponse enrollUserAndAccount(UserEnrollRequest userAndAccountEnrollRequest) throws ServiceException {
        try {
            log.info("<-- CALLING USER AND ACCOUNT ENROLLMENT ENDPOINT -->");
            UserEnrollResponse userAndAccountEnrollResponse = userApi.enrollUser(userAndAccountEnrollRequest);
            Assertions.assertNotNull(userAndAccountEnrollResponse, "Missing object 'userAndAccountEnrollResponse' when calling enrollUser(Async) for User and Account enrollment");
            Assertions.assertNotNull(userAndAccountEnrollResponse.getReferenceId(), "Missing Mastercard generated unique User's 'id' when calling enrollUser(Async) for User and Account enrollment");
            Assertions.assertNotNull(userAndAccountEnrollResponse.getAccount(), "Missing object 'accountResponse' when calling enrollUser(Async) for User and Account enrollment");
            Assertions.assertNotNull(userAndAccountEnrollResponse.getAccount().getReferenceId(), "Missing Mastercard generated unique Account's 'id' when calling enrollUser(Async) for User and Account enrollment");
            log.info("<-- USER AND ACCOUNT ENROLLED SUCCESSFULLY -->");
            return userAndAccountEnrollResponse;
        } catch (ApiException e) {
            log.error("<<-- USER AND ACCOUNT ENROLLMENT FAILED -->>");
            throw new ServiceException(e.getMessage(), deserializeErrors(e.getResponseBody()));
        }
    }

    /**
     * Retrieves a user’s details by Mastercard generated unique identifier.
     * URL: /users/{reference_id}
     * Method: GET
     * Success Response: 200(OK)
     * Error Response: 4XX or 5XX
     *
     * @param referenceId Mastercard generated unique identifier (required)
     * @return An instance of UserSearchResponse
     * @throws ServiceException If error occurred while calling find user endpoint
     */
    @Override
    public UserSearchResponse findById(String referenceId) throws ServiceException {
        try {
            log.info("<-- CALLING FIND USER ENDPOINT BY MASTERCARD GENERATED UNIQUE ID -->");
            UserSearchResponse userFindResponse = userApi.findUser(referenceId);
            Assertions.assertNotNull(userFindResponse, "Missing object 'userFindResponse' when calling findUser(Async)");
            Assertions.assertNotNull(userFindResponse.getReferenceId(), "Missing Mastercard generated unique User's 'id' when calling findUser(Async)");
            log.info("<-- USER FOUND SUCCESSFULLY -->");
            return userFindResponse;
        } catch (ApiException e) {
            log.error("<<-- USER FIND FAILED -->>");
            throw new ServiceException(e.getMessage(), deserializeErrors(e.getResponseBody()));
        }
    }

    /**
     * Retrieves a user's details based on search criteria.
     * URL: /users/searches
     * Method: POST
     * Success Response: 200(OK)
     * Error Response: 4XX or 5XX
     *
     * @param userSearchRequest User search request (required)
     * @param limit             Number of records per page. (optional)
     * @param offset            Result page you want to retrieve (0..N) (optional)
     * @return An instance of PagedUserSearchResponse
     * @throws ServiceException If error occurred while calling user search endpoint
     */
    @Override
    public PagedUserSearchResponse search(UserSearchRequest userSearchRequest, Integer limit, Integer offset) throws ServiceException {
        try {
            log.info("<-- CALLING USER SEARCH ENDPOINT -->");
            PagedUserSearchResponse pagedUserSearchResponse = userApi.searchUser(userSearchRequest, limit, offset);
            Assertions.assertNotNull(pagedUserSearchResponse, "Missing object 'pagedUserSearchResponse' when calling searchUser(Async)");
            List<UserSearchResponse> searchList = pagedUserSearchResponse.getItems();
            Assertions.assertNotNull(searchList, "Missing User search response items");
            log.info("<-- USER FOUND SUCCESSFULLY -->");
            return pagedUserSearchResponse;
        } catch (ApiException e) {
            log.error("<<-- USER SEARCH FAILED -->>");
            throw new ServiceException(e.getMessage(), deserializeErrors(e.getResponseBody()));
        }
    }

    /**
     * Updates existing user's details.
     * URL: /users/{reference_id}
     * Method: PUT
     * Success Response: 200(OK)
     * Error Response: 4XX or 5XX
     *
     * @param referenceId       Mastercard generated unique identifier (required)
     * @param userUpdateRequest User update request (required)
     * @return An instance of UserUpdateResponse
     * @throws ServiceException If error occurred while calling user update endpoint
     */
    @Override
    public UserUpdateResponse update(String referenceId, UserUpdateRequest userUpdateRequest) throws ServiceException {
        try {
            log.info("<-- CALLING USER UPDATE ENDPOINT -->");
            UserUpdateResponse userUpdateResponse = userApi.updateUser(referenceId, userUpdateRequest);
            Assertions.assertNotNull(userUpdateResponse, "Missing object 'userUpdateResponse' when calling updateUser(Async)");
            Assertions.assertNotNull(userUpdateResponse.getReferenceId(), "Missing Mastercard generated unique User's 'id' when calling enrollUser(Async)");
            log.info("<-- USER UPDATED SUCCESSFULLY -->");
            return userUpdateResponse;
        } catch (ApiException e) {
            log.error("<<-- USER UPDATE FAILED -->>");
            throw new ServiceException(e.getMessage(), deserializeErrors(e.getResponseBody()));
        }
    }

    private Errors deserializeErrors(String body) {
        return json.deserialize(body, Errors.class);
    }
}
