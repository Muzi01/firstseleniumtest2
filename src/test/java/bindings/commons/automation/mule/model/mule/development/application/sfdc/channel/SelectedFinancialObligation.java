
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
    "externalFinancialObligationId",
    "company",
    "bankAccountNumber",
    "selected"
})
public class SelectedFinancialObligation {

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("externalFinancialObligationId")
  private String externalFinancialObligationId;
  @JsonProperty ("company")
  private String company;
  @JsonProperty ("bankAccountNumber")
  private String bankAccountNumber;
  @JsonProperty ("selected")
  private Boolean selected;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("externalFinancialObligationId")
  public String getExternalFinancialObligationId() {
    return this.externalFinancialObligationId;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("externalFinancialObligationId")
  public void setExternalFinancialObligationId(final String externalFinancialObligationId) {
    this.externalFinancialObligationId = externalFinancialObligationId;
  }

  @JsonProperty ("company")
  public String getCompany() {
    return this.company;
  }

  @JsonProperty ("company")
  public void setCompany(final String company) {
    this.company = company;
  }

  @JsonProperty ("bankAccountNumber")
  public String getBankAccountNumber() {
    return this.bankAccountNumber;
  }

  @JsonProperty ("bankAccountNumber")
  public void setBankAccountNumber(final String bankAccountNumber) {
    this.bankAccountNumber = bankAccountNumber;
  }

  @JsonProperty ("selected")
  public Boolean getSelected() {
    return this.selected;
  }

  @JsonProperty ("selected")
  public void setSelected(final Boolean selected) {
    this.selected = selected;
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
    return new HashCodeBuilder().append(this.externalFinancialObligationId).append(this.company)
        .append(this.bankAccountNumber).append(this.selected).append(this.additionalProperties)
        .toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof SelectedFinancialObligation)) {
      return false;
    }
    final SelectedFinancialObligation rhs = ((SelectedFinancialObligation) other);
    return new EqualsBuilder()
        .append(this.externalFinancialObligationId, rhs.externalFinancialObligationId)
        .append(this.company, rhs.company).append(this.bankAccountNumber, rhs.bankAccountNumber)
        .append(this.selected, rhs.selected)
        .append(this.additionalProperties, rhs.additionalProperties)
        .isEquals();
  }

}
