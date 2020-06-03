
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
    "Country",
    "ContractId",
    "CloseType",
    "CloseDateTime",
    "CreateCrediMemo"
})
public class ContractCloseRequestExample {

  @JsonProperty ("Country")
  private String country;
  @JsonProperty ("ContractId")
  private String contractId;
  @JsonProperty ("CloseType")
  private String closeType;
  @JsonProperty ("CloseDateTime")
  private String closeDateTime;
  @JsonProperty ("CreateCrediMemo")
  private String createCrediMemo;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("Country")
  public String getCountry() {
    return this.country;
  }

  @JsonProperty ("Country")
  public void setCountry(final String country) {
    this.country = country;
  }

  @JsonProperty ("ContractId")
  public String getContractId() {
    return this.contractId;
  }

  @JsonProperty ("ContractId")
  public void setContractId(final String contractId) {
    this.contractId = contractId;
  }

  @JsonProperty ("CloseType")
  public String getCloseType() {
    return this.closeType;
  }

  @JsonProperty ("CloseType")
  public void setCloseType(final String closeType) {
    this.closeType = closeType;
  }

  @JsonProperty ("CloseDateTime")
  public String getCloseDateTime() {
    return this.closeDateTime;
  }

  @JsonProperty ("CloseDateTime")
  public void setCloseDateTime(final String closeDateTime) {
    this.closeDateTime = closeDateTime;
  }

  @JsonProperty ("CreateCrediMemo")
  public String getCreateCrediMemo() {
    return this.createCrediMemo;
  }

  @JsonProperty ("CreateCrediMemo")
  public void setCreateCrediMemo(final String createCrediMemo) {
    this.createCrediMemo = createCrediMemo;
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
        .append(this.country).append(this.contractId)
        .append(this.closeType).append(this.closeDateTime)
        .append(this.createCrediMemo).append(this.additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof ContractCloseRequestExample)) {
      return false;
    }
    final ContractCloseRequestExample rhs = ((ContractCloseRequestExample) other);
    return new EqualsBuilder()
        .append(this.country, rhs.country).append(this.contractId, rhs.contractId)
        .append(this.closeType, rhs.closeType).append(this.closeDateTime, rhs.closeDateTime)
        .append(this.createCrediMemo, rhs.createCrediMemo)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
