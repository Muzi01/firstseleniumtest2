package bindings.commons.automation.customer.names;

import com.ipfdigital.automation.api.customer.NameGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class NamesGeneratorConfig {

  @Bean
  GenerateNameStrategy auNameGeneratorStrategy() {
    return new AUNameGeneratorStrategy();
  }

  @Bean
  GenerateNameStrategy czNameGeneratorStrategy() {
    return new CZNameGeneratorStrategy();
  }

  @Bean
  GenerateNameStrategy eeNameGeneratorStrategy() {
    return new EENameGeneratorStrategy();
  }

  @Bean
  GenerateNameStrategy esNameGeneratorStrategy() {
    return new ESNameGeneratorStrategy();
  }

  @Bean
  GenerateNameStrategy fiNameGeneratorStrategy() {
    return new FINameGeneratorStrategy();
  }

  @Bean
  GenerateNameStrategy huNameGeneratorStrategy() {
    return new HUNameGeneratorStrategy();
  }

  @Bean
  GenerateNameStrategy ltNameGeneratorStrategy() {
    return new LTNameGeneratorStrategy();
  }

  @Bean
  GenerateNameStrategy lvNameGeneratorStrategy() {
    return new LVNameGeneratorStrategy();
  }

  @Bean
  GenerateNameStrategy mxNameGeneratorStrategy() {
    return new MXNameGeneratorStrategy();
  }

  @Bean
  GenerateNameStrategy plNameGeneratorStrategy() {
    return new PLNameGeneratorStrategy();
  }

  @Bean
  GenerateNameStrategy roNameGeneratorStrategy() {
    return new RONameGeneratorStrategy();
  }

  @Bean
  NameGenerator nameGenerator(
      final List<GenerateNameStrategy> nameStrategyInterfaceList) {
    return new NameGeneratorService(nameStrategyInterfaceList);
  }
}
