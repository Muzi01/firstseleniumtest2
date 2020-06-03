
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
    "type",
    "price",
    "amountOfFees"
})
public class BodyListInvoiceAllocations200 {

  @JsonProperty ("type")
  private String type;
  @JsonProperty ("price")
  private Double price;
  @JsonProperty ("amountOfFees")
  private Double amountOfFees;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("type")
  public String getType() {
    return this.type;
  }

  @JsonProperty ("type")
  public void setType(final String type) {
    this.type = type;
  }

  @JsonProperty ("price")
  public Double getPrice() {
    return this.price;
  }

  @JsonProperty ("price")
  public void setPrice(final Double price) {
    this.price = price;
  }

  @JsonProperty ("amountOfFees")
  public Double getAmountOfFees() {
    return this.amountOfFees;
  }

  @JsonProperty ("amountOfFees")
  public void setAmountOfFees(final Double amountOfFees) {
    this.amountOfFees = amountOfFees;
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
    return new HashCodeBuilder().append(this.type).append(this.price).append(this.amountOfFees)
        .append(this.additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof BodyListInvoiceAllocations200)) {
      return false;
    }
    final BodyListInvoiceAllocations200 rhs = ((BodyListInvoiceAllocations200) other);
    return new EqualsBuilder().append(this.type, rhs.type).append(this.price, rhs.price)
        .append(this.amountOfFees, rhs.amountOfFees)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
