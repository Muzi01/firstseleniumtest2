
package bindings.commons.automation.mule.model.mule.development.case1.sfdc.channel;

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
    "data",
    "reason",
    "success"
})
public class ProductOfferResponse {

  @JsonProperty ("data")
  private Data data;
  @JsonProperty ("reason")
  private String reason;
  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("success")
  private Boolean success;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("data")
  public Data getData() {
    return this.data;
  }

  @JsonProperty ("data")
  public void setData(final Data data) {
    this.data = data;
  }

  @JsonProperty ("reason")
  public String getReason() {
    return this.reason;
  }

  @JsonProperty ("reason")
  public void setReason(final String reason) {
    this.reason = reason;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("success")
  public Boolean getSuccess() {
    return this.success;
  }

  /**
   * 
   * (Required)
   * 
   */
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
        .append(this.data)
        .append(this.reason)
        .append(this.success)
        .append(this.additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof ProductOfferResponse)) {
      return false;
    }
    final ProductOfferResponse rhs = ((ProductOfferResponse) other);
    return new EqualsBuilder().append(this.data, rhs.data)
        .append(this.reason, rhs.reason)
        .append(this.success, rhs.success)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
