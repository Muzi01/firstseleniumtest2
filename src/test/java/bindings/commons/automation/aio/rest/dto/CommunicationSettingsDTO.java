package bindings.commons.automation.aio.rest.dto;

public class CommunicationSettingsDTO {

  /**
   * Based on AIO
   */
  public enum DeliveryMethod {
    EMAIL,
    PAPER,
    EMAIL_AND_PAPER,
    ONLINE,
    E_INVOICE
  }

  public DeliveryMethod invoiceDeliveryMethod;
  public DeliveryMethod contractDeliveryMethod;
  public Boolean preDueDateReminderSMS;
  public Boolean marketingPermission;
  public Boolean marketingPermissionEmailOrSms;
  public Boolean marketingPermissionPaper;
  public Boolean marketingPermissionPhone;
  public Boolean thirdPartiesMarketingRejection;
  public Boolean powerOfAttorney;
  public Boolean termsOfServiceViaWebsite;
  public String customerFlowStep;
}
