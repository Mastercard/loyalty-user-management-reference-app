package com.mastercard.developer.example;

import com.mastercard.developer.constants.AccountStatus;
import com.mastercard.developer.constants.AccountType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openapitools.client.model.AccountEnrollRequest;
import org.openapitools.client.model.AccountSearchRequest;
import org.openapitools.client.model.AccountUpdateRequest;

import static com.mastercard.developer.example.UserExample.COMPANY_ID;

/**
 * This is a Account example class, can be used to modify data to be passed to Account API.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountExample {

    /**
     * Create an instance of AccountEnrollRequest and set all required and (available) optional information of Account.
     * required:
     * - companyId
     * - userId
     * - accountId
     * - accountIdType
     * - status
     * - productCode
     * - programIdentifier
     *
     * @return An instance of AccountEnrollRequest
     * @implNote The required field values used in this tutorial are dummy and demo purposed only, please change it to valid one before running this application.
     */
    public static AccountEnrollRequest getAccountEnrollRequest() {
        // New account enrollment request
        AccountEnrollRequest accountEnrollRequest = new AccountEnrollRequest();
        return accountEnrollRequest.companyId(COMPANY_ID)
                .userId("C02333333325")
                .accountId("5533154982085292")
                .accountIdType(AccountType.ACCOUNT_NUMBER.name())
                .status(AccountStatus.NEW.name())
                .productCode("7274VCC")
                .programIdentifier("ZXSzM")
                .enrollmentDate("2020-03-11")
                .openDate("2017-04-21");
    }

    /**
     * Create an instance of AccountSearchRequest and set all required and optional information of Account.
     * required:
     * - companyId
     * - accountId
     * - accountIdType
     *
     * @return An instance of AccountSearchRequest
     * @implNote The required field values used in this tutorial are dummy and demo purposed only, please change it to valid one before running this application.
     */
    public static AccountSearchRequest getAccountSearchRequest() {
        // Account search request by 'accountId'
        AccountSearchRequest accountSearchRequest = new AccountSearchRequest();
        return accountSearchRequest.companyId(COMPANY_ID)
                .accountId("5574840102802448")
                .accountIdType(AccountType.ACCOUNT_NUMBER.name());
    }

    /**
     * Create an instance of AccountUpdateRequest and set all required information of Account.
     * required:
     * - companyId
     * - status
     *
     * @return An instance of AccountSearchRequest
     * @implNote The required field values used in this tutorial are dummy and demo purposed only, please change it to valid one before running this application.
     */
    public static AccountUpdateRequest getAccountUpdateRequest() {
        // Account update request
        AccountUpdateRequest accountUpdateRequest = new AccountUpdateRequest();
        return accountUpdateRequest.companyId(COMPANY_ID)
                .status(AccountStatus.GOOD_STANDING.name());
    }
}
