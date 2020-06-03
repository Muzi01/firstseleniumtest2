package bindings.commons.automation.customer.address;

import com.ipfdigital.automation.customer.Country;

public class CustomerAddressDTO {
  private Country country;
  private String street;
  private String postcode;
  private String city;
  private String suburb;
  private String door;
  private String home; // floor?

  public Country getCountry() {
    return country;
  }

  public void setCountry(final Country country) {
    this.country = country;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(final String street) {
    this.street = street;
  }

  public String getPostcode() {
    return postcode;
  }

  public void setPostcode(final String postcode) {
    this.postcode = postcode;
  }

  public String getCity() {
    return city;
  }

  public void setCity(final String city) {
    this.city = city;
  }

  public String getSuburb() {
    return suburb;
  }

  public void setSuburb(final String suburb) {
    this.suburb = suburb;
  }

  public String getDoor() {
    return door;
  }

  public void setDoor(final String door) {
    this.door = door;
  }

  public String getHome() {
    return home;
  }

  public void setHome(final String home) {
    this.home = home;
  }
}
