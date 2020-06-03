
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
    "success",
    "result",
    "customerId"
})
public class ConfirmVerificationSmsResponseExample {

  @JsonProperty ("success")
  private Boolean success;
  @JsonProperty ("result")
  private String result;
  @JsonProperty ("customerId")
  private String customerId;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("success")
  public Boolean getSuccess() {
    return this.success;
  }

  @JsonProperty ("success")
  public void setSuccess(final Boolean success) {
    this.success = success;
  }

  @JsonProperty ("result")
  public String getResult() {
    return this.result;
  }

  @JsonProperty ("result")
  public void setResult(final String result) {
    this.result = result;
  }

  @JsonProperty ("customerId")
  public String getCustomerId() {
    return this.customerId;
  }

  @JsonProperty ("customerId")
  public void setCustomerId(final String customerId) {
    this.customerId = customerId;
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
    return new HashCodeBuilder().append(this.success).append(this.result).append(this.customerId)
        .append(this.additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof ConfirmVerificationSmsResponseExample)) {
      return false;
    }
    final ConfirmVerificationSmsResponseExample rhs =
        ((ConfirmVerificationSmsResponseExample) other);
    return new EqualsBuilder()
        .append(this.success, rhs.success)
        .append(this.result, rhs.result)
        .append(this.customerId, rhs.customerId)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
