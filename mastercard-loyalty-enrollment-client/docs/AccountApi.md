# AccountApi

All URIs are relative to *http://api.mastercard.com/loyalty/enrollment/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**enrollAccount**](AccountApi.md#enrollAccount) | **POST** /accounts | Enrolls a new account of an user for a company
[**findAccount**](AccountApi.md#findAccount) | **GET** /accounts/{reference_id} | Returns the account information for an account of a company
[**searchAccount**](AccountApi.md#searchAccount) | **POST** /accounts/searches | Returns the account information for a company
[**updateAccount**](AccountApi.md#updateAccount) | **PUT** /accounts/{reference_id} | Updates the status for an account of the company


<a name="enrollAccount"></a>
# **enrollAccount**
> AccountResponse enrollAccount(accountEnrollRequest)

Enrolls a new account of an user for a company

### Example
```java
// Import classes:
import com.mastercard.developer.mastercard_loyalty_enrollment_client.ApiClient;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.ApiException;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.Configuration;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.models.*;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.api.AccountApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://api.mastercard.com/loyalty/enrollment/api");

    AccountApi apiInstance = new AccountApi(defaultClient);
    AccountEnrollRequest accountEnrollRequest = new AccountEnrollRequest(); // AccountEnrollRequest | accountEnrollRequest
    try {
      AccountResponse result = apiInstance.enrollAccount(accountEnrollRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AccountApi#enrollAccount");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **accountEnrollRequest** | [**AccountEnrollRequest**](AccountEnrollRequest.md)| accountEnrollRequest |

### Return type

[**AccountResponse**](AccountResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**201** | Successfully Enrolled Account |  -  |
**400** | Bad Request |  -  |

<a name="findAccount"></a>
# **findAccount**
> AccountSearchResponse findAccount(referenceId)

Returns the account information for an account of a company

### Example
```java
// Import classes:
import com.mastercard.developer.mastercard_loyalty_enrollment_client.ApiClient;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.ApiException;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.Configuration;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.models.*;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.api.AccountApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://api.mastercard.com/loyalty/enrollment/api");

    AccountApi apiInstance = new AccountApi(defaultClient);
    String referenceId = d8753780-62d1-4775-9549-8ed92c48c290; // String | Reference Id
    try {
      AccountSearchResponse result = apiInstance.findAccount(referenceId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AccountApi#findAccount");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **referenceId** | **String**| Reference Id |

### Return type

[**AccountSearchResponse**](AccountSearchResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successfully Retrieved Account |  -  |
**400** | Bad Request |  -  |

<a name="searchAccount"></a>
# **searchAccount**
> PagedResponseAccountSearchResponse searchAccount(accountSearchRequest, limit, offset)

Returns the account information for a company

### Example
```java
// Import classes:
import com.mastercard.developer.mastercard_loyalty_enrollment_client.ApiClient;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.ApiException;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.Configuration;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.models.*;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.api.AccountApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://api.mastercard.com/loyalty/enrollment/api");

    AccountApi apiInstance = new AccountApi(defaultClient);
    AccountSearchRequest accountSearchRequest = new AccountSearchRequest(); // AccountSearchRequest | accountSearchRequest
    Integer limit = 25; // Integer | Number of records per page.
    Integer offset = 0; // Integer | Result page you want to retrieve (0..N)
    try {
      PagedResponseAccountSearchResponse result = apiInstance.searchAccount(accountSearchRequest, limit, offset);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AccountApi#searchAccount");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **accountSearchRequest** | [**AccountSearchRequest**](AccountSearchRequest.md)| accountSearchRequest |
 **limit** | **Integer**| Number of records per page. | [optional]
 **offset** | **Integer**| Result page you want to retrieve (0..N) | [optional]

### Return type

[**PagedResponseAccountSearchResponse**](PagedResponseAccountSearchResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successfully Retrieved Account(s) |  -  |
**400** | Bad Request |  -  |

<a name="updateAccount"></a>
# **updateAccount**
> AccountResponse updateAccount(referenceId, accountUpdateRequest)

Updates the status for an account of the company

### Example
```java
// Import classes:
import com.mastercard.developer.mastercard_loyalty_enrollment_client.ApiClient;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.ApiException;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.Configuration;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.models.*;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.api.AccountApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://api.mastercard.com/loyalty/enrollment/api");

    AccountApi apiInstance = new AccountApi(defaultClient);
    String referenceId = d8753780-62d1-4775-9549-8ed92c48c290; // String | Reference Id
    AccountUpdateRequest accountUpdateRequest = new AccountUpdateRequest(); // AccountUpdateRequest | accountUpdateRequest
    try {
      AccountResponse result = apiInstance.updateAccount(referenceId, accountUpdateRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AccountApi#updateAccount");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **referenceId** | **String**| Reference Id |
 **accountUpdateRequest** | [**AccountUpdateRequest**](AccountUpdateRequest.md)| accountUpdateRequest |

### Return type

[**AccountResponse**](AccountResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successfully Updated Account |  -  |
**400** | Bad Request |  -  |

