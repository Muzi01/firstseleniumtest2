
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
    "country",
    "chequeId",
    "status"
})
public class ChequeUpdateStatus {

  @JsonProperty ("country")
  private String country;
  @JsonProperty ("chequeId")
  private String chequeId;
  @JsonProperty ("status")
  private String status;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("country")
  public String getCountry() {
    return this.country;
  }

  @JsonProperty ("country")
  public void setCountry(final String country) {
    this.country = country;
  }

  @JsonProperty ("chequeId")
  public String getChequeId() {
    return this.chequeId;
  }

  @JsonProperty ("chequeId")
  public void setChequeId(final String chequeId) {
    this.chequeId = chequeId;
  }

  @JsonProperty ("status")
  public String getStatus() {
    return this.status;
  }

  @JsonProperty ("status")
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
    return new HashCodeBuilder().append(this.country).append(this.chequeId).append(this.status)
        .append(this.additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof ChequeUpdateStatus)) {
      return false;
    }
    final ChequeUpdateStatus rhs = ((ChequeUpdateStatus) other);
    return new EqualsBuilder().append(this.country, rhs.country).append(this.chequeId, rhs.chequeId)
        .append(this.status, rhs.status).append(this.additionalProperties, rhs.additionalProperties)
        .isEquals();
  }

}
