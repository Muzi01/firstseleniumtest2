package bindings.commons.automation.customer.documents.polish;

import com.ipfdigital.automation.api.customer.NipCheckSumCalculator;
import com.ipfdigital.automation.api.customer.NipGenerator;
import com.ipfdigital.automation.api.customer.RegonChecksumGenerator;
import com.ipfdigital.automation.api.customer.TaxOfficeCodesGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PolishDocumentsConfig {

  @Bean
  PolishIdCardGeneratorService polishIdCardGenerator() {
    return new PolishIdCardGeneratorService();
  }

  @Bean
  TaxOfficeCodesGenerator polandTaxOfficeCodes() {
    return new PolishTaxOfficeCodesService();
  }

  @Bean
  NipCheckSumCalculator polandNipCheckSumCalculator() {
    return new PolishNipCheckSumCalculatorService();
  }

  @Bean
  RegonChecksumGenerator polishRegonCheckSumCalculator() {
    return new PolishRegonCheckSumCalculatorService();
  }

  @Bean
  NipGenerator nipGenerator(final NipCheckSumCalculator checkSumCalculator,
      final TaxOfficeCodesGenerator taxOfficeCodes) {
    return new NipGeneratorService(checkSumCalculator, taxOfficeCodes);
  }

  @Bean
  RegonGeneratorService regonGenerator(
      final RegonChecksumGenerator regonCheckSumCalculator) {
    return new RegonGeneratorService(regonCheckSumCalculator);
  }


}
