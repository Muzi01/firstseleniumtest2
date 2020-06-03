
package bindings.commons.automation.mule.model.mule.development.customer.sfdc.channel;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude (JsonInclude.Include.NON_NULL)
@JsonPropertyOrder ({
    "customerId",
    "countryCode",
    "dateOfBirth",
    "email",
    "password",
    "mobilePhone",
    "landlinePhone",
    "firstName",
    "lastName",
    "secondFirstName",
    "secondLastName",
    "bankAccountVerified",
    "debitCardNumber",
    "debitCardBankName",
    "bankAccount",
    "invoiceDeliveryMethod",
    "contractDeliveryMethod",
    "marketingPermission",
    "preDueDateReminderSMS",
    "marketingPermissionPaper",
    "marketingPermissionPhone",
    "marketingPermissionEmailSms",
    "deltaVistaProcessingPermission",
    "taxIdentificationNumber",
    "nationality",
    "countryOfBirth",
    "timeAtBank",
    "idCardNumber",
    "disableLogin",
    "onHold",
    "disableDraw",
    "disableSms",
    "pepStatus",
    "amlRiskGroup",
    "amlRiskReason",
    "customerAddress",
    "correspondenceAddress"
})
public class UpdateCustomerRequestExample {

  @JsonProperty ("customerId")
  private String customerId;
  @JsonProperty ("countryCode")
  private String countryCode;
  @JsonProperty ("dateOfBirth")
  private String dateOfBirth;
  @JsonProperty ("email")
  private String email;
  @JsonProperty ("password")
  private String password;
  @JsonProperty ("mobilePhone")
  private String mobilePhone;
  @JsonProperty ("landlinePhone")
  private String landlinePhone;
  @JsonProperty ("firstName")
  private String firstName;
  @JsonProperty ("lastName")
  private String lastName;
  @JsonProperty ("secondFirstName")
  private String secondFirstName;
  @JsonProperty ("secondLastName")
  private String secondLastName;
  @JsonProperty ("bankAccountVerified")
  private String bankAccountVerified;
  @JsonProperty ("debitCardNumber")
  private String debitCardNumber;
  @JsonProperty ("debitCardBankName")
  private String debitCardBankName;
  @JsonProperty ("bankAccount")
  private String bankAccount;
  @JsonProperty ("invoiceDeliveryMethod")
  private String invoiceDeliveryMethod;
  @JsonProperty ("contractDeliveryMethod")
  private String contractDeliveryMethod;
  @JsonProperty ("marketingPermission")
  private String marketingPermission;
  @JsonProperty ("preDueDateReminderSMS")
  private String preDueDateReminderSMS;
  @JsonProperty ("marketingPermissionPaper")
  private String marketingPermissionPaper;
  @JsonProperty ("marketingPermissionPhone")
  private String marketingPermissionPhone;
  @JsonProperty ("marketingPermissionEmailSms")
  private String marketingPermissionEmailSms;
  @JsonProperty ("deltaVistaProcessingPermission")
  private String deltaVistaProcessingPermission;
  @JsonProperty ("taxIdentificationNumber")
  private String taxIdentificationNumber;
  @JsonProperty ("nationality")
  private String nationality;
  @JsonProperty ("countryOfBirth")
  private String countryOfBirth;
  @JsonProperty ("timeAtBank")
  private String timeAtBank;
  @JsonProperty ("idCardNumber")
  private String idCardNumber;
  @JsonProperty ("disableLogin")
  private String disableLogin;
  @JsonProperty ("onHold")
  private String onHold;
  @JsonProperty ("disableDraw")
  private String disableDraw;
  @JsonProperty ("disableSms")
  private String disableSms;
  @JsonProperty ("pepStatus")
  private String pepStatus;
  @JsonProperty ("amlRiskGroup")
  private String amlRiskGroup;
  @JsonProperty ("amlRiskReason")
  private String amlRiskReason;
  @JsonProperty ("customerAddress")
  private CustomerAddress customerAddress;
  @JsonProperty ("correspondenceAddress")
  private CorrespondenceAddress correspondenceAddress;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("customerId")
  public String getCustomerId() {
    return this.customerId;
  }

  @JsonProperty ("customerId")
  public void setCustomerId(final String customerId) {
    this.customerId = customerId;
  }

  @JsonProperty ("countryCode")
  public String getCountryCode() {
    return this.countryCode;
  }

  @JsonProperty ("countryCode")
  public void setCountryCode(final String countryCode) {
    this.countryCode = countryCode;
  }

  @JsonProperty ("dateOfBirth")
  public String getDateOfBirth() {
    return this.dateOfBirth;
  }

