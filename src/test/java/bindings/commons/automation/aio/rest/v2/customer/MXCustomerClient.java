package bindings.commons.automation.aio.rest.v2.customer;

import com.ipfdigital.automation.aio.rest.dto.BankAccountUpdateDTO;
import com.ipfdigital.automation.aio.rest.dto.PaymentCardDTO;
import com.ipfdigital.automation.aio.rest.dto.UpdateCustomerDTO;
import feign.RequestLine;

public interface MXCustomerClient extends GenericCustomerClient {
  @Override
  @RequestLine("PUT /customer-mx/bankaccount")
  void updateBankAccount(BankAccountUpdateDTO bankAccountUpdate);

  @RequestLine("PUT /customer-mx/info-mx")
  void updateCustomer(UpdateCustomerDTO updateCustomerDTO);

  @RequestLine("PUT /customer-mx/payment-card")
  void updatePaymentCard(PaymentCardDTO paymentCard);
}
