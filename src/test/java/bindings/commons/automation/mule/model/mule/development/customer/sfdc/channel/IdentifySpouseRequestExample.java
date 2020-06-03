
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
    "ssn",
    "brand",
    "countryCode"
})
public class IdentifySpouseRequestExample {

  @JsonProperty ("ssn")
  private String ssn;
  @JsonProperty ("brand")
  private String brand;
  @JsonProperty ("countryCode")
  private String countryCode;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("ssn")
  public String getSsn() {
    return this.ssn;
  }

  @JsonProperty ("ssn")
  public void setSsn(final String ssn) {
    this.ssn = ssn;
  }

  @JsonProperty ("brand")
  public String getBrand() {
    return this.brand;
  }

  @JsonProperty ("brand")
  public void setBrand(final String brand) {
    this.brand = brand;
  }

  @JsonProperty ("countryCode")
  public String getCountryCode() {
    return this.countryCode;
  }

  @JsonProperty ("countryCode")
  public void setCountryCode(final String countryCode) {
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
    return new HashCodeBuilder().append(this.ssn)
        .append(this.brand)
        .append(this.countryCode)
        .append(this.additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof IdentifySpouseRequestExample)) {
      return false;
    }
    final IdentifySpouseRequestExample rhs = ((IdentifySpouseRequestExample) other);
    return new EqualsBuilder().append(this.ssn, rhs.ssn)
        .append(this.brand, rhs.brand)
        .append(this.countryCode, rhs.countryCode)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
