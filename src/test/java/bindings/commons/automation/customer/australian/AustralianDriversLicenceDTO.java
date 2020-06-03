package bindings.commons.automation.customer.australian;

import com.ipfdigital.automation.customer.State;

public class AustralianDriversLicenceDTO {

  private State state;
  private String expiryDate;
  private String licenceNumber;

  public AustralianDriversLicenceDTO(final State state, final String expiryDate,
      final String licenceNumber) {
    this.state = state;
    this.expiryDate = expiryDate;
    this.licenceNumber = licenceNumber;
  }

  public State getState() {
    return state;
  }

  public String getExpiryDate() {
    return expiryDate;
  }

  public String getLicenceNumber() {
    return licenceNumber;
  }

  public void setState(final State state) {
    this.state = state;
  }

  public void setExpiryDate(final String expiryDate) {
    this.expiryDate = expiryDate;
  }

  public void setLicenceNumber(final String licenceNumber) {
    this.licenceNumber = licenceNumber;
  }
}
