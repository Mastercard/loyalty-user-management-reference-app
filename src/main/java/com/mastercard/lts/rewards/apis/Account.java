package com.mastercard.lts.rewards.apis;

import com.mastercard.developer.mastercard_loyalty_enrollment_client.ApiClient;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.ApiException;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.api.AccountApi;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.model.AccountEnrollRequest;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.model.AccountResponse;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.model.AccountSearchRequest;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.model.AccountSearchResponse;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.model.AccountUpdateRequest;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.model.PagedResponseAccountSearchResponse;

public class Account {
    /**
     * Enroll Account in GRP system
     * Enrolls a new account in GRP. Every account is identified by the user Id and Account Type  and account Id.
     * @param apiClient
     * @param enrollRequest
     * @return
     * @throws ApiException if the Api call fails
     */
    public static AccountResponse enrollAccount(ApiClient apiClient, AccountEnrollRequest enrollRequest) throws ApiException {
        AccountApi api = new AccountApi(apiClient);
        AccountResponse accountResponse = api.enrollAccount(enrollRequest);
         return accountResponse;
    }

    /**
     *  Get Account
     * @param apiClient
     * @param accountReferenceId
     * @return Returns the information of existing account in GRP
     * @throws ApiException if the Api call fails
     */
    public static AccountSearchResponse getAccount(ApiClient apiClient, String accountReferenceId) throws ApiException {
        AccountApi api = new AccountApi(apiClient);
        AccountSearchResponse searchResponse = api.findAccount(accountReferenceId);
        return searchResponse;
    }

    /**
     *  Search Accounts
     * @param apiClient
     * @param accountSearchRequest
     * @param limit
     * @param offset
     * @return FReturns the paginated information of all existing Accounts for account request details
     * @throws ApiException if the Api call fails
     */
    public static PagedResponseAccountSearchResponse searchAccount(ApiClient apiClient, AccountSearchRequest accountSearchRequest, Integer limit, Integer offset) throws ApiException {
        AccountApi api = new AccountApi(apiClient);
        PagedResponseAccountSearchResponse pagedAccountSearchResponse = api.searchAccount(accountSearchRequest,limit,offset);
        return pagedAccountSearchResponse;
    }

    /**
     * Update Account
     * @param apiClient
     * @param accountUpdateRequest
     * @param accountReferenceId
     * @return Returns Updates user reference Id
     * @throws ApiException if the Api call fails
     */
    public static AccountResponse updateAccount(ApiClient apiClient, AccountUpdateRequest accountUpdateRequest, String accountReferenceId) throws ApiException {
        AccountApi api = new AccountApi(apiClient);
        AccountResponse accountResponse = api.updateAccount(accountReferenceId, accountUpdateRequest);
        return accountResponse;
    }
}
