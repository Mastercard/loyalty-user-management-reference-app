# Errors

## Properties

| Name | Type | Max Length | Description | Notes |
| :--- | :--- | :--------- | :---------- | :---- |
| **errors** | [**ErrorList**](ErrorList.md) | | Contains object of error list | |

## Sample JSON

```json
{
  "Errors": {
    "Error": [
      {
        "Source": "Loyalty-Enrollment",
        "ReasonCode": "INVALID_FIELD_FORMAT",
        "Description": "Email Address must be in the valid format.",
        "Recoverable": false,
        "Details": null
      }
    ]
  }
}
```
