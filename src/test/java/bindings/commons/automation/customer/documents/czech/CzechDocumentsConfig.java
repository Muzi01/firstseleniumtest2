package bindings.commons.automation.customer.documents.czech;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CzechDocumentsConfig {

  @Bean
  CzechIdCardGeneratorService czechIdCardGenerator() {
    return new CzechIdCardGeneratorService();
  }
}
