package bindings.commons.automation.aio.rest.finishfinancialdata;

public enum OriginalPhoneOperatorScoreFI {
  TELIA_POST_PAID(40, 8),
  DNA_PRE_PAID(41, 6),
  UNDEFINED(44, 5),
  DNA_POST_PAID(45, 3),
  SAUNALAHTI_PRE_PARID(46, -6),
  ELISTA_POST_PAID(50, 14),
  OTHER_MISSING(0, -16);

  protected Integer phoneOperatorDigits;
  protected Integer scoreValue;

  OriginalPhoneOperatorScoreFI(final int phoneOperatorDigits, final int scoreValue) {
    this.phoneOperatorDigits = phoneOperatorDigits;
    this.scoreValue = scoreValue;
  }
}
