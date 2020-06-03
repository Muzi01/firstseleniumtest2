
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
    "externalRegistryId",
    "data"
})
public class Body {

  @JsonProperty ("externalRegistryId")
  private String externalRegistryId;
  @JsonProperty ("data")
  private String data;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("externalRegistryId")
  public String getExternalRegistryId() {
    return this.externalRegistryId;
  }

  @JsonProperty ("externalRegistryId")
  public void setExternalRegistryId(final String externalRegistryId) {
    this.externalRegistryId = externalRegistryId;
  }

  @JsonProperty ("data")
  public String getData() {
    return this.data;
  }

  @JsonProperty ("data")
  public void setData(final String data) {
    this.data = data;
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
        .append(this.externalRegistryId)
        .append(this.data)
        .append(this.additionalProperties).toHashCode();
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
    return new EqualsBuilder().append(this.externalRegistryId,
        rhs.externalRegistryId)
        .append(this.data, rhs.data)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
