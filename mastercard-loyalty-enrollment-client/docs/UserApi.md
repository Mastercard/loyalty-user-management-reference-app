# UserApi

All URIs are relative to *http://api.mastercard.com/loyalty/enrollment/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**enrollUser**](UserApi.md#enrollUser) | **POST** /users | Enrolls a new user for a company, user may or may not provide other demographic information for enrollment
[**findUser**](UserApi.md#findUser) | **GET** /users/{reference_id} | Returns the demographic information related to the existing user
[**searchUser**](UserApi.md#searchUser) | **POST** /users/searches | Returns the demographic information related to the existing user information
[**updateUser**](UserApi.md#updateUser) | **PUT** /users/{reference_id} | Updates the demographic information for an existing user of the company


<a name="enrollUser"></a>
# **enrollUser**
> UserEnrollResponse enrollUser(userEnrollRequest)

Enrolls a new user for a company, user may or may not provide other demographic information for enrollment

### Example
```java
// Import classes:
import com.mastercard.developer.mastercard_loyalty_enrollment_client.ApiClient;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.ApiException;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.Configuration;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.models.*;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.api.UserApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://api.mastercard.com/loyalty/enrollment/api");

    UserApi apiInstance = new UserApi(defaultClient);
    UserEnrollRequest userEnrollRequest = new UserEnrollRequest(); // UserEnrollRequest | userEnrollRequest
    try {
      UserEnrollResponse result = apiInstance.enrollUser(userEnrollRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserApi#enrollUser");
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
 **userEnrollRequest** | [**UserEnrollRequest**](UserEnrollRequest.md)| userEnrollRequest |

### Return type

[**UserEnrollResponse**](UserEnrollResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**201** | Successfully Enrolled User |  -  |
**400** | Bad Request |  -  |

<a name="findUser"></a>
# **findUser**
> UserSearchResponse findUser(referenceId)

Returns the demographic information related to the existing user

### Example
```java
// Import classes:
import com.mastercard.developer.mastercard_loyalty_enrollment_client.ApiClient;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.ApiException;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.Configuration;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.models.*;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.api.UserApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://api.mastercard.com/loyalty/enrollment/api");

    UserApi apiInstance = new UserApi(defaultClient);
    String referenceId = 2a53135b-c1f2-4cec-88a9-b0941304d9d7; // String | Reference Id
    try {
      UserSearchResponse result = apiInstance.findUser(referenceId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserApi#findUser");
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

[**UserSearchResponse**](UserSearchResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successfully Retrieved User |  -  |
**400** | Bad Request |  -  |

<a name="searchUser"></a>
# **searchUser**
> PagedResponseUserSearchResponse searchUser(userSearchRequest, limit, offset)

Returns the demographic information related to the existing user information

### Example
```java
// Import classes:
import com.mastercard.developer.mastercard_loyalty_enrollment_client.ApiClient;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.ApiException;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.Configuration;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.models.*;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.api.UserApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://api.mastercard.com/loyalty/enrollment/api");

    UserApi apiInstance = new UserApi(defaultClient);
    UserSearchRequest userSearchRequest = new UserSearchRequest(); // UserSearchRequest | userSearchRequest
    Integer limit = 25; // Integer | Number of records per page.
    Integer offset = 0; // Integer | Result page you want to retrieve (0..N)
    try {
      PagedResponseUserSearchResponse result = apiInstance.searchUser(userSearchRequest, limit, offset);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserApi#searchUser");
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
 **userSearchRequest** | [**UserSearchRequest**](UserSearchRequest.md)| userSearchRequest |
 **limit** | **Integer**| Number of records per page. | [optional]
 **offset** | **Integer**| Result page you want to retrieve (0..N) | [optional]

### Return type

[**PagedResponseUserSearchResponse**](PagedResponseUserSearchResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successfully Retrieved User(s) |  -  |
**400** | Bad Request |  -  |

<a name="updateUser"></a>
# **updateUser**
> UserUpdateResponse updateUser(referenceId, userUpdateRequest)

Updates the demographic information for an existing user of the company

### Example
```java
// Import classes:
import com.mastercard.developer.mastercard_loyalty_enrollment_client.ApiClient;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.ApiException;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.Configuration;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.models.*;
import com.mastercard.developer.mastercard_loyalty_enrollment_client.api.UserApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://api.mastercard.com/loyalty/enrollment/api");

    UserApi apiInstance = new UserApi(defaultClient);
    String referenceId = 2a53135b-c1f2-4cec-88a9-b0941304d9d7; // String | Reference Id
    UserUpdateRequest userUpdateRequest = new UserUpdateRequest(); // UserUpdateRequest | userUpdateRequest
    try {
      UserUpdateResponse result = apiInstance.updateUser(referenceId, userUpdateRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserApi#updateUser");
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
 **userUpdateRequest** | [**UserUpdateRequest**](UserUpdateRequest.md)| userUpdateRequest |

### Return type

[**UserUpdateResponse**](UserUpdateResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successfully Updated User |  -  |
**400** | Bad Request |  -  |

