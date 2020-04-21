package com.mastercard.developer.response;

import com.google.common.collect.Lists;
import com.mastercard.developer.constants.AccountStatus;
import com.mastercard.developer.constants.AccountType;
import org.openapitools.client.model.AccountResponse;
import org.openapitools.client.model.AccountSearchResponse;
import org.openapitools.client.model.PagedAccountSearchResponse;

import java.util.UUID;

public class MockAccountResponses {

    public static final String ACCOUNT_ID = UUID.randomUUID().toString();

    public static AccountResponse getEnrollOrUpdateResponse() {
        AccountResponse accountResponse = new AccountResponse();
        return accountResponse.referenceId(ACCOUNT_ID);
    }

    public static AccountSearchResponse getSearchResponse() {
        AccountSearchResponse searchResponse = new AccountSearchResponse();
        return searchResponse.companyId("611532")
                .userId("C02333333325")
                .accountId("5533154982085292")
                .accountIdType(AccountType.ACCOUNT_NUMBER.name())
                .status(AccountStatus.NEW.name())
                .productCode("7274VCC")
                .programIdentifier("ZXSzM")
                .enrollmentDate("2020-03-11")
                .openDate("2017-04-21")
                .referenceId(ACCOUNT_ID);
    }

    public static PagedAccountSearchResponse getPagedSearchResponse() {
        PagedAccountSearchResponse pagedSearchResponse = new PagedAccountSearchResponse();
        return pagedSearchResponse.count(1)
                .limit(10)
                .offset(0)
                .total(1L)
                .items(Lists.newArrayList(getSearchResponse()));
    }

    public static String getErrorResponseBody() {
        return "{\n" +
                "  \"Errors\": {\n" +
                "    \"Error\": [\n" +
                "      {\n" +
                "        \"Source\": \"Loyalty-Enrollment\",\n" +
                "        \"ReasonCode\": \"DUPLICATE_REQUEST\",\n" +
                "        \"Description\": \"The Account already exists for the given User and Product.\",\n" +
                "        \"Recoverable\": false,\n" +
                "        \"Details\": null\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";
    }
}
