package bindings.commons.automation.customer.iban;

import com.ipfdigital.automation.api.customer.CustomerBankAccountGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CustomerBankAccountGeneratorConfig {

  @Bean
  GenerateIbanStrategy mxIbanGeneratorStrategy() {
    return new MXIbanGeneratorStrategy();
  }

  @Bean
  GenerateIbanStrategy czIbanGeneratorStrategy() {
    return new CZIbanGeneratorStrategy();
  }

  @Bean
  GenerateIbanStrategy eeIbanGeneratorStrategy() {
    return new EEIbanGeneratorStrategy();
  }

  @Bean
  GenerateIbanStrategy fiIbanGenetarStrategy() {
    return new FIIbanGeneratorStrategy();
  }

  @Bean
  GenerateIbanStrategy huIbanGeneratorStrategy() {
    return new HUIbanGeneratorStrategy();
  }

  @Bean
  GenerateIbanStrategy lvIbanGeneratorStrategy() {
    return new LVIbanGeneratorStrategy();
  }

  @Bean
  GenerateIbanStrategy ltIbanGeneratorStrategy() {
    return new LTIbanGeneratorStrategy();
  }

  @Bean
  GenerateIbanStrategy plIbanGeneratorStrategy() {
    return new PLIbanGeneratorStrategy();
  }

  @Bean
  GenerateIbanStrategy roIbanGeneratorStrategy() {
    return new ROIbanGeneratorStrategy();
  }

  @Bean
  GenerateIbanStrategy esIbanGeneratorStrategy() {
    return new ESIbanGeneratorStrategy();
  }

  @Bean
  GenerateIbanStrategy auIbanGeneratorStrategy() {
    return new AUIbanGeneratorStrategy();
  }

  @Bean
  CustomerBankAccountGenerator customerBankAccountGenerator(
      final List<GenerateIbanStrategy> ibanStrategyInterfaceList) {
    return new CustomerBankAccountGeneratorService(ibanStrategyInterfaceList);
  }
}
