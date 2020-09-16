# PagedResponseOfAccountSearchResponse

## Properties <a name="properties"></a>

| Name | Type | Max Length | Description | Notes |
| :--- | :--- | :--------- | :---------- | :---- |
| **count** | **Integer** | | The number of items that are in this offset batch ||
| **limit** | **Integer** | | The number of items in the list to be limited to ||
| **offset** | **Integer** | | The number of items to offset the start of the list from ||
| **total** | **Long** | | The total number of items that are in the entire collection ||
| **items** | [**List&lt;AccountSearchResponse&gt;**](AccountSearchResponse.md) | | ||

## Sample JSON

```json
{
  "count": 1,
  "limit": 25,
  "offset": 0,
  "total": 1,
  "items": [
    {
      "companyId": "611532",
      "memberICA": 611532,
      "userNumber": "C02333333325",
      "accountNumber": "5330333671236516",
      "status": "NEW",
      "productCode": "7274VCC",
      "programEnrollmentCode": "ZXSzM",
      "openDate": "2015-01-01",
      "enrollmentDate": "2018-03-11",
      "id": "d8753780-62d1-4775-9549-8ed92c48c290"
    }
  ]
}
```
