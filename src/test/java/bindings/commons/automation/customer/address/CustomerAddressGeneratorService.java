package bindings.commons.automation.customer.address;

import com.ipfdigital.automation.api.customer.CityGenerator;
import com.ipfdigital.automation.api.customer.CustomerAddressGenerator;
import com.ipfdigital.automation.api.customer.StreetGenerator;
import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.generators.RandomUtils;

public final class CustomerAddressGeneratorService implements CustomerAddressGenerator {

  private final StreetGenerator streetGenerator;
  private final CityGenerator cityGeneratorService;

  public CustomerAddressGeneratorService(final StreetGenerator streetGenerator,
      final CityGenerator cityGeneratorService) {
    this.streetGenerator = streetGenerator;
    this.cityGeneratorService = cityGeneratorService;
  }

  @Override
  public CustomerAddressDTO generate(final Country country) {
    final CustomerAddressDTO customerAddress = new CustomerAddressDTO();
    customerAddress.setStreet(streetGenerator.generate(country));

    final CityDTO cityDTO = cityGeneratorService.generate(country);
    customerAddress.setCity(cityDTO.getCityName());
    customerAddress.setPostcode(cityDTO.getPostcode());
    customerAddress.setSuburb(cityDTO.getSuburb());
    customerAddress.setDoor(String.valueOf(RandomUtils.randomInt(100) + 1));
    customerAddress.setHome(String.valueOf(RandomUtils.randomInt(200) + 1));
    return customerAddress;
  }
}
