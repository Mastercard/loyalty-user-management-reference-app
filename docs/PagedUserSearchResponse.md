# PagedUserSearchResponse

## Properties

| Name | Type | Max Length | Description | Notes |
| :--- | :--- | :--------- | :---------- | :---- |
| **count** | **Integer** | | The number of items that are in this offset batch | [optional] |
| **limit** | **Integer** | | The number of items in the list to be limited to | [optional] |
| **offset** | **Integer** | | The number of items to offset the start of the list from | [optional] |
| **total** | **Long** | | The total number of items that are in the entire collection | [optional] |
| **items** | [**List&lt;UserSearchResponse&gt;**](UserSearchResponse.md) | | | [optional] |

## Sample JSON

```json
{
  "count": 1,
  "limit": 25,
  "offset": 0,
  "total": 1,
  "items": [
    {
      "companyId": "611532",
      "userId": "C02333333325",
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
      },
      "referenceId": "2a53135b-c1f2-4cec-88a9-b0941304d9d7"
    }
  ]
}
```
