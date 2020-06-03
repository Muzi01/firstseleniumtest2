
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
    "amount",
    "dueDate",
    "contractStatus"
})
public class WithdrawContractResponse200Example {

  @JsonProperty ("amount")
  private String amount;
  @JsonProperty ("dueDate")
  private String dueDate;
  @JsonProperty ("contractStatus")
  private String contractStatus;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("amount")
  public String getAmount() {
    return this.amount;
  }

  @JsonProperty ("amount")
  public void setAmount(final String amount) {
    this.amount = amount;
  }

  @JsonProperty ("dueDate")
  public String getDueDate() {
    return this.dueDate;
  }

  @JsonProperty ("dueDate")
  public void setDueDate(final String dueDate) {
    this.dueDate = dueDate;
  }

  @JsonProperty ("contractStatus")
  public String getContractStatus() {
    return this.contractStatus;
  }

  @JsonProperty ("contractStatus")
  public void setContractStatus(final String contractStatus) {
    this.contractStatus = contractStatus;
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
    return new HashCodeBuilder().append(this.amount).append(this.dueDate).append(
        this.contractStatus)
        .append(this.additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof WithdrawContractResponse200Example)) {
      return false;
    }
    final WithdrawContractResponse200Example rhs = ((WithdrawContractResponse200Example) other);
    return new EqualsBuilder().append(this.amount, rhs.amount).append(this.dueDate, rhs.dueDate)
        .append(this.contractStatus, rhs.contractStatus)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
