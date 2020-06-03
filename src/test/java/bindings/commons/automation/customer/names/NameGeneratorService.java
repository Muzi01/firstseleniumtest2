package bindings.commons.automation.customer.names;

import com.ipfdigital.automation.api.customer.NameGenerator;
import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.Gender;

import java.util.List;
import java.util.Optional;

final class NameGeneratorService implements NameGenerator {
  private static final String NOT_FOUND_STRATEGY_MSG_TEMPLATE =
      "Not found strategy name for country %s";

  private final List<GenerateNameStrategy> nameStrategyInterfaceList;

  public NameGeneratorService(final List<GenerateNameStrategy> nameStrategyInterfaceList) {
    this.nameStrategyInterfaceList = nameStrategyInterfaceList;
  }

  @Override
  public String generateFirstName(final Country country, final Gender gender) {
    return findStrategy(country)
        .map(generator -> generator.generateFirstName(gender))
        .orElseThrow(() -> new IllegalStateException(
            String.format(NOT_FOUND_STRATEGY_MSG_TEMPLATE, country.name())));
  }

  @Override
  public String generateLastName(final Country country, final Gender gender) {
    return findStrategy(country)
        .map(generator -> generator.generateLastName(gender))
        .orElseThrow(() -> new IllegalStateException(
            String.format(NOT_FOUND_STRATEGY_MSG_TEMPLATE, country.name())));
  }

  @SuppressWarnings("unchecked")
  private Optional<GenerateNameStrategy> findStrategy(final Country country) {
    return nameStrategyInterfaceList
        .stream()
        .filter(strategy -> strategy.accept(country))
        .reduce((a1, a2) -> {
          throw new IllegalStateException(
              "Find more than one name generate strategy for country " + country.name());
        });
  }
}