  @JsonProperty ("dateOfBirth")
  public void setDateOfBirth(final String dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  @JsonProperty ("email")
  public String getEmail() {
    return this.email;
  }

  @JsonProperty ("email")
  public void setEmail(final String email) {
    this.email = email;
  }

  @JsonProperty ("password")
  public String getPassword() {
    return this.password;
  }

  @JsonProperty ("password")
  public void setPassword(final String password) {
    this.password = password;
  }

  @JsonProperty ("mobilePhone")
  public String getMobilePhone() {
    return this.mobilePhone;
  }

  @JsonProperty ("mobilePhone")
  public void setMobilePhone(final String mobilePhone) {
    this.mobilePhone = mobilePhone;
  }

  @JsonProperty ("landlinePhone")
  public String getLandlinePhone() {
    return this.landlinePhone;
  }

  @JsonProperty ("landlinePhone")
  public void setLandlinePhone(final String landlinePhone) {
    this.landlinePhone = landlinePhone;
  }

  @JsonProperty ("firstName")
  public String getFirstName() {
    return this.firstName;
  }

  @JsonProperty ("firstName")
  public void setFirstName(final String firstName) {
    this.firstName = firstName;
  }

  @JsonProperty ("lastName")
  public String getLastName() {
    return this.lastName;
  }

  @JsonProperty ("lastName")
  public void setLastName(final String lastName) {
    this.lastName = lastName;
  }

  @JsonProperty ("secondFirstName")
  public String getSecondFirstName() {
    return this.secondFirstName;
  }

  @JsonProperty ("secondFirstName")
  public void setSecondFirstName(final String secondFirstName) {
    this.secondFirstName = secondFirstName;
  }

  @JsonProperty ("secondLastName")
  public String getSecondLastName() {
    return this.secondLastName;
  }

  @JsonProperty ("secondLastName")
  public void setSecondLastName(final String secondLastName) {
    this.secondLastName = secondLastName;
  }

  @JsonProperty ("bankAccountVerified")
  public String getBankAccountVerified() {
    return this.bankAccountVerified;
  }

  @JsonProperty ("bankAccountVerified")
  public void setBankAccountVerified(final String bankAccountVerified) {
    this.bankAccountVerified = bankAccountVerified;
  }

  @JsonProperty ("debitCardNumber")
  public String getDebitCardNumber() {
    return this.debitCardNumber;
  }

  @JsonProperty ("debitCardNumber")
  public void setDebitCardNumber(final String debitCardNumber) {
    this.debitCardNumber = debitCardNumber;
  }

  @JsonProperty ("debitCardBankName")
  public String getDebitCardBankName() {
    return this.debitCardBankName;
  }

  @JsonProperty ("debitCardBankName")
  public void setDebitCardBankName(final String debitCardBankName) {
    this.debitCardBankName = debitCardBankName;
  }

  @JsonProperty ("bankAccount")
  public String getBankAccount() {
    return this.bankAccount;
  }

  @JsonProperty ("bankAccount")
  public void setBankAccount(final String bankAccount) {
    this.bankAccount = bankAccount;
  }

  @JsonProperty ("invoiceDeliveryMethod")
  public String getInvoiceDeliveryMethod() {
    return this.invoiceDeliveryMethod;
  }

  @JsonProperty ("invoiceDeliveryMethod")
  public void setInvoiceDeliveryMethod(final String invoiceDeliveryMethod) {
    this.invoiceDeliveryMethod = invoiceDeliveryMethod;
  }

  @JsonProperty ("contractDeliveryMethod")
  public String getContractDeliveryMethod() {
    return this.contractDeliveryMethod;
  }

  @JsonProperty ("contractDeliveryMethod")
  public void setContractDeliveryMethod(final String contractDeliveryMethod) {
    this.contractDeliveryMethod = contractDeliveryMethod;
  }

  @JsonProperty ("marketingPermission")
  public String getMarketingPermission() {
    return this.marketingPermission;
  }

  @JsonProperty ("marketingPermission")
  public void setMarketingPermission(final String marketingPermission) {
    this.marketingPermission = marketingPermission;
  }

  @JsonProperty ("preDueDateReminderSMS")
  public String getPreDueDateReminderSMS() {
    return this.preDueDateReminderSMS;
  }

  @JsonProperty ("preDueDateReminderSMS")
  public void setPreDueDateReminderSMS(final String preDueDateReminderSMS) {
    this.preDueDateReminderSMS = preDueDateReminderSMS;
  }

  @JsonProperty ("marketingPermissionPaper")
  public String getMarketingPermissionPaper() {
    return this.marketingPermissionPaper;
  }

  @JsonProperty ("marketingPermissionPaper")
  public void setMarketingPermissionPaper(final String marketingPermissionPaper) {
    this.marketingPermissionPaper = marketingPermissionPaper;
  }

  @JsonProperty ("marketingPermissionPhone")
  public String getMarketingPermissionPhone() {
    return this.marketingPermissionPhone;
  }

  @JsonProperty ("marketingPermissionPhone")
  public void setMarketingPermissionPhone(final String marketingPermissionPhone) {
    this.marketingPermissionPhone = marketingPermissionPhone;
  }

  @JsonProperty ("marketingPermissionEmailSms")
  public String getMarketingPermissionEmailSms() {
    return this.marketingPermissionEmailSms;
  }

  @JsonProperty ("marketingPermissionEmailSms")
  public void setMarketingPermissionEmailSms(final String marketingPermissionEmailSms) {
    this.marketingPermissionEmailSms = marketingPermissionEmailSms;
  }

  @JsonProperty ("deltaVistaProcessingPermission")
  public String getDeltaVistaProcessingPermission() {
    return this.deltaVistaProcessingPermission;
  }

  @JsonProperty ("deltaVistaProcessingPermission")
  public void setDeltaVistaProcessingPermission(final String deltaVistaProcessingPermission) {
    this.deltaVistaProcessingPermission = deltaVistaProcessingPermission;
  }

  @JsonProperty ("taxIdentificationNumber")
  public String getTaxIdentificationNumber() {
    return this.taxIdentificationNumber;
  }

  @JsonProperty ("taxIdentificationNumber")
  public void setTaxIdentificationNumber(final String taxIdentificationNumber) {
    this.taxIdentificationNumber = taxIdentificationNumber;
  }

  @JsonProperty ("nationality")
  public String getNationality() {
    return this.nationality;
  }

  @JsonProperty ("nationality")
  public void setNationality(final String nationality) {
    this.nationality = nationality;
  }

  @JsonProperty ("countryOfBirth")
  public String getCountryOfBirth() {
    return this.countryOfBirth;
  }

  @JsonProperty ("countryOfBirth")
  public void setCountryOfBirth(final String countryOfBirth) {
    this.countryOfBirth = countryOfBirth;
  }

  @JsonProperty ("timeAtBank")
  public String getTimeAtBank() {
    return this.timeAtBank;
  }

  @JsonProperty ("timeAtBank")
  public void setTimeAtBank(final String timeAtBank) {
    this.timeAtBank = timeAtBank;
  }

  @JsonProperty ("idCardNumber")
  public String getIdCardNumber() {
    return this.idCardNumber;
  }

  @JsonProperty ("idCardNumber")
  public void setIdCardNumber(final String idCardNumber) {
    this.idCardNumber = idCardNumber;
  }

  @JsonProperty ("disableLogin")
  public String getDisableLogin() {
    return this.disableLogin;
  }

  @JsonProperty ("disableLogin")
  public void setDisableLogin(final String disableLogin) {
    this.disableLogin = disableLogin;
  }

  @JsonProperty ("onHold")
  public String getOnHold() {
    return this.onHold;
  }

  @JsonProperty ("onHold")
  public void setOnHold(final String onHold) {
    this.onHold = onHold;
  }

  @JsonProperty ("disableDraw")
  public String getDisableDraw() {
    return this.disableDraw;
  }

  @JsonProperty ("disableDraw")
  public void setDisableDraw(final String disableDraw) {
    this.disableDraw = disableDraw;
  }

  @JsonProperty ("disableSms")
  public String getDisableSms() {
    return this.disableSms;
  }

  @JsonProperty ("disableSms")
  public void setDisableSms(final String disableSms) {
    this.disableSms = disableSms;
  }

  @JsonProperty ("pepStatus")
  public String getPepStatus() {
    return this.pepStatus;
  }

  @JsonProperty ("pepStatus")
  public void setPepStatus(final String pepStatus) {
    this.pepStatus = pepStatus;
  }

  @JsonProperty ("amlRiskGroup")
  public String getAmlRiskGroup() {
    return this.amlRiskGroup;
  }

  @JsonProperty ("amlRiskGroup")
  public void setAmlRiskGroup(final String amlRiskGroup) {
    this.amlRiskGroup = amlRiskGroup;
  }

  @JsonProperty ("amlRiskReason")
  public String getAmlRiskReason() {
    return this.amlRiskReason;
  }

  @JsonProperty ("amlRiskReason")
  public void setAmlRiskReason(final String amlRiskReason) {
    this.amlRiskReason = amlRiskReason;
  }

  @JsonProperty ("customerAddress")
  public CustomerAddress getCustomerAddress() {
    return this.customerAddress;
  }

  @JsonProperty ("customerAddress")
  public void setCustomerAddress(final CustomerAddress customerAddress) {
    this.customerAddress = customerAddress;
  }

  @JsonProperty ("correspondenceAddress")
  public CorrespondenceAddress getCorrespondenceAddress() {
    return this.correspondenceAddress;
  }

  @JsonProperty ("correspondenceAddress")
  public void setCorrespondenceAddress(final CorrespondenceAddress correspondenceAddress) {
    this.correspondenceAddress = correspondenceAddress;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperty(final String name, final Object value) {
    this.additionalProperties.put(name, value);
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder()
        .append(this.customerId).append(this.countryCode).append(this.dateOfBirth).append(
            this.email)
        .append(
            this.password)
        .append(this.mobilePhone).append(this.landlinePhone).append(this.firstName).append(
            this.lastName)
        .append(this.secondFirstName)
        .append(this.secondLastName).append(this.bankAccountVerified).append(this.debitCardNumber)
        .append(this.debitCardBankName)
        .append(this.bankAccount).append(this.invoiceDeliveryMethod).append(
            this.contractDeliveryMethod)
        .append(this.marketingPermission).append(this.preDueDateReminderSMS).append(
            this.marketingPermissionPaper)
        .append(this.marketingPermissionPhone).append(this.marketingPermissionEmailSms)
        .append(this.deltaVistaProcessingPermission).append(this.taxIdentificationNumber).append(
            this.nationality)
        .append(this.countryOfBirth).append(this.timeAtBank).append(this.idCardNumber).append(
            this.disableLogin)
        .append(this.onHold).append(this.disableDraw).append(this.disableSms).append(this.pepStatus)
        .append(this.amlRiskGroup)
        .append(this.amlRiskReason).append(this.customerAddress).append(this.correspondenceAddress)
        .append(this.additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof UpdateCustomerRequestExample)) {
      return false;
    }
    final UpdateCustomerRequestExample rhs = ((UpdateCustomerRequestExample) other);
    return new EqualsBuilder().append(this.customerId, rhs.customerId)
        .append(this.countryCode, rhs.countryCode).append(this.dateOfBirth, rhs.dateOfBirth)
        .append(this.email, rhs.email).append(this.password, rhs.password)
        .append(this.mobilePhone, rhs.mobilePhone).append(this.landlinePhone, rhs.landlinePhone)
        .append(this.firstName, rhs.firstName).append(this.lastName, rhs.lastName)
        .append(this.secondFirstName, rhs.secondFirstName)
        .append(this.secondLastName, rhs.secondLastName)
        .append(this.bankAccountVerified, rhs.bankAccountVerified)
        .append(this.debitCardNumber, rhs.debitCardNumber)
        .append(this.debitCardBankName, rhs.debitCardBankName)
        .append(this.bankAccount, rhs.bankAccount)
        .append(this.invoiceDeliveryMethod, rhs.invoiceDeliveryMethod)
        .append(this.contractDeliveryMethod, rhs.contractDeliveryMethod)
        .append(this.marketingPermission, rhs.marketingPermission)
        .append(this.preDueDateReminderSMS, rhs.preDueDateReminderSMS)
        .append(this.marketingPermissionPaper, rhs.marketingPermissionPaper)
        .append(this.marketingPermissionPhone, rhs.marketingPermissionPhone)
        .append(this.marketingPermissionEmailSms, rhs.marketingPermissionEmailSms)
        .append(this.deltaVistaProcessingPermission, rhs.deltaVistaProcessingPermission)
        .append(this.taxIdentificationNumber, rhs.taxIdentificationNumber)
        .append(this.nationality, rhs.nationality).append(this.countryOfBirth, rhs.countryOfBirth)
        .append(this.timeAtBank, rhs.timeAtBank).append(this.idCardNumber, rhs.idCardNumber)
        .append(this.disableLogin, rhs.disableLogin).append(this.onHold, rhs.onHold)
        .append(this.disableDraw, rhs.disableDraw).append(this.disableSms, rhs.disableSms)
        .append(this.pepStatus, rhs.pepStatus).append(this.amlRiskGroup, rhs.amlRiskGroup)
        .append(this.amlRiskReason, rhs.amlRiskReason)
        .append(this.customerAddress, rhs.customerAddress)
        .append(this.correspondenceAddress, rhs.correspondenceAddress)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
