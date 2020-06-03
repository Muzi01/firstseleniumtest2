package bindings.commons.automation.aio.rest.dto;

public class UpdateCustomerDTO {
  public CustomerDTO customer;
  public CommunicationSettingsDTO communicationSettings;
  public EmailUpdateDTO email;
  public PasswordUpdateDTO password;
  public AddressDTO address;
  public String taxIdentificationNumber;
}
