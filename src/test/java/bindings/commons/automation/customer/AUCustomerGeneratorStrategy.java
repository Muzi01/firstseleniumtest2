package bindings.commons.automation.customer;

import com.ipfdigital.automation.api.customer.CustomerAddressGenerator;
import com.ipfdigital.automation.api.customer.CustomerBankAccountGenerator;
import com.ipfdigital.automation.api.customer.DriversLicenceGenerator;
import com.ipfdigital.automation.api.customer.EmploymentHistoryGenerator;
import com.ipfdigital.automation.api.customer.MedicareGenerator;
import com.ipfdigital.automation.api.customer.MsisdnGenerator;
import com.ipfdigital.automation.api.customer.NameGenerator;
import com.ipfdigital.automation.api.customer.PassportGenerator;
import com.ipfdigital.automation.api.customer.SsnGenerator;
import com.ipfdigital.automation.customer.australian.AustralianPassportDTO;
import com.ipfdigital.automation.generators.RandomUtils;

final class AUCustomerGeneratorStrategy extends GenericCustomerGeneratorStrategy {

  private final DriversLicenceGenerator driversLicenceGenerator;
  private final PassportGenerator passportGenerator;
  private final MedicareGenerator medicareGenerator;
  private final EmploymentHistoryGenerator employmentHistoryGenerator;

  public AUCustomerGeneratorStrategy(final CustomerBankAccountGenerator bankAccountGenerator,
      final CustomerAddressGenerator customerAddressGenerator,
      final MsisdnGenerator msisdnGenerator,
      final NameGenerator nameGenerator,
      final SsnGenerator ssnGeneratorService,
      final DriversLicenceGenerator driversLicenceGenerator,
      final PassportGenerator passportGenerator,
      final MedicareGenerator medicareGenerator,
      final EmploymentHistoryGenerator employmentHistoryGenerator) {
    super(bankAccountGenerator, customerAddressGenerator, msisdnGenerator, nameGenerator,
        ssnGeneratorService);
    this.driversLicenceGenerator = driversLicenceGenerator;
    this.passportGenerator = passportGenerator;
    this.medicareGenerator = medicareGenerator;
    this.employmentHistoryGenerator = employmentHistoryGenerator;
  }

  @Override
  public boolean accept(final Country country) {
    return Country.AU == country;
  }

  @Override
  public GeneratedCustomerDTO generate(final GenerateCustomerDTO dto) {
    final GeneratedCustomerDTO generatedCustomerDTO = super.generate(dto);
    generatedCustomerDTO.setLoanPurpose(RandomUtils.randomEnum(LoanPurpose.class));
    generatedCustomerDTO.setMedicare(medicareGenerator.generate());

    final AustralianPassportDTO australianPassport = passportGenerator.generate();
    generatedCustomerDTO.setPassport(australianPassport.getPassportNumber());
    generatedCustomerDTO.setPersonalDocumentNumber(australianPassport.getPassportNumber());

    generatedCustomerDTO.setDriversLicence(driversLicenceGenerator.generate());
    generatedCustomerDTO.setState(RandomUtils.randomEnum(State.class));
    generatedCustomerDTO.setMaritalStatus(RandomUtils.randomEnum(MaritalStatus.class));
    generatedCustomerDTO.setResidence(RandomUtils.randomEnum(Residence.class));
    generatedCustomerDTO.setEmploymentHistory(employmentHistoryGenerator.generate());
    generatedCustomerDTO.setResidence(RandomUtils.randomEnum(Residence.class));
    return generatedCustomerDTO;
  }
}
