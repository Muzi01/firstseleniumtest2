package bindings.commons.automation.customer.debitcards;

import com.ipfdigital.automation.api.customer.DebitCardGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DebitCardsConfig {
  @Bean
  DebitCardGenerator mxDebitCardGenerator() {
    return new MXDebitCardGenerator();
  }
}
