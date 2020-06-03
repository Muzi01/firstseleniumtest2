
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
    "lastAmount",
    "mmp",
    "totalAmount",
    "totalInterest",
    "paymentInfoList"
})
public class SimulatePaymentPlanResponseSchema {

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
  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("lastAmount")
  private Double lastAmount;
  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("mmp")
  private Double mmp;
  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("totalAmount")
  private Double totalAmount;
  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("totalInterest")
  private Double totalInterest;
  @JsonProperty ("paymentInfoList")
  private List<PaymentInfoList> paymentInfoList = new ArrayList<>();
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

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("lastAmount")
  public Double getLastAmount() {
    return this.lastAmount;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("lastAmount")
  public void setLastAmount(final Double lastAmount) {
    this.lastAmount = lastAmount;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("mmp")
  public Double getMmp() {
    return this.mmp;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("mmp")
  public void setMmp(final Double mmp) {
    this.mmp = mmp;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("totalAmount")
  public Double getTotalAmount() {
    return this.totalAmount;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("totalAmount")
  public void setTotalAmount(final Double totalAmount) {
    this.totalAmount = totalAmount;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("totalInterest")
  public Double getTotalInterest() {
    return this.totalInterest;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("totalInterest")
  public void setTotalInterest(final Double totalInterest) {
    this.totalInterest = totalInterest;
  }

  @JsonProperty ("paymentInfoList")
  public List<PaymentInfoList> getPaymentInfoList() {
    return this.paymentInfoList;
  }

  @JsonProperty ("paymentInfoList")
  public void setPaymentInfoList(final List<PaymentInfoList> paymentInfoList) {
    this.paymentInfoList = paymentInfoList;
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
    return new HashCodeBuilder().append(this.success).append(this.result).append(this.lastAmount)
        .append(
            this.mmp)
        .append(this.totalAmount).append(this.totalInterest).append(this.paymentInfoList)
        .append(this.additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof SimulatePaymentPlanResponseSchema)) {
      return false;
    }
    final SimulatePaymentPlanResponseSchema rhs = ((SimulatePaymentPlanResponseSchema) other);
    return new EqualsBuilder().append(this.success, rhs.success).append(this.result, rhs.result)
        .append(this.lastAmount, rhs.lastAmount).append(this.mmp, rhs.mmp)
        .append(this.totalAmount, rhs.totalAmount).append(this.totalInterest, rhs.totalInterest)
        .append(this.paymentInfoList, rhs.paymentInfoList)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
