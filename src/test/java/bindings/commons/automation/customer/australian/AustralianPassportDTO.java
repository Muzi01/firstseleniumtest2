package bindings.commons.automation.customer.australian;

public class AustralianPassportDTO {

  private String passportNumber;

  public AustralianPassportDTO(final String passportNumber) {
    this.passportNumber = passportNumber;
  }

  public String getPassportNumber() {
    return passportNumber;
  }

  public void setPassportNumber(final String passportNumber) {
    this.passportNumber = passportNumber;
  }
}
