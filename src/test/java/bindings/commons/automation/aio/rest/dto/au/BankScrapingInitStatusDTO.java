package bindings.commons.automation.aio.rest.dto.au;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat (shape = JsonFormat.Shape.STRING)
public enum BankScrapingInitStatusDTO {
  SUCCESS,
  FAILURE
}
