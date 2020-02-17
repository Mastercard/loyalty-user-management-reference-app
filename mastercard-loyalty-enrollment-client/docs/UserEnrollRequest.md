

# UserEnrollRequest

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**companyId** | **String** | The unique identifier of the company | 
**userIdType** | **String** | For Issuer, this value has to be CUSTOMER_NUMBER | 
**userId** | **String** | The unique identifier assigned by the Company to the User | 
**firstName** | **String** | User&#39;s first name |  [optional]
**lastName** | **String** | User&#39;s last name |  [optional]
**motherMaidenName** | **String** | User&#39;s mother maiden name |  [optional]
**gender** | **String** | The ISO code used to determine the gender of the User; Expected values- 0(not known-default), 1(male), 2(female), 9(not applicable) |  [optional]
**birthDate** | **String** | User&#39;s date of birth |  [optional]
**nationalIdentifier** | **String** | National Identifier of the User |  [optional]
**businessPhoneNumber** | **String** | Valid business phone number used by the User |  [optional]
**mobilePhoneNumber** | **String** | Valid mobile phone number used by the User |  [optional]
**homePhoneNumber** | **String** | Valid home phone number used by the User |  [optional]
**emailAddress** | **String** | Valid email address used by the User |  [optional]
**vip** | **Boolean** | Allows the user to holds VIP status, default value is false |  [optional]
**employee** | **Boolean** | Allows the user to holds EMPLOYEE status, default value is false |  [optional]
**genericIdentification** | **String** | Answer to the user’s verification question |  [optional]
**genericIdentificationDescription** | **String** | The user’s custom verification question |  [optional]
**address** | [**UserAddress**](UserAddress.md) |  |  [optional]
**account** | [**Account**](Account.md) |  |  [optional]



