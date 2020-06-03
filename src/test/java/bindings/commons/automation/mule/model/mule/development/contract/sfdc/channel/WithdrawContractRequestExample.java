
package bindings.commons.automation.mule.model.mule.development.contract.sfdc.channel;

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
    "contractId",
    "closeBy",
    "closeType",
    "requestDate"
})
public class WithdrawContractRequestExample {

  @JsonProperty ("country")
  private String country;
  @JsonProperty ("contractId")
  private String contractId;
  @JsonProperty ("closeBy")
  private String closeBy;
  @JsonProperty ("closeType")
  private String closeType;
  @JsonProperty ("requestDate")
  private String requestDate;
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

  @JsonProperty ("contractId")
  public String getContractId() {
    return this.contractId;
  }

  @JsonProperty ("contractId")
  public void setContractId(final String contractId) {
    this.contractId = contractId;
  }

  @JsonProperty ("closeBy")
  public String getCloseBy() {
    return this.closeBy;
  }

  @JsonProperty ("closeBy")
  public void setCloseBy(final String closeBy) {
    this.closeBy = closeBy;
  }

  @JsonProperty ("closeType")
  public String getCloseType() {
    return this.closeType;
  }

  @JsonProperty ("closeType")
  public void setCloseType(final String closeType) {
    this.closeType = closeType;
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
    return new HashCodeBuilder().append(this.country).append(this.contractId).append(this.closeBy)
        .append(this.closeType).append(this.requestDate).append(this.additionalProperties)
        .toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof WithdrawContractRequestExample)) {
      return false;
    }
    final WithdrawContractRequestExample rhs = ((WithdrawContractRequestExample) other);
    return new EqualsBuilder().append(this.country, rhs.country)
        .append(this.contractId, rhs.contractId)
        .append(this.closeBy, rhs.closeBy).append(this.closeType, rhs.closeType)
        .append(this.requestDate, rhs.requestDate)
        .append(this.additionalProperties, rhs.additionalProperties)
        .isEquals();
  }

}
