# UserEnrollResponse

## Properties <a name="properties"></a>

| Name | Type | Max Length | Description | Notes |
| :--- | :--- | :--------- | :---------- | :---- |
| **id** | **String** | 50 | Unique User identifier assigned by Mastercard and provided to client at the time of user enrollment response. ||
| **account** | [**AccountResponse**](AccountResponse.md) | | | [optional] |

## Sample JSON

### User <a name="user"></a>
```json
{
  "id": "2a53135b-c1f2-4cec-88a9-b0941304d9d7"
}
```

### User with Account <a name="user-account"></a>
```json
{
  "id": "2a53135b-c1f2-4cec-88a9-b0941304d9d7",
  "account": {
    "id": "d8753780-62d1-4775-9549-8ed92c48c290"
  }
}
```
