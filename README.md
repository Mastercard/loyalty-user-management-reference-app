# Mastercard Loyalty Enrollment Api  Reference Application

## Description
This Reference Application is a guide for using Mastercard Loyalty Enrollment API's.

## Requirements

Reference Application requires:
1. Java 1.8+
2. Maven/Gradle

## Usage
### 1. Download the latest swagger file in YAML format from [here](https://stage.developer.mastercard.com/swagger/rebatepromotions)

### 2. Generate the client library
Go to the link [here](https://developer.mastercard.com/platform/documentation/generating-and-configuring-a-mastercard-api-client/) 
to read up on how to generate the client library with the openapi-generator.

We are using this command that generate the mastercard-loyalty-enrollment-client folder.  
`openapi-generator generate -g java --library okhttp-gson -i Loyalty_Enrollment.yaml -c mastercard-loyalty-enrollment-config.json -o mastercard-loyalty-enrollment-client`

We have included the swagger file and mastercard-loyalty-enrollment-client folder that are embedded in the app

### 3. Maven Build
Go to the `mastercard-loyalty-enrollment-client` and run `mvn clean package`

### 4. Copy the library to lib folder
In the `mastercard-grp-enrollment-client`, go to `/target` and copy `mastercard-loyalty-enrollment-client-<version number>.jar` to the `lib folder` of this reference application.

### 5. Create a new project on Mastercard developer zone under your account
1. Go to <https://developer.mastercard.com/>
2. Create a new project
3. Add "Mastercard User Management Service" to your project
4. Download and save your keystore and client id

### 6. Replace the p12 file and your credentials 
1. Go to src/main/resources and add your keystore p12 file that obtained from **step 5**
2. (**IMPORTANT**) Rename your keystore p12 certificate in `src/main/resources` to `mastercard-loyalty-enrollment-sandbox.p12`. 
3. Replace your values for `CONSUMER_KEY`, `KEY_ALIAS`, `KEYSTORE_PASSWORD`,`X_MERCHANT_KEY`,`PARTNER_CODE` in config/EnrollmentConfig.java

### 7. Run the MastercardLoyaltyEnrollmentReference.java with this Maven Command
1. Update the pom.xml to ensure the target main class is correct: `<argument>com.mastercard.lts.rewards.MastercardLoyaltyEnrollmentReference</argument>`
2. Go to `project root directory` and run `mvn clean compile exec:exec -Dexec.executable=java -Dexec.classpathScope=compile`

## Use Case
This reference app describes one end to end flow for using MasterCard  Enrollment  API's it skips some endpoints :
1. Enroll User using userId, userIdType, programCode and companyId and obtain userReferenceId
2. Get User using userReferenceId
3. Search multiple users using userId, userIdType and companyId.
4. Update user details using userReferenceId
5. Enroll Account using userId, accountId , accountIdType and companyId and obtaint accountReferenceId
6. Get Account using accountReferenceId
7. Search multiple accounts using accountId, accountIdType, programIdentifier and companyId.
8. Update Account status using accountReferenceId and companyId.
9. Enroll User and account in one go using userId, userIdType, companyId, accountId, accountIdType,programCode, and ProgramIdentifier and obtain userReferenceId and accountreferenceId. 

## API Expected sample Response but values varies for reference id's
### Enroll User
Expected response from MasterCard Loyalty Enrollment API:
```
{
  "referenceId": "2a53135b-c1f2-4cec-88a9-b0941304d9d7",
  "account": {
    "referenceId": "d8753780-62d1-4775-9549-8ed92c48c290"
  }
}
```
### Get User
Expected response from Mastercard Loyalty Enrollment API:
```
{
  "companyId": 611532,
  "userIdType": "CUSTOMER_NUMBER",
  "userId": "C02333333325",
  "firstName": "John",
  "lastName": "Smith",
  "motherMaidenName": "Smith",
  "gender": 1,
  "birthDate": "1975-03-11",
  "nationalIdentifier": "324-56-7574",
  "businessPhoneNumber": "+1(0)1234567890",
  "mobilePhoneNumber": "+1(0)1234567891",
  "homePhoneNumber": "+1(0)1234567892",
  "emailAddress": "abc@example.com",
  "vip": true,
  "employee": false,
  "genericIdentification": "Duster",
  "genericIdentificationDescription": "Pet's name",
  "address": {
    "addressLine1": "10 Upper Bank Street",
    "addressLine2": "Chesterfield",
    "addressLine3": "Canary Wharf",
    "addressLine4": "Tower Hamlets",
    "city": "London",
    "countryCode": "UK",
    "postalCode": "E1 6AN",
    "stateProvinceCode": "JS"
  },
  "referenceId": "2a53135b-c1f2-4cec-88a9-b0941304d9d7"
}
```
### Update user using userReferenceId
Expected response from Mastercard Loyalty Enrollment API:
```
{
  "referenceId": "2a53135b-c1f2-4cec-88a9-b0941304d9d7"
}
```
### Search all users associated with userId, userIdType and companyId
Expected response from Mastercard Loyalty Enrollment API:
```
{
  "count": 0,
  "limit": 0,
  "offset": 0,
  "total": 0,
  "items": [
    {
      "companyId": 611532,
      "userIdType": "CUSTOMER_NUMBER",
      "userId": "C02333333325",
      "firstName": "John",
      "lastName": "Smith",
      "motherMaidenName": "Smith",
      "gender": 1,
      "birthDate": "1975-03-11",
      "nationalIdentifier": "324-56-7574",
      "businessPhoneNumber": "+1(0)1234567890",
      "mobilePhoneNumber": "+1(0)1234567891",
      "homePhoneNumber": "+1(0)1234567892",
      "emailAddress": "abc@example.com",
      "vip": true,
      "employee": false,
      "genericIdentification": "Duster",
      "genericIdentificationDescription": "Pet's name",
      "address": {
        "addressLine1": "10 Upper Bank Street",
        "addressLine2": "Chesterfield",
        "addressLine3": "Canary Wharf",
        "addressLine4": "Tower Hamlets",
        "city": "London",
        "countryCode": "UK",
        "postalCode": "E1 6AN",
        "stateProvinceCode": "JS"
      },
      "referenceId": "2a53135b-c1f2-4cec-88a9-b0941304d9d7"
    }
  ]
}
```
### Enroll account using userId,companyId,accountId and accountIdType
Expected response from MasterCard Loyalty API:
```
{
  "referenceId": "d8753780-62d1-4775-9549-8ed92c48c290"
}
```
### Get account Deatils using accountReferenceId
Expected response from MasterCard Loyalty API:
```
{
  "companyId": 611532,
  "userIdType": "CUSTOMER_NUMBER",
  "userId": "C02333333325",
  "accountIdType": "ACCOUNT_NUMBER",
  "accountId": 5330333671236516,
  "status": "NEW",
  "productCode": "7274VCC",
  "programIdentifier": "ZXSzM",
  "openDate": "2015-01-01",
  "enrollmentDate": "2018-03-11",
  "referenceId": "d8753780-62d1-4775-9549-8ed92c48c290"
}
```
### Search all accounts associated companyId, accountIdType, accountId and programIdentifier
Expected response from MasterCard Loyalty API:
```
{
  "count": 0,
  "limit": 0,
  "offset": 0,
  "total": 0,
  "items": [
    {
      "companyId": 611532,
      "userIdType": "CUSTOMER_NUMBER",
      "userId": "C02333333325",
      "accountIdType": "ACCOUNT_NUMBER",
      "accountId": 5330333671236516,
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
### Update account using companyId,status using accountReferenceId
Expected response from MasterCard Loyalty API:
```
{
  "referenceId": "d8753780-62d1-4775-9549-8ed92c48c290"
}
```

## Author
- Name: **Mastercard**
- Contact: **apisupport@mastercard.com**

## Support
Please email to **apisupport@mastercard.com** for additional support if required. 

## License
Apache 2 License

### Copyright © 1994-2020, All Right Reserved by Mastercard.
