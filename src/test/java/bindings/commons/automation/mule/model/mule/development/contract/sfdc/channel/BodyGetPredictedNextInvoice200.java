
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
    "invoiceDate"
})
public class BodyGetPredictedNextInvoice200 {

  @JsonProperty ("amount")
  private Integer amount;
  @JsonProperty ("dueDate")
  private String dueDate;
  @JsonProperty ("invoiceDate")
  private String invoiceDate;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("amount")
  public Integer getAmount() {
    return this.amount;
  }

  @JsonProperty ("amount")
  public void setAmount(final Integer amount) {
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

  @JsonProperty ("invoiceDate")
  public String getInvoiceDate() {
    return this.invoiceDate;
  }

  @JsonProperty ("invoiceDate")
  public void setInvoiceDate(final String invoiceDate) {
    this.invoiceDate = invoiceDate;
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
    return new HashCodeBuilder().append(this.amount).append(this.dueDate).append(this.invoiceDate)
        .append(this.additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof BodyGetPredictedNextInvoice200)) {
      return false;
    }
    final BodyGetPredictedNextInvoice200 rhs = ((BodyGetPredictedNextInvoice200) other);
    return new EqualsBuilder().append(this.amount, rhs.amount).append(this.dueDate, rhs.dueDate)
        .append(this.invoiceDate, rhs.invoiceDate)
        .append(this.additionalProperties, rhs.additionalProperties)
        .isEquals();
  }

}
