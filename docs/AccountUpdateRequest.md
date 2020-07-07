# AccountUpdateRequest

## Properties <a name="properties"></a>

| Name | Type | Max Length | Description | Notes |
| :--- | :--- | :--------- | :---------- | :---- |
| **companyId** | **String** | 11 | Mastercard assigned unique identifier for a client. | [optional] |
| **memberICA** | **Long** | | Interbank Card Association number assigned by Mastercard to the client. | [optional] |
| **status** | **String** | 20 | Status for the user's account ||

## Sample JSON

```json
{
  "companyId": "611532",
  "status": "NEW"
}
```
