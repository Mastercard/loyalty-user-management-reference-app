# Account

## Properties <a name="properties"></a>

| Name | Type | Max Length | Description | Notes |
| :--- | :--- | :--------- | :---------- | :---- |
| **accountNumber** | **String** | 19 | The unique account identifier defined and provided by the client. ||
| **status** | **String** | 20 | Status for the user's account | [optional] |
| **productCode** | **String** | 20 | The account's associated reward product identifier as defined by the client and configured during the implementation of the program. ||
| **programEnrollmentCode** | **String** | 18 | The unique identifier for a client's reward program, defined by the client and configured during implementation. ||
| **openDate** | **String** | 10 | The date of account opening as defined by the client in the format yyyy-MM-dd | [optional] |
| **enrollmentDate** | **String** | 10 | The date for when the account becomes eligible for rewards program in the format yyyy-MM-dd | [optional] |
