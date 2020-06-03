package bindings.commons.automation.customer.streets;

import com.ipfdigital.automation.api.customer.StreetGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class StreetGeneratorConfig {

  @Bean
  StreetGeneratorStrategy auStreetStrategyGenerator() {
    return new AUStreetGeneratorGeneratorStrategy();
  }

  @Bean
  StreetGeneratorStrategy czStreetStrategyGenerator() {
    return new CZStreetGeneratorGeneratorStrategy();
  }

  @Bean
  StreetGeneratorStrategy eeStreetStrategyGenerator() {
    return new EEStreetGeneratorGeneratorStrategy();
  }

  @Bean
  StreetGeneratorStrategy esStreetStrategyGenerator() {
    return new ESStreetGeneratorGeneratorStrategy();
  }

  @Bean
  StreetGeneratorStrategy fiStreetStrategyGenerator() {
    return new FIStreetGeneratorGeneratorStrategy();
  }

  @Bean
  StreetGeneratorStrategy huStreetStrategyGenerator() {
    return new HUStreetGeneratorGeneratorStrategy();
  }

  @Bean
  StreetGeneratorStrategy ltStreetStrategyGenerator() {
    return new LTStreetGeneratorGeneratorStrategy();
  }

  @Bean
  StreetGeneratorStrategy lvStreetStrategyGenerator() {
    return new LVStreetGeneratorGeneratorStrategy();
  }

  @Bean
  StreetGeneratorStrategy mxStreetStrategyGenerator() {
    return new MXStreetGeneratorGeneratorStrategy();
  }

  @Bean
  StreetGeneratorStrategy plStreetStrategyGenerator() {
    return new PLStreetGeneratorGeneratorStrategy();
  }

  @Bean
  StreetGeneratorStrategy roStreetStrategyGenerator() {
    return new ROStreetGeneratorGeneratorStrategy();
  }

  @Bean
  public StreetGenerator streetGenerator(
      final List<StreetGeneratorStrategy> streetStrategyInterfaceList) {
    return new StreetGeneratorService(streetStrategyInterfaceList);
  }
}
