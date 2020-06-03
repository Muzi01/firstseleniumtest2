
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
    "success"
})
public class ObligationsReportResponseExample200 {

  @JsonProperty ("success")
  private Boolean success;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("success")
  public Boolean getSuccess() {
    return this.success;
  }

  @JsonProperty ("success")
  public void setSuccess(final Boolean success) {
    this.success = success;
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
        .append(this.success)
        .append(this.additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof ObligationsReportResponseExample200)) {
      return false;
    }
    final ObligationsReportResponseExample200 rhs = ((ObligationsReportResponseExample200) other);
    return new EqualsBuilder().append(this.success, rhs.success)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
