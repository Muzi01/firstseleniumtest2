
package bindings.commons.automation.mule.model.mule.development.extraservices.aio.channel.deactivateextraservices;


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
    "countryCode"
})
public class Properties_ {

  @JsonProperty ("countryCode")
  private CountryCode countryCode;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("countryCode")
  public CountryCode getCountryCode() {
    return this.countryCode;
  }

  @JsonProperty ("countryCode")
  public void setCountryCode(final CountryCode countryCode) {
    this.countryCode = countryCode;
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
    return new HashCodeBuilder().append(this.countryCode).append(this.additionalProperties)
        .toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof Properties_)) {
      return false;
    }
    final Properties_ rhs = ((Properties_) other);
    return new EqualsBuilder().append(this.countryCode, rhs.countryCode)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
