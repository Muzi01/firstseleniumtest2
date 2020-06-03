
package bindings.commons.automation.mule.model.mule.development.extraservices.aio.channel.deactivateextraservices.request;

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
    "contractId",
    "extraServiceName",
    "reason",
    "requestDate"
})
public class Body {

  @JsonProperty ("contractId")
  private String contractId;
  @JsonProperty ("extraServiceName")
  private String extraServiceName;
  @JsonProperty ("reason")
  private String reason;
  @JsonProperty ("requestDate")
  private String requestDate;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("contractId")
  public String getContractId() {
    return this.contractId;
  }

  @JsonProperty ("contractId")
  public void setContractId(final String contractId) {
    this.contractId = contractId;
  }

  @JsonProperty ("extraServiceName")
  public String getExtraServiceName() {
    return this.extraServiceName;
  }

  @JsonProperty ("extraServiceName")
  public void setExtraServiceName(final String extraServiceName) {
    this.extraServiceName = extraServiceName;
  }

  @JsonProperty ("reason")
  public String getReason() {
    return this.reason;
  }

  @JsonProperty ("reason")
  public void setReason(final String reason) {
    this.reason = reason;
  }

  @JsonProperty ("requestDate")
  public String getRequestDate() {
    return this.requestDate;
  }

  @JsonProperty ("requestDate")
  public void setRequestDate(final String requestDate) {
    this.requestDate = requestDate;
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
    return new HashCodeBuilder().append(this.contractId).append(this.extraServiceName).append(
        this.reason)
        .append(this.requestDate).append(this.additionalProperties).toHashCode();
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
    return new EqualsBuilder().append(this.contractId, rhs.contractId)
        .append(this.extraServiceName, rhs.extraServiceName).append(this.reason, rhs.reason)
        .append(this.requestDate, rhs.requestDate)
        .append(this.additionalProperties, rhs.additionalProperties)
        .isEquals();
  }

}
