package com.mastercard.lts.rewards.apis;

import com.mastercard.developer.mastercard_loyalty_enrollment_client.ApiClient;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.ApiException;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.api.UserApi;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.model.PagedResponseUserSearchResponse;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.model.UserEnrollRequest;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.model.UserEnrollResponse;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.model.UserSearchRequest;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.model.UserSearchResponse;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.model.UserUpdateRequest;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.model.UserUpdateResponse;

public class User {

    /**
     * Enroll a user
     * <p>
     * Enrolls a new user in GRP. Every user is identified by the user Id and user Type.
     * @throws ApiException if the Api call fails
     */
    public static UserEnrollResponse enrollUser(ApiClient apiClient, UserEnrollRequest enrollRequest) throws ApiException {
        UserApi api = new UserApi(apiClient);
        UserEnrollResponse enrollResponse = api.enrollUser(enrollRequest);
        return enrollResponse;
    }

    /**
     * 
     * Get a User
     * @return Returns the information related to the existing User
     * @throws ApiException if the Api call fails
     */
    public static UserSearchResponse getUser(ApiClient apiClient, String userRefrenceId) throws ApiException {
        UserApi api = new UserApi(apiClient);
        UserSearchResponse getResponse = api.findUser(userRefrenceId);
        return  getResponse;
    }

    /**
     * Search Users
     * @return Returns the list of all existing users as per userSearch request.
     * @param apiClient
     * @param userSearchRequest
     * @param limit
     * @param offset
     * @throws ApiException if the Api call fails
     */
    public static PagedResponseUserSearchResponse searchUser(ApiClient apiClient, UserSearchRequest userSearchRequest, Integer limit, Integer offset) throws ApiException{
        UserApi api = new UserApi(apiClient);
        PagedResponseUserSearchResponse pagedResponseUserSearchResponse = api.searchUser(userSearchRequest, limit, offset);
        return pagedResponseUserSearchResponse;
    }

    /**
     * Update User
     * @param apiClient
     * @param userUpdateRequest
     * @param userReferenceId
     * @return Returns updated user reference Id
     * @throws ApiException if the Api call fails
     */
    public static UserUpdateResponse updateUser(ApiClient apiClient, UserUpdateRequest userUpdateRequest, String userReferenceId) throws ApiException{
        UserApi api = new UserApi(apiClient);
        UserUpdateResponse updateResponse = api.updateUser(userReferenceId, userUpdateRequest);
        return updateResponse;
    }
}
