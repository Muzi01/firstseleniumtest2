package bindings.driver;

public enum MobileType {
  /**
   * List of supported phones:
   * https://cs.chromium.org/chromium/src/chrome/test/chromedriver/chrome/mobile_device_list.cc
   */
  GALAXY_S5("Galaxy S5"),
  IPHONE_6_TO_8("iPhone 6/7/8"),
  IPHONE_6_TO_8_PLUS("iPhone 6/7/8 Plus");

  private String mobileName;

  MobileType (String mobileName) {
    this.mobileName = mobileName;
  }

  public static String fromString(MobileType mobileName) {
    for (MobileType mobile : MobileType.values()) {
      if (mobile.getMobileName().equalsIgnoreCase(String.valueOf (mobileName))) {
        return mobile.getMobileName();
      }
    }
    throw new IllegalArgumentException("No value for provided phone type: " + mobileName);
  }

  public String getMobileName() {
    return mobileName;
  }
}
