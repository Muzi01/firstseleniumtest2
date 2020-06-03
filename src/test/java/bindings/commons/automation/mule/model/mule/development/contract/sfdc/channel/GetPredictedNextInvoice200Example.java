
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
    "success",
    "reason",
    "body"
})
public class GetPredictedNextInvoice200Example {

  @JsonProperty ("success")
  private Boolean success;
  @JsonProperty ("reason")
  private String reason;
  @JsonProperty ("body")
  private BodyGetPredictedNextInvoice200 body;
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

  @JsonProperty ("reason")
  public String getReason() {
    return this.reason;
  }

  @JsonProperty ("reason")
  public void setReason(final String reason) {
    this.reason = reason;
  }

  @JsonProperty ("body")
  public BodyGetPredictedNextInvoice200 getBody() {
    return this.body;
  }

  @JsonProperty ("body")
  public void setBody(final BodyGetPredictedNextInvoice200 body) {
    this.body = body;
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
    return new HashCodeBuilder().append(this.success).append(this.reason).append(this.body)
        .append(this.additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof GetPredictedNextInvoice200Example)) {
      return false;
    }
    final GetPredictedNextInvoice200Example rhs = ((GetPredictedNextInvoice200Example) other);
    return new EqualsBuilder().append(this.success, rhs.success).append(this.reason, rhs.reason)
        .append(this.body, rhs.body).append(this.additionalProperties, rhs.additionalProperties)
        .isEquals();
  }

}
