
package bindings.commons.automation.mule.model.mule.development.customer.sfdc.channel;

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
    "CreditMemoAmount",
    "ErrorMessage",
    "Status"
})
public class ContractCloseResponseExample {

  @JsonProperty ("CreditMemoAmount")
  private String creditMemoAmount;
  @JsonProperty ("ErrorMessage")
  private String errorMessage;
  @JsonProperty ("Status")
  private String status;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("CreditMemoAmount")
  public String getCreditMemoAmount() {
    return this.creditMemoAmount;
  }

  @JsonProperty ("CreditMemoAmount")
  public void setCreditMemoAmount(final String creditMemoAmount) {
    this.creditMemoAmount = creditMemoAmount;
  }

  @JsonProperty ("ErrorMessage")
  public String getErrorMessage() {
    return this.errorMessage;
  }

  @JsonProperty ("ErrorMessage")
  public void setErrorMessage(final String errorMessage) {
    this.errorMessage = errorMessage;
  }

  @JsonProperty ("Status")
  public String getStatus() {
    return this.status;
  }

  @JsonProperty ("Status")
  public void setStatus(final String status) {
    this.status = status;
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
    return new HashCodeBuilder()
        .append(this.creditMemoAmount)
        .append(this.errorMessage)
        .append(this.status)
        .append(this.additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof ContractCloseResponseExample)) {
      return false;
    }
    final ContractCloseResponseExample rhs = ((ContractCloseResponseExample) other);
    return new EqualsBuilder()
        .append(this.creditMemoAmount, rhs.creditMemoAmount)
        .append(this.errorMessage, rhs.errorMessage)
        .append(this.status, rhs.status)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
