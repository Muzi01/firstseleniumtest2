package bindings.commons.automation.generator.model.aio;

import com.ipfdigital.automation.aio.rest.dto.ProductDTO;
import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.generator.portals.Brand;
import com.ipfdigital.automation.generator.utils.Utils;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Date;
import java.sql.Timestamp;

public class Customer {
  public Long id;
  public Long firstDrawAmount;
  public String city;
  public Country country;
  public Brand brand;
  public String postcode;
  public String street;
  public String door;
  public String bankAccount;
  public String identifier;
  public String email;
  public String msisdn;
  public String msisdn2;
  public String password;
  public String firstName;
  public String secondFirstName;
  public String lastName;
  public String secondLastName;
  public String personalDocumentNumber;
  public String authBank;
  public ProductDTO product;
  public String debitCardNumber;
  public Long entityVersion;
  public String addressCountry;
  public String province;
  public String countryOfBirth;
  public String floor;
  public boolean manualAddress;
  public String shortBankAccount;
  public boolean bankAccountVerified;
  public String contractDeliveryMethod;
  public String invoiceDeliveryMethod;
  public boolean marketingPermission;
  public boolean marketingPermissionPaper;
  public boolean marketingPermissionPhone;
  public boolean consentForAgentContact;
  public boolean marketingPermissionEmailOrSms;
  public String legacyCustomerNumber;
  public String referenceNumber;
  public Timestamp updated;
  public boolean disableLogin;
  public boolean onHold;
  public boolean disableDraw;
  public boolean disableSms;
  public String gender;
  public String preferredLanguage;
  public boolean preDueDateReminderSMS;
  public String deviceToken;
  public String pin;
  public boolean registered;
  public boolean tempIdentifier;
  public Date dateOfBirth;
  public String taxIdentificationNumber;
  public String countryStateOfBirth;
  public String facebookToken;
  public Timestamp created;
  public boolean thirdPartiesMarketingRejection;
  public String fraudAMLRisk;
  public String fraudAMLRiskReason;
  public String identifierType;
  public String residentialStatus;
  public String onHoldReason;
  public boolean suspendAnonymization;
  public String directDebitCustomerReference;
  public String uuid;
  public String statementOfAccountDeliveryMethod;
  public boolean disableLateFee;
  public String cityOfBirth;
  public boolean employee;
  public String referrerChannel;

  @Override
  public String toString() {
    return Utils.toStringWithoutNullValues(this, ToStringStyle.JSON_STYLE);
  }
}
