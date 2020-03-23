# Reference Implementation for Mastercard Loyalty User Management

## Table of Contents
- [Overview](#overview)
  * [Compatibility](#compatibility)
  * [References](#references)
- [Usage](#usage)
  * [Prerequisites](#prerequisites)
  * [Configuration](#configuration)
  * [Integrating with OpenAPI Generator](#integrating-with-openapi-generator)
  * [Build and Execute](#build-and-execute)
- [Use Cases](#use-cases)
- [API Reference](#api-reference)
  * [Request Examples](#request-examples)
- [Support](#support)
- [License](#license)

## Overview <a name="overview"></a>
This is a reference application that demonstrates how Loyalty User Management API can be used for the supported operations. Please see here for details on the API: [Mastercard Developers](https://developer.mastercard.com/loyalty-user-managemennt/documentation/). 
This application illustrates connecting to the Loyalty User Management API with / without encryption.
Both the approaches require consumer key and .p12 file as received from [Mastercard Developers](https://developer.mastercard.com/dashboard).

### Compatibility <a name="compatibility"></a>
* [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html) or later

### References <a name="references"></a>
* [Mastercard’s OAuth Signer library](https://github.com/Mastercard/oauth1-signer-java)
* [Using OAuth 1.0a to Access Mastercard APIs](https://developer.mastercard.com/platform/documentation/using-oauth-1a-to-access-mastercard-apis/)

## Usage <a name="usage"></a>
### Prerequisites <a name="prerequisites"></a>
* [Mastercard Developers Account](https://developer.mastercard.com/dashboard) with access to Loyalty User Management API
* A text editor or IDE
* [Spring Boot 2.2+](https://spring.io/projects/spring-boot)
* [Apache Maven 3.3+](https://maven.apache.org/download.cgi)
* Set up the `JAVA_HOME` environment variable to match the location of your Java installation.
<br/><br/>
### Configuration <a name="configuration"></a>
* Create an account at [Mastercard Developers](https://developer.mastercard.com/account/sign-up).  
* Create a new project and add `Loyalty User Management` API to your project.   
* Configure project and download signing key. It will download the zip file.  
* Select `.p12` file from zip and copy it to `src/main/resources` in the project folder.
* Open `${project.basedir}/src/main/resources/application.properties` and configure below parameters.
    
    >**mastercard.api.base.path=https://sandbox.api.mastercard.com/loyalty/rewards/enrollment**, its a static field, will be used as a host to make API calls.
    
    **Below properties will be required for authentication of API calls.**
    
    >**mastercard.api.key.file=**, this refers to .p12 file found in the signing key. Please place .p12 file at src\main\resources in the project folder and add classpath for .p12 file.
    
    >**mastercard.api.consumer.key=**, this refers to your consumer key. Copy it from "Keys" section on your project page in [Mastercard Developers](https://developer.mastercard.com/dashboard)
      
    >**mastercard.api.keystore.alias=keyalias**, this is the default value of key alias. If it is modified, use the updated one from keys section in [Mastercard Developers](https://developer.mastercard.com/dashboard).
    
    >**mastercard.api.keystore.password=keystorepassword**, this is the default value of key alias. If it is modified, use the updated one from keys section in [Mastercard Developers](https://developer.mastercard.com/dashboard).

### Integrating with OpenAPI Generator <a name="integrating-with-openapi-generator"></a>
[OpenAPI Generator](https://github.com/OpenAPITools/openapi-generator) generates API client libraries from [OpenAPI Specs](https://github.com/OAI/OpenAPI-Specification). 
It provides generators and library templates for supporting multiple languages and frameworks.

The `com.mastercard.developer.interceptors` package will provide you with some request interceptor classes you can use when configuring your API client. These classes will take care of adding the correct `Authorization` header before sending the request.

See also:
* [OpenAPI Generator (maven Plugin)](https://mvnrepository.com/artifact/org.openapitools/openapi-generator-maven-plugin)
* [OpenAPI Generator (executable)](https://mvnrepository.com/artifact/org.openapitools/openapi-generator-cli)
* [CONFIG OPTIONS for java](https://github.com/OpenAPITools/openapi-generator/blob/master/docs/generators/java.md)

#### OpenAPI Generator Plugin Configuration
```xml
<!-- https://mvnrepository.com/artifact/org.openapitools/openapi-generator-maven-plugin -->
<plugin>
    <groupId>org.openapitools</groupId>
    <artifactId>openapi-generator-maven-plugin</artifactId>
    <version>${openapi-generator.version}</version>
    <executions>
        <execution>
            <goals>
                <goal>generate</goal>
            </goals>
            <configuration>
                <inputSpec>${project.basedir}/src/main/resources/Loyalty_User_Management-api-spec.yaml
                </inputSpec>
                <generatorName>java</generatorName>
                <library>okhttp-gson</library>
                <generateApiTests>false</generateApiTests>
                <generateModelTests>false</generateModelTests>
                <configOptions>
                    <sourceFolder>src/gen/java/main</sourceFolder>
                    <dateLibrary>java8</dateLibrary>
                </configOptions>
            </configuration>
        </execution>
    </executions>
</plugin>
```
<br/>

#### Generating The API Client Sources
Now that you have all the dependencies you need, you can generate the sources. To do this, use one of the following two methods:

`Using IDE`
* **Method 1**<br/>
  In IntelliJ IDEA, open the Maven window **(View > Tool Windows > Maven)**. Click the icons `Reimport All Maven Projects` and `Generate Sources and Update Folders for All Projects`

* **Method 2**<br/>
  In the same menu, navigate to the commands **({Project name} > Lifecycle)**, select `clean` and `compile` then click the icon `Run Maven Build`. 

`Using Terminal`<br/>
* Navigate to the root directory of the project within a terminal window and execute `mvn clean compile` command.
<br/><br/>
### Build and Execute <a name="build-and-execute"></a>
Once you’ve added the correct properties, we can build the application. We can do this by navigating to the project’s base directory from the terminal and running the following command

`mvn clean install`

When the project builds successfully you can then run the following command to start the project

`java -jar target/loyalty-user-management-reference-1.0.0.jar`
<br/><br/>
## Use Cases <a name="use-cases"></a>
> Case 1: [USER ENROLLMENT](https://developer.mastercard.com/loyalty-user-management/documentation/use-cases/user-enrollment/)
  - Cardholder’s or User’s enrollment into Mastercard Rewards platform.
  - Expected Request
  ```json
  {
    "companyId": "611532",
    "userId": "C02333333325",
    "firstName": "John",
    "lastName": "Smith",
    "motherMaidenName": "Jennifer",
    "gender": 1,
    "birthDate": "1975-03-11",
    "nationalIdentifier": "324-56-7574",
    "businessPhoneNumber": "+1(0)1234567890",
    "mobilePhoneNumber": "+1(0)1234567891",
    "homePhoneNumber": "+1(0)1234567892",
    "emailAddress": "john.smith@mastercard.com",
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
      "countryCode": "ENG",
      "postalCode": "E1 6AN",
      "stateProvinceCode": "JS"
    }
  }
  ```
  - Response
  ```json
  {
    "referenceId": "2a53135b-c1f2-4cec-88a9-b0941304d9d7"
  }
  ```
> Case 2: [USER WITH ACCOUNT ENROLLMENT](https://developer.mastercard.com/loyalty-user-management/documentation/use-cases/user-account-enrollment/)
  - Cardholder’s or User’s with Account enrollment into Mastercard Rewards platform.
  - Expected Request
  ```json
  {
    "companyId": "611532",
    "userId": "C02333333325",
    "firstName": "John",
    "lastName": "Smith",
    "motherMaidenName": "Jennifer",
    "gender": 1,
    "birthDate": "1975-03-11",
    "nationalIdentifier": "324-56-7574",
    "businessPhoneNumber": "+1(0)1234567890",
    "mobilePhoneNumber": "+1(0)1234567891",
    "homePhoneNumber": "+1(0)1234567892",
    "emailAddress": "john.smith@mastercard.com",
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
      "countryCode": "ENG",
      "postalCode": "E1 6AN",
      "stateProvinceCode": "JS"
    },
    "account": {
      "accountIdType": "ACCOUNT_NUMBER",
      "accountId": 5330333671236516,
      "status": "NEW",
      "productCode": "7274VCC",
      "programIdentifier": "ZXSzM",
      "openDate": "2015-01-01",
      "enrollmentDate": "2018-03-11"
    }
  }
  ```
  - Response
  ```json
  {
    "referenceId": "2a53135b-c1f2-4cec-88a9-b0941304d9d7",
    "account": {
      "referenceId": "d8753780-62d1-4775-9549-8ed92c48c290"
    }
  }
  ```
> Case 3: [USER SEARCH](https://developer.mastercard.com/loyalty-user-management/documentation/use-cases/user-search/)
  - Retrieves a user’s details based on search criteria or Mastercard generated unique Id.
  - Expected Request
  ```json
  {
    "companyId": "611532",
    "userId": "C02333333325"
  }
  ```
  - Response
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
        "firstName": "John",
        "lastName": "Smith",
        "motherMaidenName": "Jennifer",
        "gender": 1,
        "birthDate": "1975-03-11",
        "nationalIdentifier": "324-56-7574",
        "businessPhoneNumber": "+1(0)1234567890",
        "mobilePhoneNumber": "+1(0)1234567891",
        "homePhoneNumber": "+1(0)1234567892",
        "emailAddress": "john.smith@mastercard.com",
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
          "countryCode": "ENG",
          "postalCode": "E1 6AN",
          "stateProvinceCode": "JS"
        },
        "referenceId": "2a53135b-c1f2-4cec-88a9-b0941304d9d7"
      }
    ]
  }
  ```
> Case 4: [ACCOUNT ENROLLMENT](https://developer.mastercard.com/loyalty-user-management/documentation/use-cases/account-enrollment/)
  - User’s Account enrollment into Mastercard Rewards platform.
  - Expected Request
  ```json
  {
    "companyId": "611532",
    "userId": "C02333333325",
    "accountId": "5330333671236516",
    "accountIdType": "ACCOUNT_NUMBER",
    "status": "NEW",
    "productCode": "7274VCC",
    "programIdentifier": "ZXSzM",
    "openDate": "2015-01-01",
    "enrollmentDate": "2018-03-11"
  }
  ```
  - Response
  ```json
  {
    "referenceId": "d8753780-62d1-4775-9549-8ed92c48c290"
  }
  ```
> Case 5: [ACCOUNT SEARCH](https://developer.mastercard.com/loyalty-user-management/documentation/use-cases/account-search/)
  - Retrieves a user’s account details based on search criteria or Mastercard generated unique Id.
  - Expected Request
  ```json
  {
    "companyId": "611532",
    "accountId": "5330333671236516",
    "accountIdType": "ACCOUNT_NUMBER",
    "programIdentifier": "ZXSzM"
  }
  ```
  - Response
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
        "accountIdType": "ACCOUNT_NUMBER",
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

<br/><br/>
## API Reference <a name="api-reference"></a>
To develop a client application that consumes a RESTful Loyalty User Management API with Spring Boot, refer below documentation.

| API | Endpoint | HTTP Method | Description |
| :-- | :------- | :---------- | :---------- |
| [User Enrollment](https://developer.mastercard.com/loyalty-user-managemennt/documentation/api-reference/#apis) | `/users` | POST | Cardholder’s or User's enrollment into Mastercard Rewards platform. |
| [User with Account Enrollment](https://developer.mastercard.com/loyalty-user-managemennt/documentation/api-reference/#apis) | `/users` | POST | Cardholder’s or User's with Account enrollment into Mastercard Rewards platform. |
| [User Find By Id](https://developer.mastercard.com/loyalty-user-managemennt/documentation/api-reference/#apis) | `/users/{reference_id}` | GET | Retrieves a cardholder’s details by Mastercard generated unique Id. |
| [User Search](https://developer.mastercard.com/loyalty-user-managemennt/documentation/api-reference/#apis) | `/users/searches` | POST | Retrieves a cardholder’s details based on search criteria. |
| [User Demographic Update](https://developer.mastercard.com/loyalty-user-managemennt/documentation/api-reference/#apis) | `/users/{reference_id}` | PUT | Updates existing cardholder’s demographic details. |
| [Account Enrollment](https://developer.mastercard.com/loyalty-user-managemennt/documentation/api-reference/#apis) | `/accounts` | POST | Cardholder’s Account enrollment into Mastercard Rewards platform. |
| [Account Find By Id](https://developer.mastercard.com/loyalty-user-managemennt/documentation/api-reference/#apis) | `/accounts/{reference_id}` | GET | Retrieves a cardholder’s account details by Mastercard generated unique Id. |
| [Account Search](https://developer.mastercard.com/loyalty-user-managemennt/documentation/api-reference/#apis) | `/accounts/searches` | POST | Retrieves a cardholder’s account details based on search criteria. |
| [Account Status Update](https://developer.mastercard.com/loyalty-user-managemennt/documentation/api-reference/#apis) | `/accounts/{reference_id}` | PUT | Updates existing cardholder’s account status. |   

### Request Examples <a name="request-examples"></a>
You can change the default input passed to APIs, modify values in following files,
* `com.mastercard.developer.example.UserExample.java`
* `com.mastercard.developer.example.AccountExample.java`
<br/><br/>
## Support <a name="support"></a>
If you would like further information, please send an email to LoyaltyPromotions.Support@mastercard.com
<br/><br/>
## License <a name="license"></a>
Copyright 2020 Mastercard
 
Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
