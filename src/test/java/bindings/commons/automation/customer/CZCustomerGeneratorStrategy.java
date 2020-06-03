package bindings.commons.automation.customer;

import com.ipfdigital.automation.api.customer.CustomerAddressGenerator;
import com.ipfdigital.automation.api.customer.CustomerBankAccountGenerator;
import com.ipfdigital.automation.api.customer.IdCardGenerator;
import com.ipfdigital.automation.api.customer.MsisdnGenerator;
import com.ipfdigital.automation.api.customer.NameGenerator;
import com.ipfdigital.automation.api.customer.SsnGenerator;

public class CZCustomerGeneratorStrategy extends GenericCustomerGeneratorStrategy {

  private final IdCardGenerator idCardGenerator;

  public CZCustomerGeneratorStrategy(
      final CustomerBankAccountGenerator bankAccountGenerator,
      final CustomerAddressGenerator customerAddressGenerator,
      final MsisdnGenerator msisdnGenerator,
      final NameGenerator nameGenerator,
      final SsnGenerator ssnGenerator,
      final IdCardGenerator idCardGenerator) {
    super(bankAccountGenerator, customerAddressGenerator, msisdnGenerator, nameGenerator,
        ssnGenerator);
    this.idCardGenerator = idCardGenerator;
  }

  @Override
  public boolean accept(final Country country) {
    return Country.CZ == country;
  }

  @Override
  public GeneratedCustomerDTO generate(final GenerateCustomerDTO dto) {
    final GeneratedCustomerDTO generatedCustomerDTO = super.generate(dto);

    generatedCustomerDTO.setPersonalDocumentNumber(idCardGenerator.generate());
    return generatedCustomerDTO;
  }
}
