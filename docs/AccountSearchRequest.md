# AccountSearchRequest

## Properties <a name="properties"></a>

| Name | Type | Max Length | Description | Notes |
| :--- | :--- | :--------- | :---------- | :---- |
| **companyId** | **String** | 11 | Mastercard assigned unique identifier for a client. | [optional] |
| **memberICA** | **Long** | | Interbank Card Association number assigned by Mastercard to the client. | [optional] |
| **accountNumber** | **String** | 19 | The unique account identifier defined and provided by the client. ||
| **programEnrollmentCode** | **String** | 18 | The unique identifier for a client's reward program, defined by the client and configured during implementation. ||

## Sample JSON

```json
{
  "companyId": "611532",
  "accountNumber": "5330333671236516",
  "programEnrollmentCode": "ZXSzM"
}
```
