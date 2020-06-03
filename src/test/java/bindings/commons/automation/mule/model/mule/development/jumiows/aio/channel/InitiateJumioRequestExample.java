
package bindings.commons.automation.mule.model.mule.development.jumiows.aio.channel;

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
    "customerInternalReference",
    "userReference",
    "successUrl",
    "errorUrl",
    "callbackUrl",
    "locale",
    "country",
    "presetCountry",
    "presetIdType"
})
public class InitiateJumioRequestExample {

  @JsonProperty ("customerInternalReference")
  private String merchantIdScanReference;
  @JsonProperty ("userReference")
  private String customerId;
  @JsonProperty ("successUrl")
  private String successUrl;
  @JsonProperty ("errorUrl")
  private String errorUrl;
  @JsonProperty ("callbackUrl")
  private String callbackUrl;
  @JsonProperty ("locale")
  private String locale;
  @JsonProperty ("country")
  private String country;
  @JsonProperty ("presetCountry")
  private String presetCountry;
  @JsonProperty ("presetIdType")
  private String presetIdType;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("customerInternalReference")
  public String getMerchantIdScanReference() {
    return this.merchantIdScanReference;
  }

  @JsonProperty ("customerInternalReference")
  public void setMerchantIdScanReference(final String merchantIdScanReference) {
    this.merchantIdScanReference = merchantIdScanReference;
  }

  @JsonProperty ("userReference")
  public String getCustomerId() {
    return this.customerId;
  }

  @JsonProperty ("userReference")
  public void setCustomerId(final String customerId) {
    this.customerId = customerId;
  }

  @JsonProperty ("successUrl")
  public String getSuccessUrl() {
    return this.successUrl;
  }

  @JsonProperty ("successUrl")
  public void setSuccessUrl(final String successUrl) {
    this.successUrl = successUrl;
  }

  @JsonProperty ("errorUrl")
  public String getErrorUrl() {
    return this.errorUrl;
  }

  @JsonProperty ("errorUrl")
  public void setErrorUrl(final String errorUrl) {
    this.errorUrl = errorUrl;
  }

  @JsonProperty ("callbackUrl")
  public String getCallbackUrl() {
    return this.callbackUrl;
  }

  @JsonProperty ("callbackUrl")
  public void setCallbackUrl(final String callbackUrl) {
    this.callbackUrl = callbackUrl;
  }

  @JsonProperty ("locale")
  public String getLocale() {
    return this.locale;
  }

  @JsonProperty ("locale")
  public void setLocale(final String locale) {
    this.locale = locale;
  }

  @JsonProperty ("country")
  public String getCountry() {
    return this.country;
  }

  @JsonProperty ("country")
  public void setCountry(final String country) {
    this.country = country;
  }

  @JsonProperty ("presetCountry")
  public String getPresetCountry() {
    return this.presetCountry;
  }

  @JsonProperty ("presetCountry")
  public void setPresetCountry(final String presetCountry) {
    this.presetCountry = presetCountry;
  }

  @JsonProperty ("presetIdType")
  public String getPresetIdType() {
    return this.presetIdType;
  }

  @JsonProperty ("presetIdType")
  public void setPresetIdType(final String presetIdType) {
    this.presetIdType = presetIdType;
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
    return new HashCodeBuilder().append(this.merchantIdScanReference)
        .append(this.customerId)
        .append(this.successUrl)
        .append(this.errorUrl)
        .append(this.callbackUrl)
        .append(this.locale)
        .append(this.country)
        .append(this.presetCountry)
        .append(this.presetIdType)
        .append(this.additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof InitiateJumioRequestExample)) {
      return false;
    }
    final InitiateJumioRequestExample rhs = ((InitiateJumioRequestExample) other);
    return new EqualsBuilder().append(this.merchantIdScanReference, rhs.merchantIdScanReference)
        .append(this.customerId, rhs.customerId).append(this.successUrl, rhs.successUrl)
        .append(this.errorUrl, rhs.errorUrl).append(this.callbackUrl, rhs.callbackUrl)
        .append(this.locale, rhs.locale).append(this.country, rhs.country)
        .append(this.presetCountry, rhs.presetCountry).append(this.presetIdType, rhs.presetIdType)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
