
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
    "created",
    "agency",
    "type",
    "customerSSN",
    "validUntil",
    "error",
    "errorCode"
})
public class Body_ {

  @JsonProperty ("externalRegistryId")
  private String externalRegistryId;
  @JsonProperty ("created")
  private String created;
  @JsonProperty ("agency")
  private String agency;
  @JsonProperty ("type")
  private String type;
  @JsonProperty ("customerSSN")
  private String customerSSN;
  @JsonProperty ("validUntil")
  private String validUntil;
  @JsonProperty ("error")
  private String error;
  @JsonProperty ("errorCode")
  private String errorCode;
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

  @JsonProperty ("created")
  public String getCreated() {
    return this.created;
  }

  @JsonProperty ("created")
  public void setCreated(final String created) {
    this.created = created;
  }

  @JsonProperty ("agency")
  public String getAgency() {
    return this.agency;
  }

  @JsonProperty ("agency")
  public void setAgency(final String agency) {
    this.agency = agency;
  }

  @JsonProperty ("type")
  public String getType() {
    return this.type;
  }

  @JsonProperty ("type")
  public void setType(final String type) {
    this.type = type;
  }

  @JsonProperty ("customerSSN")
  public String getCustomerSSN() {
    return this.customerSSN;
  }

  @JsonProperty ("customerSSN")
  public void setCustomerSSN(final String customerSSN) {
    this.customerSSN = customerSSN;
  }

  @JsonProperty ("validUntil")
  public String getValidUntil() {
    return this.validUntil;
  }

  @JsonProperty ("validUntil")
  public void setValidUntil(final String validUntil) {
    this.validUntil = validUntil;
  }

  @JsonProperty ("error")
  public String getError() {
    return this.error;
  }

  @JsonProperty ("error")
  public void setError(final String error) {
    this.error = error;
  }

  @JsonProperty ("errorCode")
  public String getErrorCode() {
    return this.errorCode;
  }

  @JsonProperty ("errorCode")
  public void setErrorCode(final String errorCode) {
    this.errorCode = errorCode;
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
    return new HashCodeBuilder().append(this.externalRegistryId).append(this.created).append(
        this.agency)
        .append(this.type).append(this.customerSSN)
        .append(this.validUntil).append(this.error)
        .append(this.errorCode).append(this.additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof Body_)) {
      return false;
    }
    final Body_ rhs = ((Body_) other);
    return new EqualsBuilder()
        .append(this.externalRegistryId, rhs.externalRegistryId)
        .append(this.created, rhs.created).append(this.agency, rhs.agency)
        .append(this.type, rhs.type).append(this.customerSSN, rhs.customerSSN)
        .append(this.validUntil, rhs.validUntil).append(this.error, rhs.error)
        .append(this.errorCode, rhs.errorCode)
        .append(this.additionalProperties, rhs.additionalProperties)
        .isEquals();
  }

}
