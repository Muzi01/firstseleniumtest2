package bindings.commons.automation.customer.australian;

/**
 * Covers health care in Australia
 */

public class MedicareDTO {

  private int irn;
  private long cardNumber;
  private String expiryDate;

  public MedicareDTO(final int irn, final long cardNumber, final String expiryDate) {
    this.irn = irn;
    this.cardNumber = cardNumber;
    this.expiryDate = expiryDate;
  }

  public int getIrn() {
    return irn;
  }

  public void setIrn(final int irn) {
    this.irn = irn;
  }

  public long getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(final long cardNumber) {
    this.cardNumber = cardNumber;
  }

  public String getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(final String expiryDate) {
    this.expiryDate = expiryDate;
  }
}
