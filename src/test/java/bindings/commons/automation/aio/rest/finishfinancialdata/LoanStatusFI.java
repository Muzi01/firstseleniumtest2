package bindings.commons.automation.aio.rest.finishfinancialdata;

public enum LoanStatusFI {
  MISSING(-2, -3, 11),
  TRUE(9, 12, -5);

  protected Integer homeLoan;
  protected Integer studentLoan;
  protected Integer smallLoan;

  LoanStatusFI(final Integer homeLoan, final Integer studentLoan, final Integer smallLoan) {
    this.homeLoan = homeLoan;
    this.studentLoan = studentLoan;
    this.smallLoan = smallLoan;
  }
}
