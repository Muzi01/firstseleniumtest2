
package bindings.commons.automation.mule.model.mule.development.cheque.sfdc.channel;

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
    "chequeId",
    "rejectionReason",
    "description"
})
public class ChequeCreate {

  @JsonProperty ("chequeId")
  private String chequeId;
  @JsonProperty ("rejectionReason")
  private String rejectionReason;
  @JsonProperty ("description")
  private String description;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("chequeId")
  public String getChequeId() {
    return this.chequeId;
  }

  @JsonProperty ("chequeId")
  public void setChequeId(final String chequeId) {
    this.chequeId = chequeId;
  }

  @JsonProperty ("rejectionReason")
  public String getRejectionReason() {
    return this.rejectionReason;
  }

  @JsonProperty ("rejectionReason")
  public void setRejectionReason(final String rejectionReason) {
    this.rejectionReason = rejectionReason;
  }

  @JsonProperty ("description")
  public String getDescription() {
    return this.description;
  }

  @JsonProperty ("description")
  public void setDescription(final String description) {
    this.description = description;
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
    return new HashCodeBuilder().append(this.chequeId).append(this.rejectionReason).append(
        this.description)
        .append(this.additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof ChequeCreate)) {
      return false;
    }
    final ChequeCreate rhs = ((ChequeCreate) other);
    return new EqualsBuilder().append(this.chequeId, rhs.chequeId)
        .append(this.rejectionReason, rhs.rejectionReason).append(this.description, rhs.description)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
