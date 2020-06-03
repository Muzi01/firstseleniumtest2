package bindings.commons.automation.generators;

import java.util.concurrent.TimeUnit;

import static com.ipfdigital.automation.generators.RandomUtils.randomAlphanumeric;

public class TestUtils {

  private TestUtils() {
  }


  public static String randomEmail(final String domain) {
    return randomAlphanumeric(10) + "@" + domain;
  }

  public static long getMillisFromNow(final int daysAgo) {
    return System.currentTimeMillis() - TimeUnit.DAYS.toMillis(daysAgo);
  }
}
