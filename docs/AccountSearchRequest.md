# AccountSearchRequest

## Properties

| Name | Type | Max Length | Description | Notes |
| :--- | :--- | :--------- | :---------- | :---- |
| **companyId** | **String** | 11 | Mastercard or client defined unique identifier for a company ||
| **accountId** | **String** | 19 | The User's bank account which can be a credit card account or debit card account ||
| **programIdentifier** | **String** | 18 | The unique identifier for a Program within a company | [optional] |

## Sample JSON

```json
{
  "companyId": "611532",
  "accountId": "5330333671236516",
  "programIdentifier": "ZXSzM"
}
```
