package bindings.commons.automation.customer.ssn;

import com.ipfdigital.automation.api.customer.SsnGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SsnGeneratorConfig {

  @Bean
  GenerateSsnStrategy auSsnGeneratorStrategy() {
    return new AUSsnGeneratorStrategy();
  }

  @Bean
  GenerateSsnStrategy czSsnGeneratorStrategy() {
    return new CZSsnGeneratorStrategy();
  }

  @Bean
  GenerateSsnStrategy eeSsnGeneratorStrategy() {
    return new EESsnGeneratorStrategy();
  }

  @Bean
  GenerateSsnStrategy esSsnGeneratorStrategy() {
    return new ESSsnGeneratorStrategy();
  }

  @Bean
  GenerateSsnStrategy fiSsnGeneratorStrategy() {
    return new FISsnGeneratorStrategy();
  }

  @Bean
  GenerateSsnStrategy huSsnGeneratorStrategy() {
    return new HUSsnGeneratorStrategy();
  }

  @Bean
  GenerateSsnStrategy lvSsnGeneratorStrategy() {
    return new LVSsnGeneratorStrategy();
  }

  @Bean
  GenerateSsnStrategy ltSsnGeneratorStrategy() {
    return new LTSsnGeneratorStrategy();
  }

  @Bean
  GenerateSsnStrategy mxSsnGeneratorStrategy() {
    return new MXSsnGeneratorStrategy();
  }

  @Bean
  GenerateSsnStrategy plSsnGeneratorStrategy() {
    return new PLSsnGeneratorStrategy();
  }

  @Bean
  GenerateSsnStrategy roSsnGeneratorStrategy() {
    return new ROSsnGeneratorStrategy();
  }

  @Bean
  SsnGenerator ssnGenerator(
      final List<GenerateSsnStrategy> ssnStrategyInterfaceList) {
    return new SsnGeneratorService(ssnStrategyInterfaceList);
  }
}
