package com.mastercard.developer.service.impl;

import com.mastercard.developer.exception.ServiceException;
import com.mastercard.developer.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.UserApi;
import org.openapitools.client.model.PagedResponseUserSearchResponse;
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

    @Autowired
    public UserServiceImpl(ApiClient apiClient) {
        log.info("-->> INITIALIZING USER API");
        userApi = new UserApi(apiClient);
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
            Assert.assertNotNull("Missing object 'userEnrollResponse' when calling enrollUser(Async)", userEnrollResponse);
            Assert.assertNotNull("Missing Mastercard generated unique User's 'id' when calling enrollUser(Async)", userEnrollResponse.getReferenceId());
            log.info("<-- USER ENROLLED SUCCESSFULLY -->");
            return userEnrollResponse;
        } catch (ApiException e) {
            log.error("<<-- USER ENROLLMENT FAILED -->>");
            throw new ServiceException(e.getMessage());
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
            Assert.assertNotNull("Missing object 'userAndAccountEnrollResponse' when calling enrollUser(Async) for User and Account enrollment", userAndAccountEnrollResponse);
            Assert.assertNotNull("Missing Mastercard generated unique User's 'id' when calling enrollUser(Async) for User and Account enrollment", userAndAccountEnrollResponse.getReferenceId());
            Assert.assertNotNull("Missing object 'accountResponse' when calling enrollUser(Async) for User and Account enrollment", userAndAccountEnrollResponse.getAccount());
            Assert.assertNotNull("Missing Mastercard generated unique Account's 'id' when calling enrollUser(Async) for User and Account enrollment", userAndAccountEnrollResponse.getAccount().getReferenceId());
            log.info("<-- USER AND ACCOUNT ENROLLED SUCCESSFULLY -->");
            return userAndAccountEnrollResponse;
        } catch (ApiException e) {
            log.error("<<-- USER AND ACCOUNT ENROLLMENT FAILED -->>");
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * Retrieves a user’s details by Mastercard generated unique identifier.
     * URL: /users/{reference_id}
     * Method: GET
     * Success Response: 200(OK)
     * Error Response: 4XX or 5XX
     *
     * @param id Mastercard generated unique identifier (required)
     * @return An instance of UserSearchResponse
     * @throws ServiceException If error occurred while calling find user endpoint
     */
    @Override
    public UserSearchResponse findById(String id) throws ServiceException {
        try {
            log.info("<-- CALLING FIND USER ENDPOINT BY MASTERCARD GENERATED UNIQUE ID -->");
            UserSearchResponse userFindResponse = userApi.findUser(id);
            Assert.assertNotNull("Missing object 'userFindResponse' when calling findUser(Async)", userFindResponse);
            Assert.assertNotNull("Missing Mastercard generated unique User's 'id' when calling findUser(Async)", userFindResponse.getReferenceId());
            log.info("<-- USER FOUND SUCCESSFULLY -->");
            return userFindResponse;
        } catch (ApiException e) {
            log.error("<<-- USER FIND FAILED -->>");
            throw new ServiceException(e.getMessage());
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
     * @return An list of UserSearchResponse instances
     * @throws ServiceException If error occurred while calling user search endpoint
     */
    @Override
    public List<UserSearchResponse> search(UserSearchRequest userSearchRequest, Integer limit, Integer offset) throws ServiceException {
        try {
            log.info("<-- CALLING USER SEARCH ENDPOINT -->");
            PagedResponseUserSearchResponse pagedUserSearchResponse = userApi.searchUser(userSearchRequest, limit, offset);
            Assert.assertNotNull("Missing object 'pagedUserSearchResponse' when calling searchUser(Async)", pagedUserSearchResponse);
            List<UserSearchResponse> searchResponses = pagedUserSearchResponse.getItems();
            Assert.assertNotNull("Missing User search response items", searchResponses);
            log.info("<-- USER FOUND SUCCESSFULLY -->");
            return searchResponses;
        } catch (ApiException e) {
            log.error("<<-- USER SEARCH FAILED -->>");
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * Updates existing user's details.
     * URL: /users/{reference_id}
     * Method: PUT
     * Success Response: 200(OK)
     * Error Response: 4XX or 5XX
     *
     * @param id                Mastercard generated unique identifier (required)
     * @param userUpdateRequest User update request (required)
     * @return An instance of UserUpdateResponse
     * @throws ServiceException If error occurred while calling user update endpoint
     */
    @Override
    public UserUpdateResponse update(String id, UserUpdateRequest userUpdateRequest) throws ServiceException {
        try {
            log.info("<-- CALLING USER UPDATE ENDPOINT -->");
            UserUpdateResponse userUpdateResponse = userApi.updateUser(id, userUpdateRequest);
            Assert.assertNotNull("Missing object 'userUpdateResponse' when calling updateUser(Async)", userUpdateResponse);
            Assert.assertNotNull("Missing Mastercard generated unique User's 'id' when calling enrollUser(Async)", userUpdateResponse.getReferenceId());
            log.info("<-- USER UPDATED SUCCESSFULLY -->");
            return userUpdateResponse;
        } catch (ApiException e) {
            log.error("<<-- USER UPDATE FAILED -->>");
            throw new ServiceException(e.getMessage());
        }
    }
}
