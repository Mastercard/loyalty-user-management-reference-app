# UserSearchRequest

## Properties <a name="properties"></a>

| Name | Type | Max Length | Description | Notes |
| :--- | :--- | :--------- | :---------- | :---- |
| **companyId** | **String** | 11 | Mastercard assigned unique identifier for a client. | [optional] |
| **memberICA** | **Long** | | Interbank Card Association number assigned by Mastercard to the client. | [optional] |
| **userNumber** | **String** | 30 | The unique identifier of a user assigned by the client. ||

## Sample JSON

```json
{
  "companyId": "611532",
  "userNumber": "C02333333325"
}
```
