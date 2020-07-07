package com.mastercard.developer.response;

import com.google.common.collect.Lists;
import org.openapitools.client.model.AccountResponse;
import org.openapitools.client.model.AccountSearchResponse;
import org.openapitools.client.model.PagedAccountSearchResponse;

import java.time.LocalDate;
import java.util.UUID;

public class MockAccountResponses {

    public static final String ACCOUNT_ID = UUID.randomUUID().toString();
    public static final String DUP_REQ_DESCRIPTION = "The Account already exists for the given User and Product.";
    public static final String SER_ERR_DESCRIPTION = "Account unique Reference ID could not be found.";

    public static AccountResponse getEnrollOrUpdateResponse() {
        AccountResponse accountResponse = new AccountResponse();
        return accountResponse.id(ACCOUNT_ID);
    }

    public static AccountSearchResponse getSearchResponse() {
        AccountSearchResponse searchResponse = new AccountSearchResponse();
        return searchResponse.companyId("611532")
                .userNumber("C02333333325")
                .accountNumber("5533154982085292")
                .status("NEW")
                .productCode("7274VCC")
                .programEnrollmentCode("ZXSzM")
                .enrollmentDate(LocalDate.parse("2020-03-11"))
                .openDate(LocalDate.parse("2017-04-21"))
                .id(ACCOUNT_ID);
    }

    public static PagedAccountSearchResponse getPagedSearchResponse() {
        PagedAccountSearchResponse pagedSearchResponse = new PagedAccountSearchResponse();
        return pagedSearchResponse.count(1)
                .limit(10)
                .offset(0)
                .total(1L)
                .items(Lists.newArrayList(getSearchResponse()));
    }
}
