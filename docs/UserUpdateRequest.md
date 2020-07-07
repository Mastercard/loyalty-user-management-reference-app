# UserUpdateRequest

## Properties <a name="properties"></a>

| Name | Type | Max Length | Description | Notes |
| :--- | :--- | :--------- | :---------- | :---- |
| **companyId** | **String** | 11 | Mastercard assigned unique identifier for a client. | [optional] |
| **memberICA** | **Long** | | Interbank Card Association number assigned by Mastercard to the client. | [optional] |
| **firstName** | **String** | 50 | User's first name | [optional] |
| **lastName** | **String** | 50 | User's last name | [optional] |
| **motherMaidenName** | **String** | 30 | User's mother maiden name | [optional] |
| **gender** | **String** | 1 | The ISO code used to determine the gender of the User | [optional] |
| **birthDate** | **String** | 10 | User's date of birth in the format yyyy-MM-dd | [optional] |
| **nationalIdentifier** | **String** | 50 | User's unique identifier assigned by the national or government system. For example, passport number or tax identification number | [optional] |
| **businessPhoneNumber** | **String** | 25 | User's business phone number | [optional] |
| **mobilePhoneNumber** | **String** | 25 | User's mobile phone number | [optional] |
| **homePhoneNumber** | **String** | 25 | User's home phone number | [optional] |
| **emailAddress** | **String** | 120 | User's email address | [optional] |
| **vip** | **Boolean** | | Used to assign VIP status to a user, default value is false | [optional] |
| **employee** | **Boolean** | | Used to assign Employee status to a user, default value is false | [optional] |
| **address** | [**UserAddress**](UserAddress.md) | | User's address details | [optional] |

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
