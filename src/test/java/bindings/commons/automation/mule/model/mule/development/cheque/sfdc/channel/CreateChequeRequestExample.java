
package bindings.commons.automation.mule.model.mule.development.cheque.sfdc.channel;

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
    "creditApplicationId"
})
public class CreateChequeRequestExample {

  @JsonProperty ("country")
  private String country;
  @JsonProperty ("creditApplicationId")
  private String creditApplicationId;
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

  @JsonProperty ("creditApplicationId")
  public String getCreditApplicationId() {
    return this.creditApplicationId;
  }

  @JsonProperty ("creditApplicationId")
  public void setCreditApplicationId(final String creditApplicationId) {
    this.creditApplicationId = creditApplicationId;
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
    return new HashCodeBuilder().append(this.country).append(this.creditApplicationId)
        .append(this.additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof CreateChequeRequestExample)) {
      return false;
    }
    final CreateChequeRequestExample rhs = ((CreateChequeRequestExample) other);
    return new EqualsBuilder().append(this.country, rhs.country)
        .append(this.creditApplicationId, rhs.creditApplicationId)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
