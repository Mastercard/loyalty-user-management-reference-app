# PagedAccountSearchResponse

## Properties <a name="properties"></a>

| Name | Type | Max Length | Description | Notes |
| :--- | :--- | :--------- | :---------- | :---- |
| **count** | **Integer** | | The number of items that are in this offset batch | [optional] |
| **limit** | **Integer** | | The number of items in the list to be limited to | [optional] |
| **offset** | **Integer** | | The number of items to offset the start of the list from | [optional] |
| **total** | **Long** | | The total number of items that are in the entire collection | [optional] |
| **items** | [**List&lt;AccountSearchResponse&gt;**](AccountSearchResponse.md) | | | [optional] |

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
      "userId": "C02333333325",
      "accountId": "5330333671236516",
      "status": "NEW",
      "productCode": "7274VCC",
      "programIdentifier": "ZXSzM",
      "openDate": "2015-01-01",
      "enrollmentDate": "2018-03-11",
      "referenceId": "d8753780-62d1-4775-9549-8ed92c48c290"
    }
  ]
}
```
