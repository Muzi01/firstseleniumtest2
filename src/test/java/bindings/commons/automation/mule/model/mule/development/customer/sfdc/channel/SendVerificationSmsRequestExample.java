
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
    "countryCode",
    "msisdn",
    "ssn"
})
public class SendVerificationSmsRequestExample {

  @JsonProperty ("countryCode")
  private String countryCode;
  @JsonProperty ("msisdn")
  private String msisdn;
  @JsonProperty ("ssn")
  private String ssn;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("countryCode")
  public String getCountryCode() {
    return this.countryCode;
  }

  @JsonProperty ("countryCode")
  public void setCountryCode(final String countryCode) {
    this.countryCode = countryCode;
  }

  @JsonProperty ("msisdn")
  public String getMsisdn() {
    return this.msisdn;
  }

  @JsonProperty ("msisdn")
  public void setMsisdn(final String msisdn) {
    this.msisdn = msisdn;
  }

  @JsonProperty ("ssn")
  public String getSsn() {
    return this.ssn;
  }

  @JsonProperty ("ssn")
  public void setSsn(final String ssn) {
    this.ssn = ssn;
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
        .append(this.countryCode)
        .append(this.msisdn)
        .append(this.ssn)
        .append(this.additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof SendVerificationSmsRequestExample)) {
      return false;
    }
    final SendVerificationSmsRequestExample rhs = ((SendVerificationSmsRequestExample) other);
    return new EqualsBuilder()
        .append(this.countryCode, rhs.countryCode)
        .append(this.msisdn, rhs.msisdn)
        .append(this.ssn, rhs.ssn)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
