package bindings.commons.automation.customer.city;

import com.ipfdigital.automation.api.customer.CityGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CityGeneratorConfig {

  @Bean
  PLCityGeneratorStrategy plCityGeneratorStrategy() {
    return new PLCityGeneratorStrategy();
  }

  @Bean
  AUCityGeneratorStrategy auCityGeneratorStrategy() {
    return new AUCityGeneratorStrategy();
  }

  @Bean
  CZCityGeneratorStrategy czCityGeneratorStrategy() {
    return new CZCityGeneratorStrategy();
  }

  @Bean
  EECityGeneratorStrategy eeCityGeneratorStrategy() {
    return new EECityGeneratorStrategy();
  }

  @Bean
  ESCityGeneratorStrategy esCityGeneratorStrategy() {
    return new ESCityGeneratorStrategy();
  }

  @Bean
  FICityGeneratorStrategy fiCityGeneratorStrategy() {
    return new FICityGeneratorStrategy();
  }

  @Bean
  HUCityGeneratorStrategy huCityGeneratorStrategy() {
    return new HUCityGeneratorStrategy();
  }

  @Bean
  LTCityGeneratorStrategy ltCityGeneratorStrategy() {
    return new LTCityGeneratorStrategy();
  }

  @Bean
  LVCityGeneratorStrategy lvCityGeneratorStrategy() {
    return new LVCityGeneratorStrategy();
  }

  @Bean
  MXCityGeneratorStrategy mxCityGeneratorStrategy() {
    return new MXCityGeneratorStrategy();
  }

  @Bean
  ROCityGeneratorStrategy roCityGeneratorStrategy() {
    return new ROCityGeneratorStrategy();
  }

  @Bean
  CityGenerator cityGenerator(final List<CityStrategy> cityStrategies) {
    return new CityGeneratorService(cityStrategies);
  }

  @Bean
  CityDtoProvider cityDtoProvider(final List<CityStrategy> cityStrategies) {
    return new CityDtoProvider(cityStrategies);
  }
}
