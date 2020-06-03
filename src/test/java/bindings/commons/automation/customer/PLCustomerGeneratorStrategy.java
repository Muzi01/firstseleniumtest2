package bindings.commons.automation.customer;

import com.ipfdigital.automation.api.customer.CustomerAddressGenerator;
import com.ipfdigital.automation.api.customer.CustomerBankAccountGenerator;
import com.ipfdigital.automation.api.customer.IdCardGenerator;
import com.ipfdigital.automation.api.customer.MsisdnGenerator;
import com.ipfdigital.automation.api.customer.NameGenerator;
import com.ipfdigital.automation.api.customer.NipGenerator;
import com.ipfdigital.automation.api.customer.RegonGenerator;
import com.ipfdigital.automation.api.customer.SsnGenerator;

final class PLCustomerGeneratorStrategy extends GenericCustomerGeneratorStrategy {
  private final NipGenerator nipGenerator;
  private final RegonGenerator regonGenerator;
  private final IdCardGenerator cardGenerator;

  public PLCustomerGeneratorStrategy(final CustomerBankAccountGenerator bankAccountGenerator,
      final CustomerAddressGenerator customerAddressGenerator,
      final MsisdnGenerator msisdnGeneratorService,
      final NameGenerator nameGeneratorService,
      final SsnGenerator ssnGeneratorService,
      final NipGenerator nipGenerator, final RegonGenerator regonGenerator,
      final IdCardGenerator cardGenerator) {
    super(bankAccountGenerator, customerAddressGenerator, msisdnGeneratorService,
        nameGeneratorService,
        ssnGeneratorService);
    this.nipGenerator = nipGenerator;
    this.regonGenerator = regonGenerator;
    this.cardGenerator = cardGenerator;
  }

  @Override
  public boolean accept(final Country country) {
    return Country.PL == country;
  }

  @Override
  public GeneratedCustomerDTO generate(final GenerateCustomerDTO dto) {
    final GeneratedCustomerDTO generatedCustomerDTO = super.generate(dto);

    generatedCustomerDTO.setRegon(regonGenerator.generate());
    generatedCustomerDTO.setNip(nipGenerator.generate());
    generatedCustomerDTO.setPersonalDocumentNumber(cardGenerator.generate());
    return generatedCustomerDTO;
  }
}
