# UserUpdateRequest

## Properties

| Name | Type | Max Length | Description | Notes |
| :--- | :--- | :--------- | :---------- | :---- |
| **companyId** | **String** | 11 | Mastercard or client defined unique identifier for a company ||
| **firstName** | **String** | 50 | First name of User | [optional] |
| **lastName** | **String** | 50 | Last name of User | [optional] |
| **motherMaidenName** | **String** | 30 | Mother maiden name of User | [optional] |
| **gender** | **String** | 1 | The ISO code used to determine the gender of the User | [optional] |
| **birthDate** | **String** | 10 | Date of birth of user in the format YYYY-MM-DD | [optional] |
| **nationalIdentifier** | **String** | 50 | National Identifier of the User | [optional] |
| **businessPhoneNumber** | **String** | 25 | Business phone number of the User | [optional] |
| **mobilePhoneNumber** | **String** | 25 | Mobile phone number of the User | [optional] |
| **homePhoneNumber** | **String** | 25 | Home phone number of the User | [optional] |
| **emailAddress** | **String** | 120 | Email address of the User | [optional] |
| **vip** | **Boolean** | | Allows the user to holds VIP status, default value is false | [optional] |
| **employee** | **Boolean** | | Allows the user to holds EMPLOYEE status, default value is false | [optional] |
| **genericIdentification** | **String** | 15 | Answer to the user’s verification question | [optional] |
| **genericIdentificationDescription** | **String** | 25 | The user’s custom verification question | [optional] |
| **address** | [**UserAddress**](UserAddress.md) | | Address details of the User | [optional] |

## Sample JSON

```json
{
  "companyId": "611532",
  "firstName": "John",
  "lastName": "Smith",
  "motherMaidenName": "Jennifer",
  "gender": 1,
  "birthDate": "1975-03-11",
  "nationalIdentifier": "324-56-7574",
  "businessPhoneNumber": "+1(0)1234567890",
  "mobilePhoneNumber": "+1(0)1234567891",
  "homePhoneNumber": "+1(0)1234567892",
  "emailAddress": "john.smith@mastercard.com",
  "vip": true,
  "employee": false,
  "genericIdentification": "Duster",
  "genericIdentificationDescription": "Pet's name",
  "address": {
    "addressLine1": "10 Upper Bank Street",
    "addressLine2": "Chesterfield",
    "addressLine3": "Canary Wharf",
    "addressLine4": "Tower Hamlets",
    "city": "London",
    "countryCode": "ENG",
    "postalCode": "E1 6AN",
    "stateProvinceCode": "JS"
  }
}
```
