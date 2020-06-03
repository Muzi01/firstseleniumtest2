package bindings.commons.automation.customer.msisdn;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MsisdnGeneratorConfig {

  @Bean
  GenerateMsisdnStrategy auMsisdnGeneratorStrategy() {
    return new AUMsisdnGeneratorStrategy();
  }

  @Bean
  GenerateMsisdnStrategy czMsisdnGeneratorStrategy() {
    return new CZMsisdnGeneratorStrategy();
  }

  @Bean
  GenerateMsisdnStrategy eeMsisdnGeneratorStrategy() {
    return new EEMsisdnGeneratorStrategy();
  }

  @Bean
  GenerateMsisdnStrategy esMsisdnGeneratorStrategy() {
    return new ESMsisdnGeneratorStrategy();
  }

  @Bean
  GenerateMsisdnStrategy fiMsisdnGeneratorStrategy() {
    return new FIMsisdnGeneratorStrategy();
  }

  @Bean
  GenerateMsisdnStrategy huMsisdnGeneratorStrategy() {
    return new HUMsisdnGeneratorStrategy();
  }

  @Bean
  GenerateMsisdnStrategy ltMsisdnGeneratorStrategy() {
    return new LTMsisdnGeneratorStrategy();
  }

  @Bean
  GenerateMsisdnStrategy lvMsisdnGeneratorStrategy() {
    return new LVMsisdnGeneratorStrategy();
  }

  @Bean
  GenerateMsisdnStrategy mxMsisdnGeneratorStrategy() {
    return new MXMsisdnGeneratorStrategy();
  }

  @Bean
  GenerateMsisdnStrategy plMsisdnGeneratorStrategy() {
    return new PLMsisdnGeneratorStrategy();
  }

  @Bean
  GenerateMsisdnStrategy roMsisdnGeneratorStrategy() {
    return new ROMsisdnGeneratorStrategy();
  }

  @Bean
  MsisdnGeneratorService msisdnGenerator(
      final List<GenerateMsisdnStrategy> msisdnStrategyInterfaceList) {
    return new MsisdnGeneratorService(msisdnStrategyInterfaceList);
  }
}
