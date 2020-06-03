package bindings.commons.automation.generator.model.aio;

import java.sql.Date;
import java.sql.Timestamp;

public class UserRegistrationRequest {
  public Long id;
  public Long entityVersion;
  public Date closed;
  public Date created;
  public String state;
  public Date lastSmsSent;
  public String msisdn;
  public String otp;
  public String country;
  public String identificationMD5;
  public String identificationRejectReason;
  public String identificationStatus;
  public String identifier;
  public String reminderSMSState;
  public Long customerId;
  public String requestType;
  public String previousIdentificationStatus;
  public String bank;
  public String email;
  public String password;
  public String brand;
  public String bankReturnUrl;
  public String origin;
  public int otpSentCounter;
  public int otpVerifiedCounter;
  public Timestamp updated;
  public Long creditApplicationId;
}
