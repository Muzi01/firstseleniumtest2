
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
    "amountOfFrees"
})
public class Body {

  @JsonProperty ("type")
  private String type;
  @JsonProperty ("price")
  private Double price;
  @JsonProperty ("amountOfFrees")
  private Integer amountOfFrees;
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

  @JsonProperty ("amountOfFrees")
  public Integer getAmountOfFrees() {
    return this.amountOfFrees;
  }

  @JsonProperty ("amountOfFrees")
  public void setAmountOfFrees(final Integer amountOfFrees) {
    this.amountOfFrees = amountOfFrees;
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
    return new HashCodeBuilder().append(this.type).append(this.price).append(this.amountOfFrees)
        .append(this.additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof Body)) {
      return false;
    }
    final Body rhs = ((Body) other);
    return new EqualsBuilder().append(this.type, rhs.type).append(this.price, rhs.price)
        .append(this.amountOfFrees, rhs.amountOfFrees)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
