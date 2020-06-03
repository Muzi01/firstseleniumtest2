
package bindings.commons.automation.mule.model.mule.development.application.sfdc.channel;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude (JsonInclude.Include.NON_NULL)
@JsonPropertyOrder ({
    "benefits",
    "preferableDueDate",
    "dependantsInHousehold",
    "otherLoans",
    "spouseSsn",
    "loanType",
    "loans",
    "yearsAtAddress",
    "beneficialOwner",
    "livingCosts",
    "housingType",
    "creditCardOwner",
    "creditBureauQuestion4Answer",
    "isCarOwned",
    "housingCosts",
    "creditBureauQuestion1Answer",
    "activityType",
    "personalIDNumber",
    "martialStatus",
    "otherAddress",
    "registrySalary",
    "spouseSalary",
    "registrySpouseSalary",
    "spouseTotalMonthlyObligations",
    "registrySpouseTotalMonthlyObligations",
    "registryTotalMonthlyObligations"
})
public class OtherData {

  @JsonProperty ("benefits")
  private String benefits;
  @JsonProperty ("preferableDueDate")
  private Integer preferableDueDate;
  @JsonProperty ("dependantsInHousehold")
  private Integer dependantsInHousehold;
  @JsonProperty ("otherLoans")
  private String otherLoans;
  @JsonProperty ("spouseSsn")
  private String spouseSsn;
  @JsonProperty ("loanType")
  private LoanType loanType;
  @JsonProperty ("loans")
  private Loans loans;
  @JsonProperty ("yearsAtAddress")
  private String yearsAtAddress;
  @JsonProperty ("beneficialOwner")
  private String beneficialOwner;
  @JsonProperty ("livingCosts")
  private String livingCosts;
  @JsonProperty ("housingType")
  private String housingType;
  @JsonProperty ("creditCardOwner")
  private Boolean creditCardOwner;
  @JsonProperty ("creditBureauQuestion4Answer")
  private String creditBureauQuestion4Answer;
  @JsonProperty ("isCarOwned")
  private Boolean isCarOwned;
  @JsonProperty ("housingCosts")
  private String housingCosts;
  @JsonProperty ("creditBureauQuestion1Answer")
  private Boolean creditBureauQuestion1Answer;
  @JsonProperty ("activityType")
  private String activityType;
  @JsonProperty ("personalIDNumber")
  private String personalIDNumber;
  @JsonProperty ("martialStatus")
  private String martialStatus;
  @JsonProperty ("otherAddress")
  private String otherAddress;
  @JsonProperty ("registrySalary")
  private Double registrySalary;
  @JsonProperty ("spouseSalary")
  private Double spouseSalary;
  @JsonProperty ("registrySpouseSalary")
  private Double registrySpouseSalary;
  @JsonProperty ("spouseTotalMonthlyObligations")
  private Double spouseTotalMonthlyObligations;
  @JsonProperty ("registrySpouseTotalMonthlyObligations")
  private Double registrySpouseTotalMonthlyObligations;
  @JsonProperty ("registryTotalMonthlyObligations")
  private Double registryTotalMonthlyObligations;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("benefits")
  public String getBenefits() {
    return this.benefits;
  }

  @JsonProperty ("benefits")
  public void setBenefits(final String benefits) {
    this.benefits = benefits;
  }

  @JsonProperty ("preferableDueDate")
  public Integer getPreferableDueDate() {
    return this.preferableDueDate;
  }

  @JsonProperty ("preferableDueDate")
  public void setPreferableDueDate(final Integer preferableDueDate) {
    this.preferableDueDate = preferableDueDate;
  }

  @JsonProperty ("dependantsInHousehold")
  public Integer getDependantsInHousehold() {
    return this.dependantsInHousehold;
  }

  @JsonProperty ("dependantsInHousehold")
  public void setDependantsInHousehold(final Integer dependantsInHousehold) {
    this.dependantsInHousehold = dependantsInHousehold;
  }

  @JsonProperty ("otherLoans")
  public String getOtherLoans() {
    return this.otherLoans;
  }

  @JsonProperty ("otherLoans")
  public void setOtherLoans(final String otherLoans) {
    this.otherLoans = otherLoans;
  }

  @JsonProperty ("spouseSsn")
  public String getSpouseSsn() {
    return this.spouseSsn;
  }

  @JsonProperty ("spouseSsn")
  public void setSpouseSsn(final String spouseSsn) {
    this.spouseSsn = spouseSsn;
  }

  @JsonProperty ("loanType")
  public LoanType getLoanType() {
    return this.loanType;
  }

  @JsonProperty ("loanType")
  public void setLoanType(final LoanType loanType) {
    this.loanType = loanType;
  }

  @JsonProperty ("loans")
  public Loans getLoans() {
    return this.loans;
  }

