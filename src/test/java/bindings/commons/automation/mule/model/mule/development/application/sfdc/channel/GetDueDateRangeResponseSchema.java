
package bindings.commons.automation.mule.model.mule.development.application.sfdc.channel;

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
    "success",
    "result",
    "firstDate",
    "defaultDate",
    "lastDate"
})
public class GetDueDateRangeResponseSchema {

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("success")
  private Boolean success;
  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("result")
  private String result;
  @JsonProperty ("firstDate")
  private String firstDate;
  @JsonProperty ("defaultDate")
  private String defaultDate;
  @JsonProperty ("lastDate")
  private String lastDate;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

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

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("result")
  public String getResult() {
    return this.result;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("result")
  public void setResult(final String result) {
    this.result = result;
  }

  @JsonProperty ("firstDate")
  public String getFirstDate() {
    return this.firstDate;
  }

  @JsonProperty ("firstDate")
  public void setFirstDate(final String firstDate) {
    this.firstDate = firstDate;
  }

  @JsonProperty ("defaultDate")
  public String getDefaultDate() {
    return this.defaultDate;
  }

  @JsonProperty ("defaultDate")
  public void setDefaultDate(final String defaultDate) {
    this.defaultDate = defaultDate;
  }

  @JsonProperty ("lastDate")
  public String getLastDate() {
    return this.lastDate;
  }

  @JsonProperty ("lastDate")
  public void setLastDate(final String lastDate) {
    this.lastDate = lastDate;
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
    return new HashCodeBuilder().append(this.success).append(this.result).append(this.firstDate)
        .append(this.defaultDate).append(this.lastDate).append(this.additionalProperties)
        .toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof GetDueDateRangeResponseSchema)) {
      return false;
    }
    final GetDueDateRangeResponseSchema rhs = ((GetDueDateRangeResponseSchema) other);
    return new EqualsBuilder().append(this.success, rhs.success).append(this.result, rhs.result)
        .append(this.firstDate, rhs.firstDate).append(this.defaultDate, rhs.defaultDate)
        .append(this.lastDate, rhs.lastDate)
        .append(this.additionalProperties, rhs.additionalProperties)
        .isEquals();
  }

}
