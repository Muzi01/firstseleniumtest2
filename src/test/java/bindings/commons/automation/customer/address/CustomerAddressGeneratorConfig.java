package bindings.commons.automation.customer.address;

import com.ipfdigital.automation.api.customer.CityGenerator;
import com.ipfdigital.automation.api.customer.CustomerAddressGenerator;
import com.ipfdigital.automation.api.customer.StreetGenerator;
import com.ipfdigital.automation.customer.city.CityGeneratorConfig;
import com.ipfdigital.automation.customer.streets.StreetGeneratorConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import ({StreetGeneratorConfig.class, CityGeneratorConfig.class})
public class CustomerAddressGeneratorConfig {

  @Bean
  CustomerAddressGenerator addressGenerator(final StreetGenerator streetGenerator,
      final CityGenerator cityGenerator) {
    return new CustomerAddressGeneratorService(streetGenerator, cityGenerator);
  }
}
