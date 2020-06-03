package bindings.commons.automation.aio.rest.finishfinancialdata;

public enum EducationFI {
  UNIVERSITY(10),
  POLYTECHNIC(5),
  HIGH(5),
  VOCATIONAL(-3),
  MIDDLE(-3);

  protected Integer value;

  EducationFI(final Integer value) {
    this.value = value;
  }
}
