package bindings.commons.automation.aio.rest.v2.banks;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MobileIdAuthRequestDTO {
  @JsonProperty ("SSN")
  public String ssn;
  public String msisdn;

  public MobileIdAuthRequestDTO(String ssn, String msisdn) {
    this.ssn = ssn;
    this.msisdn = msisdn;
  }
}
