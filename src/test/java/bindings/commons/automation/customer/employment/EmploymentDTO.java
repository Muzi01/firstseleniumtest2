package bindings.commons.automation.customer.employment;

public class EmploymentDTO {

  public String employerName;
  public String jobTitle;
  public EmploymentType employmentType;
  public JobDuration jobDuration;

  public EmploymentDTO(final String employerName, final String jobTitle,
      final EmploymentType employmentType,
      final JobDuration jobDuration) {
    this.employerName = employerName;
    this.jobTitle = jobTitle;
    this.employmentType = employmentType;
    this.jobDuration = jobDuration;
  }

  public String getEmployerName() {
    return employerName;
  }

  public void setEmployerName(final String employerName) {
    this.employerName = employerName;
  }

  public String getJobTitle() {
    return jobTitle;
  }

  public void setJobTitle(final String jobTitle) {
    this.jobTitle = jobTitle;
  }

  public EmploymentType getEmploymentType() {
    return employmentType;
  }

  public void setEmploymentType(final EmploymentType employmentType) {
    this.employmentType = employmentType;
  }

  public JobDuration getJobDuration() {
    return jobDuration;
  }

  public void setJobDuration(final JobDuration jobDuration) {
    this.jobDuration = jobDuration;
  }
}
