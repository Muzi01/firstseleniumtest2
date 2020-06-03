
package bindings.commons.automation.mule.model.mule.development.case1.sfdc.channel;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
    "firstDueDate",
    "selectedFinancialObligations"
})
public class ProductOfferRequest {

  @JsonProperty ("countryCode")
  private String countryCode;
  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("applicationId")
  private String applicationId;
  @JsonProperty ("firstDueDate")
  private String firstDueDate;
  @JsonProperty ("selectedFinancialObligations")
  private List<SelectedFinancialObligation> selectedFinancialObligations = new ArrayList<>();
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("countryCode")
  public String getCountryCode() {
    return this.countryCode;
  }

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

  @JsonProperty ("firstDueDate")
  public String getFirstDueDate() {
    return this.firstDueDate;
  }

  @JsonProperty ("firstDueDate")
  public void setFirstDueDate(final String firstDueDate) {
    this.firstDueDate = firstDueDate;
  }

  @JsonProperty ("selectedFinancialObligations")
  public List<SelectedFinancialObligation> getSelectedFinancialObligations() {
    return this.selectedFinancialObligations;
  }

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
        this.firstDueDate)
        .append(this.selectedFinancialObligations).append(this.additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof ProductOfferRequest)) {
      return false;
    }
    final ProductOfferRequest request = ((ProductOfferRequest) other);
    return new EqualsBuilder().append(this.countryCode, request.countryCode)
        .append(this.applicationId, request.applicationId)
        .append(this.firstDueDate, request.firstDueDate)
        .append(this.selectedFinancialObligations, request.selectedFinancialObligations)
        .append(this.additionalProperties, request.additionalProperties).isEquals();
  }

}
