package bindings.commons.automation.customer.city;

import com.ipfdigital.automation.api.customer.CityGenerator;
import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.address.CityDTO;

import java.util.List;

final class CityGeneratorService implements CityGenerator {

  private final List<CityStrategy> cityStrategies;

  public CityGeneratorService(final List<CityStrategy> cityStrategies) {
    this.cityStrategies = cityStrategies;
  }

  @Override
  public CityDTO generate(final Country country) {
    return cityStrategies
        .stream()
        .filter(strategy -> strategy.accept(country))
        .reduce((a1, a2) -> {
          throw new IllegalStateException(
              "Find more than one city generate strategy for country " + country.name());
        })
        .map(GenerateCityStrategy::generate)
        .orElseThrow(() -> new IllegalStateException(
            "Not found city generator for country " + country.name()));
  }

}
