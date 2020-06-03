package bindings.commons.automation.customer;

import com.ipfdigital.automation.api.customer.CustomerAddressGenerator;
import com.ipfdigital.automation.api.customer.CustomerBankAccountGenerator;
import com.ipfdigital.automation.api.customer.DebitCardGenerator;
import com.ipfdigital.automation.api.customer.MsisdnGenerator;
import com.ipfdigital.automation.api.customer.NameGenerator;
import com.ipfdigital.automation.api.customer.SsnGenerator;

final class MXCustomerGeneratorStrategy extends GenericCustomerGeneratorStrategy {

  private final DebitCardGenerator debitCardGenerator;

  public MXCustomerGeneratorStrategy(final CustomerBankAccountGenerator bankAccountGenerator,
      final CustomerAddressGenerator customerAddressGeneratorService,
      final MsisdnGenerator msisdnGeneratorService,
      final NameGenerator nameGeneratorService,
      final SsnGenerator ssnGeneratorService, final DebitCardGenerator debitCardGenerator) {
    super(bankAccountGenerator, customerAddressGeneratorService, msisdnGeneratorService,
        nameGeneratorService,
        ssnGeneratorService);
    this.debitCardGenerator = debitCardGenerator;
  }

  @Override
  public boolean accept(final Country country) {
    return Country.MX == country;
  }

  @Override
  public GeneratedCustomerDTO generate(final GenerateCustomerDTO dto) {
    final GenerateCustomerDTOMX generateCustomerDTOMX = new GenerateCustomerDTOMX();
    final GenerateCustomerDTO customerDTO = generateCustomerDTOMX.customizeForMX(dto);
    final GeneratedCustomerDTO generatedCustomerDTO = super.generate(customerDTO);
    generatedCustomerDTO.setDebitCardNumber(debitCardGenerator.generate());
    return generatedCustomerDTO;
  }
}
