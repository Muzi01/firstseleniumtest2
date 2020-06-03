package bindings.commons.automation.aio.rest.v2.customer;

import com.ipfdigital.automation.aio.rest.dto.AddressDTO;
import com.ipfdigital.automation.aio.rest.dto.BankAccountUpdateDTO;
import com.ipfdigital.automation.aio.rest.dto.CommunicationSettingsDTO;
import com.ipfdigital.automation.aio.rest.dto.PasswordUpdateDTO;
import feign.RequestLine;

public interface PLCustomerClient extends GenericCustomerClient {
  @Override
  @RequestLine("PUT /customer-pl/bankaccount")
  void updateBankAccount(BankAccountUpdateDTO bankAccountUpdate);

  @Override
  @RequestLine("PUT /customer-pl/communication-settings-pl")
  void updateCommunicationSettings(CommunicationSettingsDTO communicationSettings);

  @Override
  @RequestLine("PUT /customer-pl/address-step2")
  void updateAddress(AddressDTO addressDTO);

  @RequestLine("PUT /customer-pl/password")
  void updatePassword(PasswordUpdateDTO password);
}
