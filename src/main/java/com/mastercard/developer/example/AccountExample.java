package com.mastercard.developer.example;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openapitools.client.model.AccountEnrollRequest;
import org.openapitools.client.model.AccountSearchRequest;
import org.openapitools.client.model.AccountUpdateRequest;

import java.time.LocalDate;

import static com.mastercard.developer.example.UserExample.COMPANY_ID;

/**
 * This is a Account example class, can be used to modify data to be passed to Account API.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountExample {

    /**
     * Create an instance of AccountEnrollRequest and set all required and (available) optional information of Account.
     * required:
     * - companyId or memberICA
     * - userNumber
     * - accountNumber
     * - productCode
     * - programEnrollmentCode
     *
     * @return An instance of AccountEnrollRequest
     * @implNote The required field values used in this tutorial are dummy and demo purposed only, please change it to valid one before running this application.
     */
    public static AccountEnrollRequest getAccountEnrollRequest() {
        // New account enrollment request
        AccountEnrollRequest accountEnrollRequest = new AccountEnrollRequest();
        return accountEnrollRequest.companyId(COMPANY_ID)
                .userNumber("C02333333325")
                .accountNumber("5533154982085292")
                .status(AccountEnrollRequest.StatusEnum.NEW)
                .productCode("7274VCC")
                .programEnrollmentCode("ZXSzM")
                .enrollmentDate(LocalDate.parse("2020-03-11"))
                .openDate(LocalDate.parse("2017-04-21"));
    }

    /**
     * Create an instance of AccountSearchRequest and set all required and optional information of Account.
     * required:
     * - companyId or memberICA
     * - accountNumber
     *
     * @return An instance of AccountSearchRequest
     * @implNote The required field values used in this tutorial are dummy and demo purposed only, please change it to valid one before running this application.
     */
    public static AccountSearchRequest getAccountSearchRequest() {
        // Account search request by 'accountNumber'
        AccountSearchRequest accountSearchRequest = new AccountSearchRequest();
        return accountSearchRequest.companyId(COMPANY_ID)
                .accountNumber("5574840102802448");
    }

    /**
     * Create an instance of AccountUpdateRequest and set all required information of Account.
     * required:
     * - companyId or memberICA
     * - status
     *
     * @return An instance of AccountSearchRequest
     * @implNote The required field values used in this tutorial are dummy and demo purposed only, please change it to valid one before running this application.
     */
    public static AccountUpdateRequest getAccountUpdateRequest() {
        // Account update request
        AccountUpdateRequest accountUpdateRequest = new AccountUpdateRequest();
        return accountUpdateRequest.companyId(COMPANY_ID)
                .status(AccountUpdateRequest.StatusEnum.GOOD_STANDING);
    }
}
