package bindings.commons.automation.customer;

import com.ipfdigital.automation.api.customer.CustomerGenerator;

import java.util.List;

final class CustomerGeneratorService implements CustomerGenerator {

  private final List<GenerateCustomerStrategy> customerDataStrategies;
  private final GenericCustomerGeneratorStrategy genericCustomerDataGeneratorStrategy;

  public CustomerGeneratorService(
      final List<GenerateCustomerStrategy> customerDataStrategies,
      final GenericCustomerGeneratorStrategy genericCustomerDataGeneratorStrategy) {
    this.customerDataStrategies = customerDataStrategies;
    this.genericCustomerDataGeneratorStrategy = genericCustomerDataGeneratorStrategy;
  }

  @Override
  public GeneratedCustomerDTO generate(final GenerateCustomerDTO parametersDTO) {
    return customerDataStrategies
        .stream()
        .filter(strategy -> strategy.accept(parametersDTO.country))
        .reduce((a1, a2) -> {
          throw new IllegalStateException(
              "Find more than one customer data generate strategy for country "
                  + parametersDTO.country.name());
        })
        .map(finded -> finded.generate(parametersDTO))
        .orElse(genericCustomerDataGeneratorStrategy.generate(parametersDTO));
  }
}
