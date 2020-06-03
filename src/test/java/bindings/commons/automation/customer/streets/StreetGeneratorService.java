package bindings.commons.automation.customer.streets;

import com.ipfdigital.automation.api.customer.StreetGenerator;
import com.ipfdigital.automation.customer.Country;

import java.util.List;

final class StreetGeneratorService implements StreetGenerator {

  private final List<StreetGeneratorStrategy> streetGeneratorStrategyList;

  public StreetGeneratorService(
      final List<StreetGeneratorStrategy> streetGeneratorStrategyList) {
    this.streetGeneratorStrategyList = streetGeneratorStrategyList;
  }

  @Override
  public String generate(final Country country) {
    return streetGeneratorStrategyList
        .stream()
        .filter(strategy -> strategy.accept(country))
        .reduce((a1, a2) -> {
          throw new IllegalStateException(
              "Find more than one street generate strategy for country " + country.name());
        })
        .map(StreetGeneratorStrategy::generate)
        .orElseThrow(
            () -> new IllegalStateException("Not found street for country " + country.name()));
  }
}
