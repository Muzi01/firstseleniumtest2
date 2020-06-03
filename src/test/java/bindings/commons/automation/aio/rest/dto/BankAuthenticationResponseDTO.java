package bindings.commons.automation.aio.rest.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

@XmlRootElement
public class BankAuthenticationResponseDTO {

  public String url;
  public Map<String, String> parameters;
  public AuthType authType;

  public BankAuthenticationResponseDTO() {
  }

  public BankAuthenticationResponseDTO(String url, Map<String, String> parameters) {
    super();
    this.url = url;
    this.parameters = parameters;
  }

  public BankAuthenticationResponseDTO(
      String url, Map<String, String> parameters, AuthType authType) {
    super();
    this.url = url;
    this.parameters = parameters;
    this.authType = authType;
  }

  @Override
  public String toString() {
    return "BankAuthenticationResponseDTO{" +
        "url='" + url + '\'' +
        ", parameters=" + parameters +
        ", authType=" + authType +
        '}';
  }

  public enum AuthType {
    TUPAS,
    IPIZZA,
    EE_MOIBLE_ID,
    BANK,
    EE_ID_CARD,
    SIGNICAT
  }
}
