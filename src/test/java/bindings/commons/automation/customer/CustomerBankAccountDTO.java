package bindings.commons.automation.customer;

public class CustomerBankAccountDTO {

  private String number;
  private String shortNumber;

  public CustomerBankAccountDTO(final String number, final String shortNumber) {
    this.number = number;
    this.shortNumber = shortNumber;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(final String number) {
    this.number = number;
  }

  public String getShortNumber() {
    return shortNumber;
  }

  public void setShortNumber(final String shortNumber) {
    this.shortNumber = shortNumber;
  }
}
