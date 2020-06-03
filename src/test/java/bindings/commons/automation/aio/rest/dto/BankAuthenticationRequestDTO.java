package bindings.commons.automation.aio.rest.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BankAuthenticationRequestDTO {
  public String callbackUrl;

  public BankAuthenticationRequestDTO(String callbackUrl) {
    this.callbackUrl = callbackUrl;
  }
}
