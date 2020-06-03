package bindings.commons.automation.aio.rest.dto;

public class UpdateCustomerES {

  private CustomerDTO customer;
  private CommunicationSettingsDTO communicationSettings;
  private EmailUpdateDTO email;
  private PasswordUpdateDTO password;
  private AddressDTO address;

  public UpdateCustomerES withCustomer(final CustomerDTO customer) {
    this.customer = customer;
    return this;
  }

  public UpdateCustomerES withCommunicationSettings(
      final CommunicationSettingsDTO communicationSettings) {
    this.communicationSettings = communicationSettings;
    return this;
  }

  public UpdateCustomerES withEmail(final EmailUpdateDTO email) {
    this.email = email;
    return this;
  }

  public UpdateCustomerES withPassword(final PasswordUpdateDTO password) {
    this.password = password;
    return this;
  }

  public UpdateCustomerES withAddress(final AddressDTO address) {
    this.address = address;
    return this;
  }

  public CustomerDTO getCustomer() {
    return this.customer;
  }

  public void setCustomer(final CustomerDTO customer) {
    this.customer = customer;
  }

  public CommunicationSettingsDTO getCommunicationSettings() {
    return this.communicationSettings;
  }

  public void setCommunicationSettings(final CommunicationSettingsDTO communicationSettings) {
    this.communicationSettings = communicationSettings;
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
