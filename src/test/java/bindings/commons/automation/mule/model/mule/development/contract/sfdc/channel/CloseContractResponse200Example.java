
package bindings.commons.automation.mule.model.mule.development.contract.sfdc.channel;

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
    "creditMemoAmount"
})
public class CloseContractResponse200Example {

  @JsonProperty ("creditMemoAmount")
  private String creditMemoAmount;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("creditMemoAmount")
  public String getCreditMemoAmount() {
    return this.creditMemoAmount;
  }

  @JsonProperty ("creditMemoAmount")
  public void setCreditMemoAmount(final String creditMemoAmount) {
    this.creditMemoAmount = creditMemoAmount;
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
    return new HashCodeBuilder().append(this.creditMemoAmount).append(this.additionalProperties)
        .toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof CloseContractResponse200Example)) {
      return false;
    }
    final CloseContractResponse200Example rhs = ((CloseContractResponse200Example) other);
    return new EqualsBuilder().append(this.creditMemoAmount, rhs.creditMemoAmount)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
