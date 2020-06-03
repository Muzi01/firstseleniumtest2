
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
    "dueDate",
    "principalLeft",
    "amortisation",
    "allocatedFees",
    "allocatedInterest"
})
public class PaymentInfoList {

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("dueDate")
  private String dueDate;
  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("principalLeft")
  private Double principalLeft;
  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("amortisation")
  private Double amortisation;
  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("allocatedFees")
  private Double allocatedFees;
  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("allocatedInterest")
  private Double allocatedInterest;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("dueDate")
  public String getDueDate() {
    return this.dueDate;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("dueDate")
  public void setDueDate(final String dueDate) {
    this.dueDate = dueDate;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("principalLeft")
  public Double getPrincipalLeft() {
    return this.principalLeft;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("principalLeft")
  public void setPrincipalLeft(final Double principalLeft) {
    this.principalLeft = principalLeft;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("amortisation")
  public Double getAmortisation() {
    return this.amortisation;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("amortisation")
  public void setAmortisation(final Double amortisation) {
    this.amortisation = amortisation;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("allocatedFees")
  public Double getAllocatedFees() {
    return this.allocatedFees;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("allocatedFees")
  public void setAllocatedFees(final Double allocatedFees) {
    this.allocatedFees = allocatedFees;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("allocatedInterest")
  public Double getAllocatedInterest() {
    return this.allocatedInterest;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("allocatedInterest")
  public void setAllocatedInterest(final Double allocatedInterest) {
    this.allocatedInterest = allocatedInterest;
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
    return new HashCodeBuilder().append(this.dueDate).append(this.principalLeft).append(
        this.amortisation)
        .append(this.allocatedFees).append(this.allocatedInterest).append(this.additionalProperties)
        .toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof PaymentInfoList)) {
      return false;
    }
    final PaymentInfoList rhs = ((PaymentInfoList) other);
    return new EqualsBuilder().append(this.dueDate, rhs.dueDate)
        .append(this.principalLeft, rhs.principalLeft)
        .append(this.amortisation, rhs.amortisation).append(this.allocatedFees, rhs.allocatedFees)
        .append(this.allocatedInterest, rhs.allocatedInterest)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
