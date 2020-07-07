# Error

## Properties <a name="properties"></a>

| Name | Type | Max Length | Description | Notes |
| :--- | :--- | :--------- | :---------- | :---- |
| **source** | **String** | 50 | Source of the error ||
| **reasonCode** | **String** | 100 | A unique constant identifying the error case encountered during transaction processing. ||
| **description** | **String** | 100 | Short description of the ReasonCode field. ||
| **recoverable** | **Boolean** | | Indicates whether this error will always be returned for this request, or retrying could change the outcome. ||
| **details** | **String** | 0 | Where appropriate, indicates detailed information about data received. ||
