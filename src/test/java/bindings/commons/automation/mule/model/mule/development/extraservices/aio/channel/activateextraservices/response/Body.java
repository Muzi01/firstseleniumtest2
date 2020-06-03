
package bindings.commons.automation.mule.model.mule.development.extraservices.aio.channel.activateextraservices.response;

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
    "status",
    "errorMessage"
})
public class Body {

  @JsonProperty ("status")
  private String status;
  @JsonProperty ("errorMessage")
  private String errorMessage;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("status")
  public String getStatus() {
    return this.status;
  }

  @JsonProperty ("status")
  public void setStatus(final String status) {
    this.status = status;
  }

  @JsonProperty ("errorMessage")
  public String getErrorMessage() {
    return this.errorMessage;
  }

  @JsonProperty ("errorMessage")
  public void setErrorMessage(final String errorMessage) {
    this.errorMessage = errorMessage;
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
    return new HashCodeBuilder().append(this.status).append(this.errorMessage).append(
        this.additionalProperties)
        .toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof Body)) {
      return false;
    }
    final Body rhs = ((Body) other);
    return new EqualsBuilder().append(this.status, rhs.status)
        .append(this.errorMessage, rhs.errorMessage)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
