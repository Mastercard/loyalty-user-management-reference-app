package com.mastercard.developer.response;

import com.google.common.collect.Lists;
import com.mastercard.developer.constants.Gender;
import org.openapitools.client.model.PagedUserSearchResponse;
import org.openapitools.client.model.UserAddress;
import org.openapitools.client.model.UserEnrollResponse;
import org.openapitools.client.model.UserSearchResponse;
import org.openapitools.client.model.UserUpdateResponse;

import java.util.UUID;

import static com.mastercard.developer.response.MockAccountResponses.getEnrollOrUpdateResponse;

public class MockUserResponses {

    public static final String USER_ID = UUID.randomUUID().toString();

    public static UserEnrollResponse getEnrollResponse(boolean addAccount) {
        UserEnrollResponse enrollResponse = new UserEnrollResponse();
        enrollResponse.setReferenceId(USER_ID);
        if (addAccount) {
            enrollResponse.setAccount(getEnrollOrUpdateResponse());
        }
        return enrollResponse;
    }

    public static UserUpdateResponse getUpdateResponse() {
        UserUpdateResponse updateResponse = new UserUpdateResponse();
        return updateResponse.referenceId(USER_ID);
    }

    public static UserSearchResponse getSearchResponse() {
        UserSearchResponse searchResponse = new UserSearchResponse();

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

        return searchResponse.companyId("611532")
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
                .address(userAddress)
                .referenceId(USER_ID);
    }

    public static PagedUserSearchResponse getPagedSearchResponse() {
        PagedUserSearchResponse pagedSearchResponse = new PagedUserSearchResponse();
        return pagedSearchResponse.count(1)
                .limit(10)
                .offset(0)
                .total(1L)
                .items(Lists.newArrayList(getSearchResponse()));
    }
}