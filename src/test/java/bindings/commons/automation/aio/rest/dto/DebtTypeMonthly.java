package bindings.commons.automation.aio.rest.dto;

public enum DebtTypeMonthly {
  HOME_LOAN(19),
  LEASING_HIRE_PURCHASE(10),
  FASTLOAN(10),
  CONSUMER_LOAN(10),
  CAR_LEASING(10),
  STUDENT_LOAN(10),
  OVERDRAFT(10),
  CREDIT_CARD(10),
  GUARANTEE_SURETY(10),
  OTHER(10);

  protected Integer value;

  DebtTypeMonthly(final Integer value) {
    this.value = value;
  }
}
