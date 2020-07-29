# Reference Implementation for Mastercard Loyalty User Management

[![](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/Mastercard/loyalty-user-management-reference/blob/master/LICENSE)

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
  * [Authorization](#authorization)
  * [Encryption and Decryption](#encryption-and-decryption)
    - [Loading Encryption Certificate](#loading-encryption-certificate)
    - [Loading Decryption Key](#loading-decryption-key)
    - [Configuring JWE Instance](#configuring-jwe-instance)  
    - [Encrypting Entire Payloads](#encrypting-entire-payloads)
    - [Decrypting Entire Payloads](#decrypting-entire-payloads)
  * [Request Examples](#request-examples)
  * [Recommendation](#recommendation)
- [Support](#support)
- [License](#license)

## Overview <a name="overview"></a>
This is a reference application to demonstrate how Loyalty User Management API can be used for the supported operations. Please see here for details on the API: [Mastercard Developers](https://developer.mastercard.com/loyalty-user-managemennt/documentation/).
This application illustrates connecting to the Loyalty User Management API with encryption. To call these APIs, consumer key, .p12 files and encryption certs are required from your [Mastercard Developers](https://developer.mastercard.com/dashboard) project.

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

### Configuration <a name="configuration"></a>
* Create an account at [Mastercard Developers](https://developer.mastercard.com/account/sign-up).  
* Create a new project and add `Loyalty User Management` API to your project.   
* Configure project and download signing key. It will download the zip file.  
* Select `.p12` files from zip and copy it to `src/main/resources` in the project folder.
* Open `${project.basedir}/src/main/resources/application.properties` and configure below parameters.
    
    >**mastercard.api.base-path=https://sandbox.api.mastercard.com/loyalty/rewards/enrollment**, its a static field, will be used as a host to make API calls.
    
    **Below properties will be required for authentication of API calls.**
    
    >**mastercard.api.key-file=**, this refers to .p12 file found in the signing key. Please place .p12 file at src\main\resources in the project folder and add classpath for .p12 file.
    
    >**mastercard.api.consumer-key=**, this refers to your consumer key. Copy it from "Keys" section on your project page in [Mastercard Developers](https://developer.mastercard.com/dashboard)
      
    >**mastercard.api.keystore-alias=keyalias**, this is the default value of key alias. If it is modified, use the updated one from keys section in [Mastercard Developers](https://developer.mastercard.com/dashboard).
    
    >**mastercard.api.keystore-password=keystorepassword**, this is the default value of key alias. If it is modified, use the updated one from keys section in [Mastercard Developers](https://developer.mastercard.com/dashboard).

    **Below properties will be required to encrypt and decrypt the request and response payloads**
    
    >**mastercard.api.encryption-certificate-file=**, this is the path to certificate (.crt or .pem) file. Add classpath for file, after placing it at src\main\resources in the project folder. Download Encryption Key from "Client Encryption" section on your project page in [Mastercard Developers](https://developer.mastercard.com/dashboard)
    
    >**mastercard.api.encryption-key-fingerprint=**, (optional) this is the encryption key fingerprint, required to encrypt a request. Copy it from "Client Encryption" section on your project page in [Mastercard Developers](https://developer.mastercard.com/dashboard)
    
    >**mastercard.api.decryption-key-file=**, this is your private key (.p12) file, required to decrypt a request. Add classpath for this file, after placing it at src\main\resources in the project folder.
    
    >**mastercard.api.decryption-key-alias=**, this is required to load your decryption private key.

    >**mastercard.api.decryption-keystore-password=**, this is required to load your decryption private key. 

### Integrating with OpenAPI Generator <a name="integrating-with-openapi-generator"></a>
[OpenAPI Generator](https://github.com/OpenAPITools/openapi-generator) generates API client libraries from [OpenAPI Specs](https://github.com/OAI/OpenAPI-Specification). 
It provides generators and library templates for supporting multiple languages and frameworks.

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
                <inputSpec>${project.basedir}/src/main/resources/Loyalty_User_Management-api-spec.yaml</inputSpec>
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

#### Generating The API Client Sources
Now that you have all the dependencies you need, you can generate the sources. To do this, use one of the following two methods:

`Using IDE`
* **Method 1**<br/>
  In IntelliJ IDEA, open the Maven window **(View > Tool Windows > Maven)**. Click the icons `Reimport All Maven Projects` and `Generate Sources and Update Folders for All Projects`

* **Method 2**<br/>
  In the same menu, navigate to the commands **({Project name} > Lifecycle)**, select `clean` and `compile` then click the icon `Run Maven Build`. 

`Using Terminal`
* Navigate to the root directory of the project within a terminal window and execute `mvn clean compile` command.

### Build and Execute <a name="build-and-execute"></a>
Once you’ve added the correct properties, we can build the application. We can do this by navigating to the project’s base directory from the terminal and running the following command

`mvn clean install`

When the project builds successfully you can then run the following command to start the project

`java -jar target/loyalty-user-management-reference-1.0.0.jar`

## Use Cases <a name="use-cases"></a>
### User
> Case 1: [USER ENROLLMENT](https://developer.mastercard.com/loyalty-user-management/documentation/use-cases/user-enrollment/)
  - User’s demographic details enrollment into Mastercard Rewards platform.
  - Refer to model classes for field level information.

    | URL | Method | Request | Response |
    | :-- | :----- | :------ | :------- |
    | `/users` | POST | [UserEnrollRequest](docs/UserEnrollRequest.md) | [UserEnrollResponse](docs/UserEnrollResponse.md) |
  
> Case 2: [USER WITH ACCOUNT ENROLLMENT](https://developer.mastercard.com/loyalty-user-management/documentation/use-cases/user-account-enrollment/)
  - User’s demographic with Account details enrollment into Mastercard Rewards platform.
  - Refer to model classes for field level information.
  
    | URL | Method | Request | Response |
    | :-- | :----- | :------ | :------- |
    | `/users` | POST | [UserEnrollRequest](docs/UserEnrollRequest.md) | [UserEnrollResponse](docs/UserEnrollResponse.md) |
    
> Case 3: [USER SEARCH](https://developer.mastercard.com/loyalty-user-management/documentation/use-cases/user-search/)
  - Retrieves a User’s details from Mastercard Rewards platform based on search criteria.
  - Refer to model classes for field level information.
    
    | URL | Method | Request | Response |
    | :-- | :----- | :------ | :------- |
    | `/users/searches` | POST | [UserSearchRequest](docs/UserSearchRequest.md) | [PagedUserSearchResponse](docs/PagedUserSearchResponse.md) |

> Case 4: [USER SEARCH BY ID](https://developer.mastercard.com/loyalty-user-management/documentation/use-cases/user-search/)
  - Retrieves a User’s details from Mastercard Rewards platform by Mastercard generated unique Id.
  - Refer to model classes for field level information.
    
    | URL | Method | Request | Response |
    | :-- | :----- | :------ | :------- |
    | `/users/{id}` | GET | - | [UserSearchResponse](docs/UserSearchResponse.md) |
    
> Case 5: [USER UPDATE](https://developer.mastercard.com/loyalty-user-management/documentation/use-cases/user-enrollment/)
  - Updates User’s demographic details into Mastercard Rewards platform.
  - Refer to model classes for field level information.

    | URL | Method | Request | Response |
    | :-- | :----- | :------ | :------- |
    | `/users/{id}` | PUT | [UserUpdateRequest](docs/UserUpdateRequest.md) | [UserUpdateResponse](docs/UserUpdateResponse.md) |

### Account          
> Case 1: [ACCOUNT ENROLLMENT](https://developer.mastercard.com/loyalty-user-management/documentation/use-cases/account-enrollment/)
  - User’s Account details enrollment into Mastercard Rewards platform.
  - Refer to model classes for field level information.
      
    | URL | Method | Request | Response |
    | :-- | :----- | :------ | :------- |
    | `/accounts` | POST | [AccountEnrollRequest](docs/AccountEnrollRequest.md) | [AccountResponse](docs/AccountResponse.md) |
      
> Case 2: [ACCOUNT SEARCH](https://developer.mastercard.com/loyalty-user-management/documentation/use-cases/account-search/)
  - Retrieves a User’s account details from Mastercard Rewards platform based on search criteria.
  - Refer to model classes for field level information.
        
    | URL | Method | Request | Response |
    | :-- | :----- | :------ | :------- |
    | `/accounts/searches` | POST | [AccountSearchRequest](docs/AccountSearchRequest.md) | [PagedAccountSearchResponse](docs/PagedAccountSearchResponse.md) |

> Case 3: [ACCOUNT SEARCH BY ID](https://developer.mastercard.com/loyalty-user-management/documentation/use-cases/account-search/)
  - Retrieves a User’s account details from Mastercard Rewards platform by Mastercard generated unique Id.
  - Refer to model classes for field level information.
        
    | URL | Method | Request | Response |
    | :-- | :----- | :------ | :------- |
    | `/accounts/{id}` | GET | - | [AccountSearchResponse](docs/AccountSearchResponse.md) |

> Case 4: [ACCOUNT STATUS UPDATE](https://developer.mastercard.com/loyalty-user-management/documentation/use-cases/account-enrollment/)
  - Updates User's account status into Mastercard Rewards Platform
  - Refer to model classes for field level information.
        
    | URL | Method | Request | Response |
    | :-- | :----- | :------ | :------- |
    | `/accounts/{id}` | PUT | [AccountUpdateRequest](docs/AccountUpdateRequest.md) | [AccountResponse](docs/AccountResponse.md) |

> Case 5: [ERROR HANDLING](https://developer.mastercard.com/loyalty-user-management/documentation/api-reference/error-responses/)
  - The operation can fail for various reasons like formatting, field length exceeds, etc.
  - This use case just shows one of the example of such failures.
  - For the complete list of application specific error codes, refer to [Application Error Codes](https://developer.mastercard.com/loyalty-user-management/documentation/api-reference/application-error-codes/).
  - Also refer to model class [Errors](docs/Errors.md) for field level information.    
    
## API Reference <a name="api-reference"></a>
To develop a client application that consumes a RESTful Loyalty User Management API with Spring Boot, refer below documentation.

| API | Endpoint | HTTP Method | Encrypted Request | Encrypted Response | Description |
| :-- | :------- | :---------- | :---------------- | :----------------- | :---------- |
| [User Enrollment](https://developer.mastercard.com/loyalty-user-managemennt/documentation/api-reference/specification/#apis) | `/users` | POST | YES | NO | User's demographic details enrollment into Mastercard Rewards platform. |
| [User With Account Enrollment](https://developer.mastercard.com/loyalty-user-managemennt/documentation/api-reference/specification/#apis) | `/users` | POST | YES | NO | User's demographic with Account details enrollment into Mastercard Rewards platform. |
| [User Search By ID](https://developer.mastercard.com/loyalty-user-managemennt/documentation/api-reference/specification/#apis) | `/users/{id}` | GET | NO | YES | Retrieves a User’s details by Mastercard generated unique Id. |
| [User Search](https://developer.mastercard.com/loyalty-user-managemennt/documentation/api-reference/specification/#apis) | `/users/searches` | POST | YES | YES | Retrieves a User’s details based on search criteria. |
| [User Update](https://developer.mastercard.com/loyalty-user-managemennt/documentation/api-reference/specification/#apis) | `/users/{id}` | PUT | YES | NO | Updates existing User’s demographic details. |
| [Account Enrollment](https://developer.mastercard.com/loyalty-user-managemennt/documentation/api-reference/specification/#apis) | `/accounts` | POST | YES | NO | User’s account details enrollment into Mastercard Rewards platform. |
| [Account Search By ID](https://developer.mastercard.com/loyalty-user-managemennt/documentation/api-reference/specification/#apis) | `/accounts/{id}` | GET | NO | YES | Retrieves a User’s account details by Mastercard generated unique Id. |
| [Account Search](https://developer.mastercard.com/loyalty-user-managemennt/documentation/api-reference/specification/#apis) | `/accounts/searches` | POST | YES | YES | Retrieves a User’s account details based on search criteria. |
| [Account Update](https://developer.mastercard.com/loyalty-user-managemennt/documentation/api-reference/specification/#apis) | `/accounts/{id}` | PUT | YES | NO | Updates existing User’s account status. |   

### Authorization <a name="authorization"></a>
The `com.mastercard.developer.interceptors` package will provide you with some request interceptor classes you can use when configuring your API client. These classes will take care of adding the correct `Authorization` header before sending the request.

### Encryption and Decryption <a name="encryption-and-decryption"></a>
The `com.mastercard.developer.crypto.interceptors` provides a class that you can use when configuring your API client. This class will take care of encrypting payload before sending the request and decrypting payload after receiving the response.

#### Loading Encryption Certificate <a name="loading-encryption-certificate"></a>

A `Certificate` object can be created from a file by calling `EncryptionUtils.loadEncryptionCertificate`:
```java
Certificate encryptionCertificate = EncryptionUtils.loadEncryptionCertificate("<insert certificate file path>");
```

Supported certificate formats: PEM, DER.

#### Loading Decryption Key <a name="loading-decryption-key"></a>

##### From a PKCS#12 Key Store

A `PrivateKey` object can be created from a PKCS#12 key store by calling `EncryptionUtils.loadDecryptionKey` the following way:
```java
PrivateKey decryptionKey = EncryptionUtils.loadDecryptionKey(
                                    "<insert PKCS#12 key file path>", 
                                    "<insert key alias>", 
                                    "<insert key password>");
```

#### Configuring JWE Instance <a name="configuring-jwe-instance"></a>
Use the `JweConfigBuilder` to create `JweConfig` instances. Example:
```java
JweConfig jweConfig = JweConfigBuilder.builder()
    .withEncryptionCertificate(encryptionCertificate)
    .withEncryptionKeyFingerprint(encryptionKeyFingerprint)
    .withDecryptionKey(decryptionKey)
    .build();
```

#### Encrypting Entire Payloads <a name="encrypting-entire-payloads"></a>

Example using the configuration [above](#configuring-jwe-instance):
```java
String payload = "{" +
    "    \"sensitiveField1\": \"sensitiveValue1\"," +
    "    \"sensitiveField2\": \"sensitiveValue2\"" +
    "}";
```

Output:
```json
{
    "encryptedPayload": "eyJhbGciOiJSU0EtT0(...)IsImVuYyI6IkEyNTifQ.OKOawDo13gRp2ojaHV7LFpZcg(...)VZKTyKOMTYUmKoTCVJRgckCL9kiMT03JGe.48V1_ALb6US04U3b.5eym8TW_c8SuK0ltJ3rpYI(...)7TALvtu6UG9oMo4vpzs9tX_EFShS8iB7j6ji.XFBoMYUZodetZdvTiFvSkQ"
}
```

#### Decrypting Entire Payloads <a name="decrypting-entire-payloads"></a>

Example using the configuration [above](#configuring-jwe-instance):
```java
String encryptedPayload = "{" +
    "  \"encryptedPayload\": \"eyJhbGciOiJSU0EtT0F(...)BiYzQyTIyNTQ1ODgzNSJ9.VkO7N6gAptqoD7IQaK(...)ptYySP_TuvERby89DY1EezAm3A.qj6ISyzq1ASDJKD0.ENF7bUfBkoWAEYvk(...)o9JGMctx-PSdeVqwCQAVRNj0pYs1WjOp4UDbE4eEZIF6YA.Wc7ARH7R6sikpKzxET3MYA\" +
    "}";
```

Output:
```json
{
    "sensitiveField1": "sensitiveValue1",
    "sensitiveField2": "sensitiveValue2"
}
```

### Request Examples <a name="request-examples"></a>
You can change the default input passed to APIs, modify values in following files,
* `com.mastercard.developer.example.UserExample.java`
* `com.mastercard.developer.example.AccountExample.java`

### Recommendation <a name="recommendation"></a>
It is recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issues.

## Support <a name="support"></a>
If you would like further information, please send an email to apisupport@mastercard.com

## License <a name="license"></a>
Copyright 2020 Mastercard
 
Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
