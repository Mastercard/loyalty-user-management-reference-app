/*
 * User Account Management
 * API interface to enroll users and accounts
 *
 * The version of the OpenAPI document: 1.0.0
 * Contact: Loyalty_Technology_Solutions_Scrum_Vulcans@mastercard.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package com.mastercard.developer.mastercard_loyalty_enrollment_client.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * AccountSearchRequest
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2020-02-11T17:56:47.857+05:30[Asia/Kolkata]")
public class AccountSearchRequest {
  public static final String SERIALIZED_NAME_COMPANY_ID = "companyId";
  @SerializedName(SERIALIZED_NAME_COMPANY_ID)
  private String companyId;

  public static final String SERIALIZED_NAME_ACCOUNT_ID_TYPE = "accountIdType";
  @SerializedName(SERIALIZED_NAME_ACCOUNT_ID_TYPE)
  private String accountIdType;

  public static final String SERIALIZED_NAME_ACCOUNT_ID = "accountId";
  @SerializedName(SERIALIZED_NAME_ACCOUNT_ID)
  private String accountId;

  public static final String SERIALIZED_NAME_PROGRAM_IDENTIFIER = "programIdentifier";
  @SerializedName(SERIALIZED_NAME_PROGRAM_IDENTIFIER)
  private String programIdentifier;


  public AccountSearchRequest companyId(String companyId) {
    
    this.companyId = companyId;
    return this;
  }

   /**
   * Interbank Card Association Number assigned by Mastercard to the Issuer.
   * @return companyId
  **/
  @ApiModelProperty(example = "611532", required = true, value = "Interbank Card Association Number assigned by Mastercard to the Issuer.")

  public String getCompanyId() {
    return companyId;
  }


  public void setCompanyId(String companyId) {
    this.companyId = companyId;
  }


  public AccountSearchRequest accountIdType(String accountIdType) {
    
    this.accountIdType = accountIdType;
    return this;
  }

   /**
   * Account id Type assigned by the Issuer.
   * @return accountIdType
  **/
  @ApiModelProperty(example = "ACCOUNT_NUMBER", required = true, value = "Account id Type assigned by the Issuer.")

  public String getAccountIdType() {
    return accountIdType;
  }


  public void setAccountIdType(String accountIdType) {
    this.accountIdType = accountIdType;
  }


  public AccountSearchRequest accountId(String accountId) {
    
    this.accountId = accountId;
    return this;
  }

   /**
   * Account id assigned by the Issuer.
   * @return accountId
  **/
  @ApiModelProperty(example = "5330333671236516", required = true, value = "Account id assigned by the Issuer.")

  public String getAccountId() {
    return accountId;
  }


  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }


  public AccountSearchRequest programIdentifier(String programIdentifier) {
    
    this.programIdentifier = programIdentifier;
    return this;
  }

   /**
   * Program identifier assigned by the Issuer.
   * @return programIdentifier
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "ZXSzM", value = "Program identifier assigned by the Issuer.")

  public String getProgramIdentifier() {
    return programIdentifier;
  }


  public void setProgramIdentifier(String programIdentifier) {
    this.programIdentifier = programIdentifier;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountSearchRequest accountSearchRequest = (AccountSearchRequest) o;
    return Objects.equals(this.companyId, accountSearchRequest.companyId) &&
        Objects.equals(this.accountIdType, accountSearchRequest.accountIdType) &&
        Objects.equals(this.accountId, accountSearchRequest.accountId) &&
        Objects.equals(this.programIdentifier, accountSearchRequest.programIdentifier);
  }

  @Override
  public int hashCode() {
    return Objects.hash(companyId, accountIdType, accountId, programIdentifier);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountSearchRequest {\n");
    sb.append("    companyId: ").append(toIndentedString(companyId)).append("\n");
    sb.append("    accountIdType: ").append(toIndentedString(accountIdType)).append("\n");
    sb.append("    accountId: ").append(toIndentedString(accountId)).append("\n");
    sb.append("    programIdentifier: ").append(toIndentedString(programIdentifier)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
