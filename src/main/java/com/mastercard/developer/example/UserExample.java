package com.mastercard.developer.example;

import com.mastercard.developer.constants.AccountStatus;
import com.mastercard.developer.constants.AccountType;
import com.mastercard.developer.constants.Gender;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openapitools.client.model.Account;
import org.openapitools.client.model.UserAddress;
import org.openapitools.client.model.UserEnrollRequest;
import org.openapitools.client.model.UserSearchRequest;
import org.openapitools.client.model.UserUpdateRequest;

/**
 * This is a User example class, can be used to modify data to be passed to User API.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserExample {

    static final String COMPANY_ID = "611532";

    /**
     * Create an instance of UserEnrollRequest and set all required and (available) optional information of User.
     * required:
     * - companyId
     * - userId
     *
     * @return An instance of UserEnrollRequest
     * @implNote The companyId and userId values used in this tutorial are dummy and demo purposed only, please change it to valid one before running this application.
     */
    public static UserEnrollRequest getUserEnrollRequest() {
        // New user enrollment request
        UserEnrollRequest userEnrollRequest = new UserEnrollRequest();

        // User's address information
        UserAddress userAddress = new UserAddress();
        userAddress.addressLine1("10 Upper Bank Street")
                .addressLine2("Chesterfield")
                .addressLine3("Canary Wharf")
                .addressLine4("Tower Hamlets")
                .city("London")
                .postalCode("E1 6AN")
                .stateProvinceCode("JS")
                .countryCode("ENG");

        return userEnrollRequest.companyId(COMPANY_ID)
                .userId("C02333333325")
                .firstName("John")
                .lastName("Smith")
                .motherMaidenName("Maria")
                .gender(Gender.MALE.code())
                .birthDate("1975-03-11")
                .nationalIdentifier("324-56-7574")
                .businessPhoneNumber("+44(0)1234567890")
                .mobilePhoneNumber("+44(0)1234567891")
                .homePhoneNumber("+44(0)1234567892")
                .emailAddress("john.smith@mastercard.com")
                .vip(Boolean.TRUE)
                .employee(Boolean.FALSE)
                .genericIdentification("Duster")
                .genericIdentificationDescription("Pet's name")
                .address(userAddress);
    }

    /**
     * Create an instance of UserEnrollRequest and set all required and (available) optional information of User and Account.
     * required:
     * - companyId
     * - userId
     * - accountId
     * - accountIdType
     * - status
     * - productCode
     * - programIdentifier
     *
     * @return An instance of UserEnrollRequest
     * @implNote The required field values used in this tutorial are dummy and demo purposed only, please change it to valid one before running this application.
     */
    public static UserEnrollRequest getUserAndAccountEnrollRequest() {
        // New user enrollment request
        UserEnrollRequest userAndAccountEnrollRequest = getUserEnrollRequest();

        // New account enrollment
        Account enrollAccount = new Account();
        enrollAccount.accountId("5574840102802448")
                .accountIdType(AccountType.ACCOUNT_NUMBER.name())
                .status(AccountStatus.NEW.name())
                .productCode("7274VCC")
                .programIdentifier("ZXSzM")
                .enrollmentDate("2020-03-11")
                .openDate("2017-04-21");

        return userAndAccountEnrollRequest.userId("C02233122026")
                .account(enrollAccount);
    }

    /**
     * Create an instance of UserSearchRequest and set all required and (available) optional information of User.
     * required:
     * - companyId
     * - userId
     *
     * @return An instance of UserEnrollRequest
     * @implNote The companyId and userId values used in this tutorial are dummy and demo purposed only, please change it to valid one before running this application.
     */
    public static UserSearchRequest getUserSearchRequest() {
        // User search request
        UserSearchRequest userSearchRequest = new UserSearchRequest();
        return userSearchRequest.companyId(COMPANY_ID)
                .userId("C02333333325");
    }

    /**
     * Create an instance of UserUpdateRequest and set all required and (available) optional information of User.
     * required:
     * - companyId
     *
     * @return An instance of UserEnrollRequest
     * @implNote The companyId value used in this tutorial is dummy and demo purposed only, please change it to valid one before running this application.
     */
    public static UserUpdateRequest getUserUpdateRequest() {
        // User update request
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest();

        UserAddress userAddress = new UserAddress();
        userAddress.addressLine1("83 Newton Street")
                .addressLine2("The Sorting House")
                .addressLine3(null)
                .addressLine4(null)
                .city("Manchester")
                .postalCode("M1 1ER")
                .stateProvinceCode("JT")
                .countryCode("ENG");

        return userUpdateRequest.companyId(COMPANY_ID)
                .nationalIdentifier("255-55-6664")
                .mobilePhoneNumber("+44(0)9823342343")
                .homePhoneNumber("+4490244243234")
                .emailAddress("john.smith005@mastercard.com")
                .employee(Boolean.TRUE)
                .address(userAddress);
    }
}
