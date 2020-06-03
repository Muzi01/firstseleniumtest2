
package bindings.commons.automation.mule.model.mule.development.extraservices.aio.channel.activateextraservices;

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
    "creditApplicationId",
    "extraServiceName"
})
public class Properties__ {

  @JsonProperty ("creditApplicationId")
  private CreditApplicationId creditApplicationId;
  @JsonProperty ("extraServiceName")
  private ExtraServiceName extraServiceName;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("creditApplicationId")
  public CreditApplicationId getCreditApplicationId() {
    return this.creditApplicationId;
  }

  @JsonProperty ("creditApplicationId")
  public void setCreditApplicationId(final CreditApplicationId creditApplicationId) {
    this.creditApplicationId = creditApplicationId;
  }

  @JsonProperty ("extraServiceName")
  public ExtraServiceName getExtraServiceName() {
    return this.extraServiceName;
  }

  @JsonProperty ("extraServiceName")
  public void setExtraServiceName(final ExtraServiceName extraServiceName) {
    this.extraServiceName = extraServiceName;
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
    return new HashCodeBuilder().append(this.creditApplicationId).append(this.extraServiceName)
        .append(this.additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof Properties__)) {
      return false;
    }
    final Properties__ rhs = ((Properties__) other);
    return new EqualsBuilder().append(this.creditApplicationId, rhs.creditApplicationId)
        .append(this.extraServiceName, rhs.extraServiceName)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
