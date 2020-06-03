package bindings.commons.automation.customer;

import com.ipfdigital.automation.generator.utils.AgeLimit;

public class GenerateCustomerDTOMX {

  public GenerateCustomerDTO customizeForMX(final GenerateCustomerDTO dto) {
    if (dto.minAge < AgeLimit.MX.getMinAge()) {
      dto.minAge = AgeLimit.MX.getMinAge();
    }
    if (dto.maxAge > AgeLimit.MX.getMaxAge()) {
      dto.maxAge = AgeLimit.MX.getMaxAge();
    }
    return dto;
  }
}
