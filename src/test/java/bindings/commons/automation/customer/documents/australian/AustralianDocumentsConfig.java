package bindings.commons.automation.customer.documents.australian;

import com.ipfdigital.automation.api.customer.DriversLicenceGenerator;
import com.ipfdigital.automation.api.customer.MedicareGenerator;
import com.ipfdigital.automation.api.customer.PassportGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AustralianDocumentsConfig {

  @Bean
  DriversLicenceGenerator australianDriversLicenceGenerator() {
    return new AustralianDriversLicenceGenerator();
  }

  @Bean
  PassportGenerator australianPassportGenerator() {
    return new AustralianPassportGenerator();
  }

  @Bean
  MedicareGenerator medicareGenerator() {
    return new MedicareGeneratorService();
  }

}
