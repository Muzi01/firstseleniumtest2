package bindings.commons.automation.customer.employment;

import com.ipfdigital.automation.api.customer.EmploymentGenerator;
import com.ipfdigital.automation.api.customer.EmploymentHistoryGenerator;
import com.ipfdigital.automation.api.customer.EmploymentTypeGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmploymentConfig {

  @Bean
  EmploymentTypeGenerator employmentTypeGenerator() {
    return new EmploymentTypeGeneratorService();
  }

  @Bean
  EmploymentGenerator employmentGenerator(final EmploymentTypeGenerator employmentTypeGenerator) {
    return new EmploymentGeneratorService(employmentTypeGenerator);
  }

  @Bean
  EmploymentHistoryGenerator employmentHistoryGenerator(
      final EmploymentGenerator employmentGeneratorService,
      final EmploymentTypeGenerator employmentTypeGenerator) {
    return new EmploymentHistoryGeneratorService(employmentGeneratorService,
        employmentTypeGenerator);
  }
}
