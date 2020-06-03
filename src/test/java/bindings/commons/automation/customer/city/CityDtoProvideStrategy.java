package bindings.commons.automation.customer.city;

import com.ipfdigital.automation.customer.AcceptStrategy;
import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.address.CityDTO;

import java.util.List;

public interface CityDtoProvideStrategy extends AcceptStrategy<Country> {

  default CityDTO provideCityDto(final String cityName) {
    return getCities().stream().filter(cityDTO -> cityDTO.getCityName().equalsIgnoreCase(cityName))
        .findFirst()
        .orElseThrow(() -> new IllegalStateException(
            String.format("Not found cityDto with city: %s, for strategy %s", cityName,
                getClass().getSimpleName())));
  }

  List<CityDTO> getCities();
}
