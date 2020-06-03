package bindings.commons.automation.mule.utils;

import java.util.Base64;

public class Tools {

  public String getAuthorizationBasic(final String userName, final String password) {

    return "Basic " + Base64.getEncoder().encodeToString((userName + ":" + password).getBytes());
  }
}
