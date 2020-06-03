
package bindings.commons.automation.mule.model.mule.development.application.sfdc.channel;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude (JsonInclude.Include.NON_NULL)
@JsonPropertyOrder ({
    "timeAtEmployment",
    "totalNetIncome",
    "education",
    "occupation",
    "employmentType",
    "timeAtBank",
    "companyName",
    "salaryPaymentDay",
    "incomeTerminationDate",
    "industry",
    "monthlyFinancialExpenses",
    "salary",
    "otherIncome",
    "monthlyObligations",
    "livingCostsAmount",
    "pension"
})
public class EducationAndEmployment {

  @JsonProperty ("timeAtEmployment")
  private String timeAtEmployment;
  @JsonProperty ("totalNetIncome")
  private Integer totalNetIncome;
  @JsonProperty ("education")
  private String education;
  @JsonProperty ("occupation")
  private String occupation;
  @JsonProperty ("employmentType")
  private String employmentType;
  @JsonProperty ("timeAtBank")
  private String timeAtBank;
  @JsonProperty ("companyName")
  private String companyName;
  @JsonProperty ("salaryPaymentDay")
  private Integer salaryPaymentDay;
  @JsonProperty ("incomeTerminationDate")
  private String incomeTerminationDate;
  @JsonProperty ("industry")
  private String industry;
  @JsonProperty ("monthlyFinancialExpenses")
  private String monthlyFinancialExpenses;
  @JsonProperty ("salary")
  private String salary;
  @JsonProperty ("otherIncome")
  private String otherIncome;
  @JsonProperty ("monthlyObligations")
  private String monthlyObligations;
  @JsonProperty ("livingCostsAmount")
  private String livingCostsAmount;
  @JsonProperty ("pension")
  private String pension;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("timeAtEmployment")
  public String getTimeAtEmployment() {
    return this.timeAtEmployment;
  }

  @JsonProperty ("timeAtEmployment")
  public void setTimeAtEmployment(final String timeAtEmployment) {
    this.timeAtEmployment = timeAtEmployment;
  }

  @JsonProperty ("totalNetIncome")
  public Integer getTotalNetIncome() {
    return this.totalNetIncome;
  }

  @JsonProperty ("totalNetIncome")
  public void setTotalNetIncome(final Integer totalNetIncome) {
    this.totalNetIncome = totalNetIncome;
  }

  @JsonProperty ("education")
  public String getEducation() {
    return this.education;
  }

  @JsonProperty ("education")
  public void setEducation(final String education) {
    this.education = education;
  }

  @JsonProperty ("occupation")
  public String getOccupation() {
    return this.occupation;
  }

  @JsonProperty ("occupation")
  public void setOccupation(final String occupation) {
    this.occupation = occupation;
  }

  @JsonProperty ("employmentType")
  public String getEmploymentType() {
    return this.employmentType;
  }

  @JsonProperty ("employmentType")
  public void setEmploymentType(final String employmentType) {
    this.employmentType = employmentType;
  }

  @JsonProperty ("timeAtBank")
  public String getTimeAtBank() {
    return this.timeAtBank;
  }

  @JsonProperty ("timeAtBank")
  public void setTimeAtBank(final String timeAtBank) {
    this.timeAtBank = timeAtBank;
  }

  @JsonProperty ("companyName")
  public String getCompanyName() {
    return this.companyName;
  }

  @JsonProperty ("companyName")
  public void setCompanyName(final String companyName) {
    this.companyName = companyName;
  }

  @JsonProperty ("salaryPaymentDay")
  public Integer getSalaryPaymentDay() {
    return this.salaryPaymentDay;
  }

  @JsonProperty ("salaryPaymentDay")
  public void setSalaryPaymentDay(final Integer salaryPaymentDay) {
    this.salaryPaymentDay = salaryPaymentDay;
  }

  @JsonProperty ("incomeTerminationDate")
  public String getIncomeTerminationDate() {
    return this.incomeTerminationDate;
  }

  @JsonProperty ("incomeTerminationDate")
  public void setIncomeTerminationDate(final String incomeTerminationDate) {
    this.incomeTerminationDate = incomeTerminationDate;
  }

  @JsonProperty ("industry")
  public String getIndustry() {
    return this.industry;
  }

  @JsonProperty ("industry")
  public void setIndustry(final String industry) {
    this.industry = industry;
  }

  @JsonProperty ("monthlyFinancialExpenses")
  public String getMonthlyFinancialExpenses() {
    return this.monthlyFinancialExpenses;
  }

  @JsonProperty ("monthlyFinancialExpenses")
  public void setMonthlyFinancialExpenses(final String monthlyFinancialExpenses) {
    this.monthlyFinancialExpenses = monthlyFinancialExpenses;
  }

  @JsonProperty ("salary")
  public String getSalary() {
    return this.salary;
  }

  @JsonProperty ("salary")
  public void setSalary(final String salary) {
    this.salary = salary;
  }

  @JsonProperty ("otherIncome")
  public String getOtherIncome() {
    return this.otherIncome;
  }

  @JsonProperty ("otherIncome")
  public void setOtherIncome(final String otherIncome) {
    this.otherIncome = otherIncome;
  }

  @JsonProperty ("monthlyObligations")
  public String getMonthlyObligations() {
    return this.monthlyObligations;
  }

  @JsonProperty ("monthlyObligations")
  public void setMonthlyObligations(final String monthlyObligations) {
    this.monthlyObligations = monthlyObligations;
  }

  @JsonProperty ("livingCostsAmount")
  public String getLivingCostsAmount() {
    return this.livingCostsAmount;
  }

  @JsonProperty ("livingCostsAmount")
  public void setLivingCostsAmount(final String livingCostsAmount) {
    this.livingCostsAmount = livingCostsAmount;
  }

  @JsonProperty ("pension")
  public String getPension() {
    return this.pension;
  }

  @JsonProperty ("pension")
  public void setPension(final String pension) {
    this.pension = pension;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperty(final String name, final Object value) {
    this.additionalProperties.put(name, value);
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(this.timeAtEmployment).append(this.totalNetIncome).append(
        this.education)
        .append(this.occupation).append(this.employmentType).append(this.timeAtBank).append(
            this.companyName)
        .append(this.salaryPaymentDay).append(this.incomeTerminationDate).append(this.industry)
        .append(this.monthlyFinancialExpenses).append(this.salary).append(this.otherIncome)
        .append(this.monthlyObligations).append(this.livingCostsAmount).append(this.pension)
        .append(this.additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof EducationAndEmployment)) {
      return false;
    }
    final EducationAndEmployment rhs = ((EducationAndEmployment) other);
    return new EqualsBuilder().append(this.timeAtEmployment, rhs.timeAtEmployment)
        .append(this.totalNetIncome, rhs.totalNetIncome).append(this.education, rhs.education)
        .append(this.occupation, rhs.occupation).append(this.employmentType, rhs.employmentType)
        .append(this.timeAtBank, rhs.timeAtBank).append(this.companyName, rhs.companyName)
        .append(this.salaryPaymentDay, rhs.salaryPaymentDay)
        .append(this.incomeTerminationDate, rhs.incomeTerminationDate)
        .append(this.industry, rhs.industry)
        .append(this.monthlyFinancialExpenses, rhs.monthlyFinancialExpenses)
        .append(this.salary, rhs.salary)
        .append(this.otherIncome, rhs.otherIncome)
        .append(this.monthlyObligations, rhs.monthlyObligations)
        .append(this.livingCostsAmount, rhs.livingCostsAmount).append(this.pension, rhs.pension)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
