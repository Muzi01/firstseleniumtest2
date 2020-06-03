
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude (JsonInclude.Include.NON_NULL)
@JsonPropertyOrder ({
    "countryCode",
    "applicationId",
    "productId",
    "loanType",
    "firstDueDate",
    "withSave",
    "firstDrawAmount",
    "selectedFinancialObligations"
})
public class CalculateApplicationRequest {

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("countryCode")
  private String countryCode;
  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("applicationId")
  private String applicationId;
  @JsonProperty ("productId")
  private String productId;
  @JsonProperty ("loanType")
  private LoanType loanType;
  @JsonProperty ("firstDueDate")
  private String firstDueDate;
  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("withSave")
  private Boolean withSave;
  @JsonProperty ("firstDrawAmount")
  private Double firstDrawAmount;
  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("selectedFinancialObligations")
  private List<SelectedFinancialObligation> selectedFinancialObligations = new ArrayList<>();
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("countryCode")
  public String getCountryCode() {
    return this.countryCode;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("countryCode")
  public void setCountryCode(final String countryCode) {
    this.countryCode = countryCode;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("applicationId")
  public String getApplicationId() {
    return this.applicationId;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("applicationId")
  public void setApplicationId(final String applicationId) {
    this.applicationId = applicationId;
  }

  @JsonProperty ("productId")
  public String getProductId() {
    return this.productId;
  }

  @JsonProperty ("productId")
  public void setProductId(final String productId) {
    this.productId = productId;
  }

  @JsonProperty ("loanType")
  public LoanType getLoanType() {
    return this.loanType;
  }

  @JsonProperty ("loanType")
  public void setLoanType(final LoanType loanType) {
    this.loanType = loanType;
  }

  @JsonProperty ("firstDueDate")
  public String getFirstDueDate() {
    return this.firstDueDate;
  }

  @JsonProperty ("firstDueDate")
  public void setFirstDueDate(final String firstDueDate) {
    this.firstDueDate = firstDueDate;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("withSave")
  public Boolean getWithSave() {
    return this.withSave;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("withSave")
  public void setWithSave(final Boolean withSave) {
    this.withSave = withSave;
  }

  @JsonProperty ("firstDrawAmount")
  public Double getFirstDrawAmount() {
    return this.firstDrawAmount;
  }

  @JsonProperty ("firstDrawAmount")
  public void setFirstDrawAmount(final Double firstDrawAmount) {
    this.firstDrawAmount = firstDrawAmount;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("selectedFinancialObligations")
  public List<SelectedFinancialObligation> getSelectedFinancialObligations() {
    return this.selectedFinancialObligations;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("selectedFinancialObligations")
  public void setSelectedFinancialObligations(
      final List<SelectedFinancialObligation> selectedFinancialObligations) {
    this.selectedFinancialObligations = selectedFinancialObligations;
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
    return new HashCodeBuilder().append(this.countryCode).append(this.applicationId).append(
        this.productId)
        .append(this.loanType).append(this.firstDueDate).append(this.withSave).append(
            this.firstDrawAmount)
        .append(this.selectedFinancialObligations).append(this.additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof CalculateApplicationRequest)) {
      return false;
    }
    final CalculateApplicationRequest rhs = ((CalculateApplicationRequest) other);
    return new EqualsBuilder().append(this.countryCode, rhs.countryCode)
        .append(this.applicationId, rhs.applicationId).append(this.productId, rhs.productId)
        .append(this.loanType, rhs.loanType).append(this.firstDueDate, rhs.firstDueDate)
        .append(this.withSave, rhs.withSave).append(this.firstDrawAmount, rhs.firstDrawAmount)
        .append(this.selectedFinancialObligations, rhs.selectedFinancialObligations)
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
