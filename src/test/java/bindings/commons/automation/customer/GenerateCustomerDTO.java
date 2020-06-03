package bindings.commons.automation.customer;

public class GenerateCustomerDTO {

  public static final int MIN_AGE_DEFAULT_VALUE = 20;
  public static final int MAX_AGE_DEFAULT_VALUE = 60;

  public Country country;
  public int minAge;
  public int maxAge;
  public boolean isFeign;

  public GenerateCustomerDTO(final Country country, final int minAge, final int maxAge,
      final boolean isFeign) {
    this.country = country;
    this.minAge = minAge;
    this.maxAge = maxAge;
    this.isFeign = isFeign;
  }

  public GenerateCustomerDTO(final Country country, final int minAge, final int maxAge) {
    this.country = country;
    this.minAge = minAge;
    this.maxAge = maxAge;
  }
}
