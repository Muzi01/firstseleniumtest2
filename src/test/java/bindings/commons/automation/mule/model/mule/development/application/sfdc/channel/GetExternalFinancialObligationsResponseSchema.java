
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude (JsonInclude.Include.NON_NULL)
@JsonPropertyOrder ({
    "success",
    "result",
    "externalFinancialObligationsList"
})
public class GetExternalFinancialObligationsResponseSchema {

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("success")
  private String success;
  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("result")
  private String result;
  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("externalFinancialObligationsList")
  private List<ExternalFinancialObligationsList> externalFinancialObligationsList =
      new ArrayList<>();
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("success")
  public String getSuccess() {
    return this.success;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("success")
  public void setSuccess(final String success) {
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

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("externalFinancialObligationsList")
  public List<ExternalFinancialObligationsList> getExternalFinancialObligationsList() {
    return this.externalFinancialObligationsList;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("externalFinancialObligationsList")
  public void setExternalFinancialObligationsList(
      final List<ExternalFinancialObligationsList> externalFinancialObligationsList) {
    this.externalFinancialObligationsList = externalFinancialObligationsList;
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
    return new HashCodeBuilder().append(this.success).append(this.result)
        .append(this.externalFinancialObligationsList).append(this.additionalProperties)
        .toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof GetExternalFinancialObligationsResponseSchema)) {
      return false;
    }
    final GetExternalFinancialObligationsResponseSchema rhs =
        ((GetExternalFinancialObligationsResponseSchema) other);
    return new EqualsBuilder().append(this.success, rhs.success).append(this.result, rhs.result)
        .append(this.externalFinancialObligationsList, rhs.externalFinancialObligationsList)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
