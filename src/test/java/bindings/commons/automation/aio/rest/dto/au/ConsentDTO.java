package bindings.commons.automation.aio.rest.dto.au;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude (JsonInclude.Include.NON_NULL)
@JsonPropertyOrder ({
    "general-terms",
    "communication",
    "direct-debit"
})
public class ConsentDTO {
  @JsonProperty ("general-terms")
  public boolean generalTerms;
  @JsonProperty ("communication")
  public boolean communication;
  @JsonProperty ("direct-debit")
  public boolean directDebit;
}
