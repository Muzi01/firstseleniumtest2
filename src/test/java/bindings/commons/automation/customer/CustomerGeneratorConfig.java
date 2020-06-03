package bindings.commons.automation.customer;

import com.ipfdigital.automation.api.customer.CustomerAddressGenerator;
import com.ipfdigital.automation.api.customer.CustomerBankAccountGenerator;
import com.ipfdigital.automation.api.customer.CustomerGenerator;
import com.ipfdigital.automation.api.customer.DebitCardGenerator;
import com.ipfdigital.automation.api.customer.DriversLicenceGenerator;
import com.ipfdigital.automation.api.customer.EmploymentHistoryGenerator;
import com.ipfdigital.automation.api.customer.MedicareGenerator;
import com.ipfdigital.automation.api.customer.MsisdnGenerator;
import com.ipfdigital.automation.api.customer.NameGenerator;
import com.ipfdigital.automation.api.customer.NipGenerator;
import com.ipfdigital.automation.api.customer.PassportGenerator;
import com.ipfdigital.automation.api.customer.RegonGenerator;
import com.ipfdigital.automation.api.customer.SsnGenerator;
import com.ipfdigital.automation.customer.address.CustomerAddressGeneratorConfig;
import com.ipfdigital.automation.customer.debitcards.DebitCardsConfig;
import com.ipfdigital.automation.customer.documents.australian.AustralianDocumentsConfig;
import com.ipfdigital.automation.customer.documents.czech.CzechDocumentsConfig;
import com.ipfdigital.automation.customer.documents.czech.CzechIdCardGeneratorService;
import com.ipfdigital.automation.customer.documents.polish.PolishDocumentsConfig;
import com.ipfdigital.automation.customer.documents.polish.PolishIdCardGeneratorService;
import com.ipfdigital.automation.customer.employment.EmploymentConfig;
import com.ipfdigital.automation.customer.iban.CustomerBankAccountGeneratorConfig;
import com.ipfdigital.automation.customer.msisdn.MsisdnGeneratorConfig;
import com.ipfdigital.automation.customer.names.NamesGeneratorConfig;
import com.ipfdigital.automation.customer.ssn.SsnGeneratorConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.List;

@Configuration
@Import ({CustomerAddressGeneratorConfig.class,
    CustomerBankAccountGeneratorConfig.class,
    SsnGeneratorConfig.class,
    MsisdnGeneratorConfig.class,
    NamesGeneratorConfig.class,
    AustralianDocumentsConfig.class,
    EmploymentConfig.class,
    PolishDocumentsConfig.class,
    CzechDocumentsConfig.class,
    DebitCardsConfig.class
})
public class CustomerGeneratorConfig {

  @Bean
  GenerateCustomerStrategy auCustomerDataGeneratorStrategy(
      final CustomerBankAccountGenerator customerBankAccountGenerator,
      final CustomerAddressGenerator customerAddressGenerator,
      final MsisdnGenerator msisdnGenerator,
      final NameGenerator nameGenerator,
      final SsnGenerator ssnGenerator,
      final DriversLicenceGenerator driversLicenceGenerator,
      final PassportGenerator passportGenerator,
      final MedicareGenerator medicareGeneratorService,
      final EmploymentHistoryGenerator employmentHistoryGeneratorService) {
    return new AUCustomerGeneratorStrategy(customerBankAccountGenerator,
        customerAddressGenerator,
        msisdnGenerator, nameGenerator, ssnGenerator, driversLicenceGenerator,
        passportGenerator, medicareGeneratorService, employmentHistoryGeneratorService);
  }

  @Bean
  GenerateCustomerStrategy mxCustomerDataGeneratorStrategy(
      final CustomerBankAccountGenerator customerBankAccountGenerator,
      final CustomerAddressGenerator customerAddressGenerator,
      final MsisdnGenerator msisdnGenerator,
      final NameGenerator nameGenerator,
      final SsnGenerator ssnGenerator,
      final DebitCardGenerator debitCardGenerator) {
    return new MXCustomerGeneratorStrategy(customerBankAccountGenerator,
        customerAddressGenerator,
        msisdnGenerator, nameGenerator, ssnGenerator, debitCardGenerator);
  }

  @Bean
  GenerateCustomerStrategy plCustomerDataGeneratorStrategy(
      final CustomerBankAccountGenerator customerBankAccountGenerator,
      final CustomerAddressGenerator customerAddressGeneratorService,
      final MsisdnGenerator msisdnGeneratorService,
      final NameGenerator nameGeneratorService,
      final SsnGenerator ssnGenerator,
      final NipGenerator nipGenerator,
      final RegonGenerator regonGenerator,
      final PolishIdCardGeneratorService idCardGenerator) {
    return new PLCustomerGeneratorStrategy(customerBankAccountGenerator,
        customerAddressGeneratorService, msisdnGeneratorService, nameGeneratorService, ssnGenerator,
        nipGenerator,
        regonGenerator, idCardGenerator);
  }

  @Bean
  GenerateCustomerStrategy czCustomerDataGeneratorStrategy(
      final CustomerBankAccountGenerator bankAccountGenerator,
      final CustomerAddressGenerator customerAddressGenerator,
      final MsisdnGenerator msisdnGenerator,
      final NameGenerator nameGenerator,
      final SsnGenerator ssnGenerator,
      final CzechIdCardGeneratorService idCardGenerator) {
    return new CZCustomerGeneratorStrategy(bankAccountGenerator, customerAddressGenerator,
        msisdnGenerator, nameGenerator, ssnGenerator, idCardGenerator);
  }

  @Bean
  GenericCustomerGeneratorStrategy genericCustomerDataGeneratorStrategy(
      final CustomerBankAccountGenerator customerBankAccountGenerator,
      final CustomerAddressGenerator customerAddressGenerator,
      final MsisdnGenerator msisdnGenerator,
      final NameGenerator nameGenerator,
      final SsnGenerator ssnGenerator) {
    return new GenericCustomerGeneratorStrategy(customerBankAccountGenerator,
        customerAddressGenerator, msisdnGenerator, nameGenerator, ssnGenerator);
  }

  @Bean
  CustomerGenerator customerDataGenerator(
      final List<GenerateCustomerStrategy> dataGeneratorStrategyInterfaces,
      final GenericCustomerGeneratorStrategy genericCustomerDataGeneratorStrategy) {
    return new CustomerGeneratorService(dataGeneratorStrategyInterfaces,
        genericCustomerDataGeneratorStrategy);
  }

}
