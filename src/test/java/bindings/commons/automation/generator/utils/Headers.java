package bindings.commons.automation.generator.utils;

import com.ipfdigital.automation.customer.Country;

import java.util.HashMap;
import java.util.Map;

public class Headers {
  private Headers() {
    throw new IllegalStateException("Headers are utility class and should not be instantiate!");
  }

  public static Map<String, String> getMobileWalletHeaders(final Country country) {
    final Map<String, String> headers = new HashMap<>();
    headers.put("content-type", "application/json");
    headers.put("x-channel", "Mobile-Wallet");
    headers.put("x-country", country.name());

    return headers;
  }
}
