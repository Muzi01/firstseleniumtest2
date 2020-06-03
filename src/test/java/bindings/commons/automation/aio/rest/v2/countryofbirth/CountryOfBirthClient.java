package bindings.commons.automation.aio.rest.v2.countryofbirth;

import com.ipfdigital.automation.aio.rest.dto.CountryOfBirthUpdateDTO;
import feign.RequestLine;

public interface CountryOfBirthClient {
  @RequestLine("PUT /countryOfBirth")
  void storeCountryOfBirth(CountryOfBirthUpdateDTO countryOfBirth);
}