  @JsonProperty ("loans")
  public void setLoans(final Loans loans) {
    this.loans = loans;
  }

  @JsonProperty ("yearsAtAddress")
  public String getYearsAtAddress() {
    return this.yearsAtAddress;
  }

  @JsonProperty ("yearsAtAddress")
  public void setYearsAtAddress(final String yearsAtAddress) {
    this.yearsAtAddress = yearsAtAddress;
  }

  @JsonProperty ("beneficialOwner")
  public String getBeneficialOwner() {
    return this.beneficialOwner;
  }

  @JsonProperty ("beneficialOwner")
  public void setBeneficialOwner(final String beneficialOwner) {
    this.beneficialOwner = beneficialOwner;
  }

  @JsonProperty ("livingCosts")
  public String getLivingCosts() {
    return this.livingCosts;
  }

  @JsonProperty ("livingCosts")
  public void setLivingCosts(final String livingCosts) {
    this.livingCosts = livingCosts;
  }

  @JsonProperty ("housingType")
  public String getHousingType() {
    return this.housingType;
  }

  @JsonProperty ("housingType")
  public void setHousingType(final String housingType) {
    this.housingType = housingType;
  }

  @JsonProperty ("creditCardOwner")
  public Boolean getCreditCardOwner() {
    return this.creditCardOwner;
  }

  @JsonProperty ("creditCardOwner")
  public void setCreditCardOwner(final Boolean creditCardOwner) {
    this.creditCardOwner = creditCardOwner;
  }

  @JsonProperty ("creditBureauQuestion4Answer")
  public String getCreditBureauQuestion4Answer() {
    return this.creditBureauQuestion4Answer;
  }

  @JsonProperty ("creditBureauQuestion4Answer")
  public void setCreditBureauQuestion4Answer(final String creditBureauQuestion4Answer) {
    this.creditBureauQuestion4Answer = creditBureauQuestion4Answer;
  }

  @JsonProperty ("isCarOwned")
  public Boolean getIsCarOwned() {
    return this.isCarOwned;
  }

  @JsonProperty ("isCarOwned")
  public void setIsCarOwned(final Boolean isCarOwned) {
    this.isCarOwned = isCarOwned;
  }

  @JsonProperty ("housingCosts")
  public String getHousingCosts() {
    return this.housingCosts;
  }

  @JsonProperty ("housingCosts")
  public void setHousingCosts(final String housingCosts) {
    this.housingCosts = housingCosts;
  }

  @JsonProperty ("creditBureauQuestion1Answer")
  public Boolean getCreditBureauQuestion1Answer() {
    return this.creditBureauQuestion1Answer;
  }

  @JsonProperty ("creditBureauQuestion1Answer")
  public void setCreditBureauQuestion1Answer(final Boolean creditBureauQuestion1Answer) {
    this.creditBureauQuestion1Answer = creditBureauQuestion1Answer;
  }

  @JsonProperty ("activityType")
  public String getActivityType() {
    return this.activityType;
  }

  @JsonProperty ("activityType")
  public void setActivityType(final String activityType) {
    this.activityType = activityType;
  }

  @JsonProperty ("personalIDNumber")
  public String getPersonalIDNumber() {
    return this.personalIDNumber;
  }

  @JsonProperty ("personalIDNumber")
  public void setPersonalIDNumber(final String personalIDNumber) {
    this.personalIDNumber = personalIDNumber;
  }

  @JsonProperty ("martialStatus")
  public String getMartialStatus() {
    return this.martialStatus;
  }

  @JsonProperty ("martialStatus")
  public void setMartialStatus(final String martialStatus) {
    this.martialStatus = martialStatus;
  }

  @JsonProperty ("otherAddress")
  public String getOtherAddress() {
    return this.otherAddress;
  }

  @JsonProperty ("otherAddress")
  public void setOtherAddress(final String otherAddress) {
    this.otherAddress = otherAddress;
  }

  @JsonProperty ("registrySalary")
  public Double getRegistrySalary() {
    return this.registrySalary;
  }

  @JsonProperty ("registrySalary")
  public void setRegistrySalary(final Double registrySalary) {
    this.registrySalary = registrySalary;
  }

  @JsonProperty ("spouseSalary")
  public Double getSpouseSalary() {
    return this.spouseSalary;
  }

  @JsonProperty ("spouseSalary")
  public void setSpouseSalary(final Double spouseSalary) {
    this.spouseSalary = spouseSalary;
  }

  @JsonProperty ("registrySpouseSalary")
  public Double getRegistrySpouseSalary() {
    return this.registrySpouseSalary;
  }

  @JsonProperty ("registrySpouseSalary")
  public void setRegistrySpouseSalary(final Double registrySpouseSalary) {
    this.registrySpouseSalary = registrySpouseSalary;
  }

