package bindings.commons.automation.aio.rest.dto;

public class UpdateCustomerMX {

  private CustomerDTO customer;
  private String taxIdentificationNumber;
  private CommunicationSettingsDTO communicationSettingsMX;
  private EmailUpdateDTO email;
  private PasswordUpdateDTO password;
  private AddressDTO address;

  public UpdateCustomerMX withCustomer(final CustomerDTO customer) {
    this.customer = customer;
    return this;
  }

  public UpdateCustomerMX withTaxIdentificationNumber(final String taxIdentificationNumber) {
    this.taxIdentificationNumber = taxIdentificationNumber;
    return this;
  }

  public UpdateCustomerMX withCommunicationSettings(
      final CommunicationSettingsDTO communicationSettingsMX) {
    this.communicationSettingsMX = communicationSettingsMX;
    return this;
  }

  public UpdateCustomerMX withEmail(final EmailUpdateDTO email) {
    this.email = email;
    return this;
  }

  public UpdateCustomerMX withPassword(final PasswordUpdateDTO password) {
    this.password = password;
    return this;
  }

  public UpdateCustomerMX withAddress(final AddressDTO address) {
    this.address = address;
    return this;
  }

  public CustomerDTO getCustomer() {
    return this.customer;
  }

  public void setCustomer(final CustomerDTO customer) {
    this.customer = customer;
  }

  public String getTaxIdentificationNumber() {
    return this.taxIdentificationNumber;
  }

  public void setTaxIdentificationNumber(final String taxIdentificationNumber) {
    this.taxIdentificationNumber = taxIdentificationNumber;
  }

  public CommunicationSettingsDTO getCommunicationSettingsMX() {
    return this.communicationSettingsMX;
  }

  public void setCommunicationSettingsMX(final CommunicationSettingsDTO communicationSettingsMX) {
    this.communicationSettingsMX = communicationSettingsMX;
  }

  public EmailUpdateDTO getEmail() {
    return this.email;
  }

  public void setEmail(final EmailUpdateDTO email) {
    this.email = email;
  }

  public PasswordUpdateDTO getPassword() {
    return this.password;
  }

  public void setPassword(final PasswordUpdateDTO password) {
    this.password = password;
  }

  public AddressDTO getAddress() {
    return this.address;
  }

  public void setAddress(final AddressDTO address) {
    this.address = address;
  }
}
