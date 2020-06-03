package bindings.commons.automation.customer.city;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.address.CityDTO;

import java.util.List;

public class CityDtoProvider {

  private final List<CityStrategy> cityStrategies;

  public CityDtoProvider(final List<CityStrategy> cityStrategies) {
    this.cityStrategies = cityStrategies;
  }


  public CityDTO provide(final Country country, final String cityName) {
    return cityStrategies
        .stream()
        .filter(strategy -> strategy.accept(country))
        .reduce((a1, a2) -> {
          throw new IllegalStateException(
              "Find more than one city generate strategy for country " + country.name());
        })
        .map(strategy -> strategy.provideCityDto(cityName))
        .orElseThrow(
            () -> new IllegalStateException("Not found cities for country " + country.name()));

  }
}
