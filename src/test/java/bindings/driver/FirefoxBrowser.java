package bindings.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;


public class FirefoxBrowser {
  private FirefoxBrowser() {
    throw new IllegalStateException("This class should not be instantiated!");
  }


  public static FirefoxOptions getOptions() {
    FirefoxProfile profile = new FirefoxProfile();

    final FirefoxOptions options = new FirefoxOptions();
    final String isHeadlessBrowser = System.getProperty("headless").toLowerCase();

    if (isHeadlessBrowser.equals("false")) {
      profile.setPreference("dom.webnotifications.enabled", false);
      profile.setPreference("dom.push.enabled", false);
      profile.setPreference("app.update.auto", false);
      profile.setPreference("app.update.enabled", false);
    } else {
      options.setHeadless(true);
    }
    options.setProfile(profile);
    return options;
  }

  public static WebDriver setupDriver() {
    final FirefoxOptions options = FirefoxBrowser.getOptions();
    return new FirefoxDriver(options);
  }
}
