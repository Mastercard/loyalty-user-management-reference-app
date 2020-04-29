# Error

## Properties <a name="properties"></a>

| Name | Type | Max Length | Description | Notes |
| :--- | :--- | :--------- | :---------- | :---- |
| **source** | **String** | | Source of the error | [optional] |
| **reasonCode** | **String** | | A unique constant identifying the error case encountered during transaction processing. | [optional] |
| **description** | **String** | | Short description of the ReasonCode field. | [optional] |
| **recoverable** | **Boolean** | | Indicates whether this error will always be returned for this request, or retrying could change the outcome. | [optional] |
| **details** | **String** | | Where appropriate, indicates detailed information about data received. | [optional] |
