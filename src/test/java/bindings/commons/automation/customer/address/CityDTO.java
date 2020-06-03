package bindings.commons.automation.customer.address;

public class CityDTO {
  private String cityName;
  private String postcode;
  private String suburb;

  public CityDTO(final String cityName, final String postcode, final String suburb) {
    this.cityName = cityName;
    this.postcode = postcode;
    this.suburb = suburb;
  }

  public CityDTO(final String cityName, final String postcode) {
    this.cityName = cityName;
    this.postcode = postcode;
  }

  public String getCityName() {
    return cityName;
  }

  public String getPostcode() {
    return postcode;
  }

  public String getSuburb() {
    return suburb;
  }

  public void setCityName(final String cityName) {
    this.cityName = cityName;
  }

  public void setPostcode(final String postcode) {
    this.postcode = postcode;
  }

  public void setSuburb(final String suburb) {
    this.suburb = suburb;
  }
}
