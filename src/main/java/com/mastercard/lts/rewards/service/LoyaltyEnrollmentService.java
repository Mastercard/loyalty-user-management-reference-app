package com.mastercard.lts.rewards.service;

import com.mastercard.developer.mastercard_loyalty_enrollment_client.ApiClient;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.ApiException;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.model.AccountEnrollRequest;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.model.AccountResponse;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.model.AccountSearchRequest;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.model.AccountUpdateRequest;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.model.PagedResponseAccountSearchResponse;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.model.PagedResponseUserSearchResponse;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.model.UserEnrollRequest;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.model.UserEnrollResponse;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.model.UserSearchRequest;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.model.UserUpdateRequest;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.model.UserUpdateResponse;
import com.mastercard.lts.rewards.apis.Account;
import com.mastercard.lts.rewards.apis.User;

import static com.mastercard.lts.rewards.apis.Account.getAccount;
import static com.mastercard.lts.rewards.apis.User.getUser;

public class LoyaltyEnrollmentService {

    public static void executesMasterCardLoyaltyEnrollmentApis(ApiClient apiClient) throws ApiException {

        UserEnrollResponse userEnrollResponse = enrollUser(apiClient);

        getUser(apiClient, userEnrollResponse.getReferenceId());
        searchUsers(apiClient);
        updateUser(apiClient, userEnrollResponse.getReferenceId());

        AccountResponse accountEnrollRequest = enrollAccount(apiClient);

        getAccount(apiClient, accountEnrollRequest.getReferenceId());
        searchAccounts(apiClient);
        updateAccount(apiClient, accountEnrollRequest.getReferenceId());
    }

    private static UserEnrollResponse enrollUser(ApiClient apiClient) throws ApiException {
        UserEnrollRequest enrollRequest = new UserEnrollRequest();
        enrollRequest.setCompanyId("611532");
        enrollRequest.setUserIdType("CUSTOMER_NUMBER");
        enrollRequest.setUserId("C02333333325");
        enrollRequest.setFirstName("John");
        enrollRequest.setLastName("Smith");
        enrollRequest.setMotherMaidenName("Maria");
        enrollRequest.setGender("1");
        enrollRequest.birthDate("1975-03-11");
        enrollRequest.setNationalIdentifier("324-56-7574");
        enrollRequest.setBusinessPhoneNumber("+1(0)1234567890");
        enrollRequest.setMobilePhoneNumber("+1(0)1234567891");
        enrollRequest.homePhoneNumber("+1(0)1234567892");
        enrollRequest.setEmailAddress("john.smith@mastercard.com");
        enrollRequest.setVip(false);
        enrollRequest.setEmployee(false);
        enrollRequest.setGenericIdentification("Duster");
        enrollRequest.setGenericIdentificationDescription("Pet's name");
        return User.enrollUser(apiClient, enrollRequest);
    }

    private static PagedResponseUserSearchResponse searchUsers(ApiClient apiClient) throws ApiException {
        UserSearchRequest userSearchRequest = new UserSearchRequest();
        userSearchRequest.setCompanyId("611532");
        userSearchRequest.setUserIdType("CUSTOMER_NUMBER");
        userSearchRequest.setUserId("C02333333325");
        return User.searchUser(apiClient, userSearchRequest, 0, 0);
    }

    private static UserUpdateResponse updateUser(ApiClient apiClient, String referenceId) throws ApiException {
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest();
        userUpdateRequest.setEmailAddress("john.smith1@mastercard.com");
        userUpdateRequest.setVip(true);
        userUpdateRequest.setEmployee(true);
        return User.updateUser(apiClient, userUpdateRequest, referenceId);
    }

    private static AccountResponse enrollAccount(ApiClient apiClient) throws ApiException {
        AccountEnrollRequest accountEnrollRequest = new AccountEnrollRequest();
        accountEnrollRequest.setCompanyId("611532");
        accountEnrollRequest.setUserIdType("CUSTOMER_NUMBER");
        accountEnrollRequest.setUserId("C02333333325");
        accountEnrollRequest.setAccountIdType("ACCOUNT_NUMBER");
        accountEnrollRequest.setAccountId("5330333671236516");
        accountEnrollRequest.setStatus("NEW");
        accountEnrollRequest.setProductCode("7274VCC");
        accountEnrollRequest.setProgramIdentifier("ZXSzM");
        accountEnrollRequest.setEnrollmentDate("2020-01-01");
        accountEnrollRequest.setOpenDate("2020-01-01");
        return Account.enrollAccount(apiClient, accountEnrollRequest);
    }

    private static PagedResponseAccountSearchResponse searchAccounts(ApiClient apiClient) throws ApiException {
        AccountSearchRequest accountSearchRequest = new AccountSearchRequest();
        accountSearchRequest.setCompanyId("611532");
        accountSearchRequest.setAccountIdType("CUSTOMER_NUMBER");
        accountSearchRequest.setAccountId("C02333333325");
        accountSearchRequest.setProgramIdentifier("ZXSzM");
        return Account.searchAccount(apiClient, accountSearchRequest, 0, 0);
    }

    private static AccountResponse updateAccount(ApiClient apiClient, String referenceId) throws ApiException {
        AccountUpdateRequest accountUpdateRequest = new AccountUpdateRequest();
        accountUpdateRequest.setCompanyId("john.smith1@mastercard.com");
        accountUpdateRequest.setStatus("GOOD_STANDING");
        return Account.updateAccount(apiClient, accountUpdateRequest, referenceId);
    }
}
