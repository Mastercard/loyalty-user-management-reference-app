/*
 * User Account Management
 * API interface to enroll users and accounts
 *
 * The version of the OpenAPI document: 1.0.0
 * Contact: Loyalty_Technology_Solutions_Scrum_Vulcans@mastercard.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package com.mastercard.developer.mastercard_loyalty_enrollment_client.api;

import com.mastercard.developer.mastercard_loyalty_enrollment_client.ApiException;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.model.Errors;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.model.PagedResponseUserSearchResponse;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.model.UserEnrollRequest;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.model.UserEnrollResponse;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.model.UserSearchRequest;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.model.UserSearchResponse;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.model.UserUpdateRequest;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.model.UserUpdateResponse;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for UserApi
 */
@Ignore
public class UserApiTest {

    private final UserApi api = new UserApi();

    
    /**
     * Enrolls a new user for a company, user may or may not provide other demographic information for enrollment
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void enrollUserTest() throws ApiException {
        UserEnrollRequest userEnrollRequest = null;
        UserEnrollResponse response = api.enrollUser(userEnrollRequest);

        // TODO: test validations
    }
    
    /**
     * Returns the demographic information related to the existing user
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void findUserTest() throws ApiException {
        String referenceId = null;
        UserSearchResponse response = api.findUser(referenceId);

        // TODO: test validations
    }
    
    /**
     * Returns the demographic information related to the existing user information
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void searchUserTest() throws ApiException {
        UserSearchRequest userSearchRequest = null;
        Integer limit = null;
        Integer offset = null;
        PagedResponseUserSearchResponse response = api.searchUser(userSearchRequest, limit, offset);

        // TODO: test validations
    }
    
    /**
     * Updates the demographic information for an existing user of the company
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void updateUserTest() throws ApiException {
        String referenceId = null;
        UserUpdateRequest userUpdateRequest = null;
        UserUpdateResponse response = api.updateUser(referenceId, userUpdateRequest);

        // TODO: test validations
    }
    
}