  @JsonProperty ("spouseTotalMonthlyObligations")
  public Double getSpouseTotalMonthlyObligations() {
    return this.spouseTotalMonthlyObligations;
  }

  @JsonProperty ("spouseTotalMonthlyObligations")
  public void setSpouseTotalMonthlyObligations(final Double spouseTotalMonthlyObligations) {
    this.spouseTotalMonthlyObligations = spouseTotalMonthlyObligations;
  }

  @JsonProperty ("registrySpouseTotalMonthlyObligations")
  public Double getRegistrySpouseTotalMonthlyObligations() {
    return this.registrySpouseTotalMonthlyObligations;
  }

  @JsonProperty ("registrySpouseTotalMonthlyObligations")
  public void setRegistrySpouseTotalMonthlyObligations(
      final Double registrySpouseTotalMonthlyObligations) {
    this.registrySpouseTotalMonthlyObligations = registrySpouseTotalMonthlyObligations;
  }

  @JsonProperty ("registryTotalMonthlyObligations")
  public Double getRegistryTotalMonthlyObligations() {
    return this.registryTotalMonthlyObligations;
  }

  @JsonProperty ("registryTotalMonthlyObligations")
  public void setRegistryTotalMonthlyObligations(final Double registryTotalMonthlyObligations) {
    this.registryTotalMonthlyObligations = registryTotalMonthlyObligations;
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
    return new HashCodeBuilder().append(this.benefits).append(this.preferableDueDate)
        .append(this.dependantsInHousehold).append(this.otherLoans).append(this.spouseSsn).append(
            this.loanType)
        .append(this.loans).append(this.yearsAtAddress).append(this.beneficialOwner).append(
            this.livingCosts)
        .append(this.housingType).append(this.creditCardOwner).append(
            this.creditBureauQuestion4Answer)
        .append(this.isCarOwned).append(this.housingCosts).append(this.creditBureauQuestion1Answer)
        .append(this.activityType).append(this.personalIDNumber).append(this.martialStatus).append(
            this.otherAddress)
        .append(this.registrySalary).append(this.spouseSalary).append(this.registrySpouseSalary)
        .append(this.spouseTotalMonthlyObligations).append(
            this.registrySpouseTotalMonthlyObligations)
        .append(this.registryTotalMonthlyObligations).append(this.additionalProperties)
        .toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof OtherData)) {
      return false;
    }
    final OtherData rhs = ((OtherData) other);
    return new EqualsBuilder().append(this.benefits, rhs.benefits)
        .append(this.preferableDueDate, rhs.preferableDueDate)
        .append(this.dependantsInHousehold, rhs.dependantsInHousehold)
        .append(this.otherLoans, rhs.otherLoans)
        .append(this.spouseSsn, rhs.spouseSsn).append(this.loanType, rhs.loanType).append(
            this.loans, rhs.loans)
        .append(this.yearsAtAddress, rhs.yearsAtAddress)
        .append(this.beneficialOwner, rhs.beneficialOwner)
        .append(this.livingCosts, rhs.livingCosts).append(this.housingType, rhs.housingType)
        .append(this.creditCardOwner, rhs.creditCardOwner)
        .append(this.creditBureauQuestion4Answer, rhs.creditBureauQuestion4Answer)
        .append(this.isCarOwned, rhs.isCarOwned).append(this.housingCosts, rhs.housingCosts)
        .append(this.creditBureauQuestion1Answer, rhs.creditBureauQuestion1Answer)
        .append(this.activityType, rhs.activityType)
        .append(this.personalIDNumber, rhs.personalIDNumber)
        .append(this.martialStatus, rhs.martialStatus).append(this.otherAddress, rhs.otherAddress)
        .append(this.registrySalary, rhs.registrySalary).append(this.spouseSalary, rhs.spouseSalary)
        .append(this.registrySpouseSalary, rhs.registrySpouseSalary)
        .append(this.spouseTotalMonthlyObligations, rhs.spouseTotalMonthlyObligations)
        .append(this.registrySpouseTotalMonthlyObligations,
            rhs.registrySpouseTotalMonthlyObligations)
        .append(this.registryTotalMonthlyObligations, rhs.registryTotalMonthlyObligations)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

  public enum LoanType {

    PERSONAL_LOAN("PERSONAL_LOAN"),
    FAMILY_LOAN("FAMILY_LOAN");
    private final String value;
    private static final Map<String, LoanType> CONSTANTS = new HashMap<>();

    static {
      for (final LoanType c : values()) {
        CONSTANTS.put(c.value, c);
      }
    }

    private LoanType(final String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.value;
    }

    @JsonValue
    public String value() {
      return this.value;
    }

    @JsonCreator
    public static LoanType fromValue(final String value) {
      final LoanType constant = CONSTANTS.get(value);
      if (constant == null) {
        throw new IllegalArgumentException(value);
      } else {
        return constant;
      }
    }

  }

}
