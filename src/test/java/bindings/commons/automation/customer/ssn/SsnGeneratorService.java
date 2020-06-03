package bindings.commons.automation.customer.ssn;

import com.ipfdigital.automation.api.customer.SsnGenerator;

import java.util.List;

final class SsnGeneratorService implements SsnGenerator {

  private final List<GenerateSsnStrategy> ssnStrategyInterfaceList;

  public SsnGeneratorService(final List<GenerateSsnStrategy> ssnStrategyInterfaceList) {
    this.ssnStrategyInterfaceList = ssnStrategyInterfaceList;
  }

  @Override
  public String generate(final GenerateSsnParamsDTO dto) {
    return ssnStrategyInterfaceList
        .stream()
        .filter(strategy -> strategy.accept(dto.getCountry()))
        .reduce((a1, a2) -> {
          throw new IllegalStateException(
              "Find more than one ssn generate strategy for country " + dto.getCountry().name());
        })
        .map(generator -> generator.generate(dto))
        .orElseThrow(
            () -> new IllegalStateException(
                "Not found ssn strategy for country" + dto.getCountry().name()));
  }
}
