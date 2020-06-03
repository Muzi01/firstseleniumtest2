package bindings.commons.automation.aio.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TMXInitRequestDTO {

  @JsonProperty ("top_domain")
  public String topDomain;

  @JsonProperty ("selected_amount")
  public Integer selectedAmount;

  public TMXInitRequestDTO(final String topDomain, final Integer selectedAmount) {
    this.topDomain = topDomain;
    this.selectedAmount = selectedAmount;
  }
}
