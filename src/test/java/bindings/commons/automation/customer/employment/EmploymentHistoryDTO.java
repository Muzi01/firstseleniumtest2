package bindings.commons.automation.customer.employment;

public class EmploymentHistoryDTO {

  private StudiesLevel studiesLevel;
  private EmploymentType unemployed;
  private EmploymentDTO first;
  private EmploymentDTO second;

  public EmploymentHistoryDTO(
      final StudiesLevel studiesLevel, final EmploymentType unemployed,
      final EmploymentDTO first,
      final EmploymentDTO second) {
    this.studiesLevel = studiesLevel;
    this.unemployed = unemployed;
    this.first = first;
    this.second = second;
  }

  public StudiesLevel getStudiesLevel() {
    return studiesLevel;
  }

  public void setStudiesLevel(final StudiesLevel studiesLevel) {
    this.studiesLevel = studiesLevel;
  }

  public EmploymentType getUnemployed() {
    return unemployed;
  }

  public void setUnemployed(final EmploymentType unemployed) {
    this.unemployed = unemployed;
  }

  public EmploymentDTO getFirst() {
    return first;
  }

  public void setFirst(final EmploymentDTO first) {
    this.first = first;
  }

  public EmploymentDTO getSecond() {
    return second;
  }

  public void setSecond(final EmploymentDTO second) {
    this.second = second;
  }
}
