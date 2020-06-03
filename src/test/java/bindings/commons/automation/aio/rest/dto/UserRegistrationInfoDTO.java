package bindings.commons.automation.aio.rest.dto;

import com.ipfdigital.automation.aio.rest.dto.au.ConsentsDTO;
import com.ipfdigital.automation.customer.Gender;

public class UserRegistrationInfoDTO {
  public String ssn;
  public String msisdn;
  public String msisdn2;
  public String firstName;
  public String lastName;
  public String email;
  public String otp;
  public String password;
  public Gender gender;

  // LT
  public String bankName;
  public Boolean consent;
  public Boolean dataProcessingConsent;
  public Boolean politicallyExposedPerson;
  public String bankReturnUrl;

  // AU
  public Boolean instantMessenger;
  public ConsentsDTO consents;
  public int[] dateOfBirth;
  public String productTypeOnCreation;
}
