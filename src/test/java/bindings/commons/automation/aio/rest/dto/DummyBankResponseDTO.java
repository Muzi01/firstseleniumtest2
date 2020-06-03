package bindings.commons.automation.aio.rest.dto;

import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Map;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

public class DummyBankResponseDTO {

  public String url;
  public Map<String, String> parameters;
  public String transactionId;

  @Override
  public String toString() {
    return reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
