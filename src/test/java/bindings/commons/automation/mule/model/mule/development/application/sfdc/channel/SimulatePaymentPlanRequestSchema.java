
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
    "countryCode",
    "applicationId",
    "productId",
    "drawAmount",
    "firstDueDate"
})
public class SimulatePaymentPlanRequestSchema {

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("countryCode")
  private String countryCode;
  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("applicationId")
  private String applicationId;
  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("productId")
  private String productId;
  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("drawAmount")
  private Double drawAmount;
  @JsonProperty ("firstDueDate")
  private String firstDueDate;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("countryCode")
  public String getCountryCode() {
    return this.countryCode;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("countryCode")
  public void setCountryCode(final String countryCode) {
    this.countryCode = countryCode;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("applicationId")
  public String getApplicationId() {
    return this.applicationId;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("applicationId")
  public void setApplicationId(final String applicationId) {
    this.applicationId = applicationId;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("productId")
  public String getProductId() {
    return this.productId;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("productId")
  public void setProductId(final String productId) {
    this.productId = productId;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("drawAmount")
  public Double getDrawAmount() {
    return this.drawAmount;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("drawAmount")
  public void setDrawAmount(final Double drawAmount) {
    this.drawAmount = drawAmount;
  }

  @JsonProperty ("firstDueDate")
  public String getFirstDueDate() {
    return this.firstDueDate;
  }

  @JsonProperty ("firstDueDate")
  public void setFirstDueDate(final String firstDueDate) {
    this.firstDueDate = firstDueDate;
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
    return new HashCodeBuilder().append(this.countryCode).append(this.applicationId).append(
        this.productId)
        .append(this.drawAmount).append(this.firstDueDate).append(this.additionalProperties)
        .toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof SimulatePaymentPlanRequestSchema)) {
      return false;
    }
    final SimulatePaymentPlanRequestSchema rhs = ((SimulatePaymentPlanRequestSchema) other);
    return new EqualsBuilder().append(this.countryCode, rhs.countryCode)
        .append(this.applicationId, rhs.applicationId).append(this.productId, rhs.productId)
        .append(this.drawAmount, rhs.drawAmount).append(this.firstDueDate, rhs.firstDueDate)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
