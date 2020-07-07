# AccountEnrollRequest

## Properties <a name="properties"></a>

| Name | Type | Max Length | Description | Notes |
| :--- | :--- | :--------- | :---------- | :---- |
| **companyId** | **String** | 11 | Mastercard assigned unique identifier for a client. | [optional] |
| **memberICA** | **Long** | | Interbank Card Association number assigned by Mastercard to the client. | [optional] |
| **userNumber** | **String** | 30 | The unique identifier of a user assigned by the client. ||
| **accountNumber** | **String** | 19 | The unique account identifier defined and provided by the client. ||
| **status** | **String** | 20 | Status for the user's account | [optional] |
| **productCode** | **String** | 20 | The account's associated reward product identifier as defined by the client and configured during the implementation of the program. ||
| **programEnrollmentCode** | **String** | 18 | The unique identifier for a client's reward program, defined by the client and configured during implementation. ||
| **openDate** | **String** | 10 | The date of account opening as defined by the client in the format yyyy-MM-dd | [optional] |
| **enrollmentDate** | **String** | 10 | The date for when the account becomes eligible for rewards program in the format yyyy-MM-dd | [optional] |

## Sample JSON

```json
{
  "companyId": "611532",
  "memberICA": "888882",
  "userNumber": "C02333333325",
  "accountNumber": "5330333671236516",
  "status": "NEW",
  "productCode": "7274VCC",
  "programEnrollmentCode": "ZXSzM",
  "openDate": "2015-01-01",
  "enrollmentDate": "2018-03-11"
}
```
