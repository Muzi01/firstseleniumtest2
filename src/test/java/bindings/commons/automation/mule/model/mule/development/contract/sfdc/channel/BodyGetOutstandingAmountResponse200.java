
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
    "collectionFee",
    "drawFee",
    "extraServiceFee",
    "interest",
    "lateInterest",
    "lateInvoiceables",
    "monthlyFee",
    "normalInvoiceables",
    "principal",
    "reminderFee",
    "totalAmount"
})
public class BodyGetOutstandingAmountResponse200 {

  @JsonProperty ("collectionFee")
  private Integer collectionFee;
  @JsonProperty ("drawFee")
  private Integer drawFee;
  @JsonProperty ("extraServiceFee")
  private Integer extraServiceFee;
  @JsonProperty ("interest")
  private Integer interest;
  @JsonProperty ("lateInterest")
  private Integer lateInterest;
  @JsonProperty ("lateInvoiceables")
  private Integer lateInvoiceables;
  @JsonProperty ("monthlyFee")
  private Integer monthlyFee;
  @JsonProperty ("normalInvoiceables")
  private Integer normalInvoiceables;
  @JsonProperty ("principal")
  private Integer principal;
  @JsonProperty ("reminderFee")
  private Integer reminderFee;
  @JsonProperty ("totalAmount")
  private Integer totalAmount;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("collectionFee")
  public Integer getCollectionFee() {
    return this.collectionFee;
  }

  @JsonProperty ("collectionFee")
  public void setCollectionFee(final Integer collectionFee) {
    this.collectionFee = collectionFee;
  }

  @JsonProperty ("drawFee")
  public Integer getDrawFee() {
    return this.drawFee;
  }

  @JsonProperty ("drawFee")
  public void setDrawFee(final Integer drawFee) {
    this.drawFee = drawFee;
  }

  @JsonProperty ("extraServiceFee")
  public Integer getExtraServiceFee() {
    return this.extraServiceFee;
  }

  @JsonProperty ("extraServiceFee")
  public void setExtraServiceFee(final Integer extraServiceFee) {
    this.extraServiceFee = extraServiceFee;
  }

  @JsonProperty ("interest")
  public Integer getInterest() {
    return this.interest;
  }

  @JsonProperty ("interest")
  public void setInterest(final Integer interest) {
    this.interest = interest;
  }

  @JsonProperty ("lateInterest")
  public Integer getLateInterest() {
    return this.lateInterest;
  }

  @JsonProperty ("lateInterest")
  public void setLateInterest(final Integer lateInterest) {
    this.lateInterest = lateInterest;
  }

  @JsonProperty ("lateInvoiceables")
  public Integer getLateInvoiceables() {
    return this.lateInvoiceables;
  }

  @JsonProperty ("lateInvoiceables")
  public void setLateInvoiceables(final Integer lateInvoiceables) {
    this.lateInvoiceables = lateInvoiceables;
  }

  @JsonProperty ("monthlyFee")
  public Integer getMonthlyFee() {
    return this.monthlyFee;
  }

  @JsonProperty ("monthlyFee")
  public void setMonthlyFee(final Integer monthlyFee) {
    this.monthlyFee = monthlyFee;
  }

  @JsonProperty ("normalInvoiceables")
  public Integer getNormalInvoiceables() {
    return this.normalInvoiceables;
  }

  @JsonProperty ("normalInvoiceables")
  public void setNormalInvoiceables(final Integer normalInvoiceables) {
    this.normalInvoiceables = normalInvoiceables;
  }

  @JsonProperty ("principal")
  public Integer getPrincipal() {
    return this.principal;
  }

  @JsonProperty ("principal")
  public void setPrincipal(final Integer principal) {
    this.principal = principal;
  }

  @JsonProperty ("reminderFee")
  public Integer getReminderFee() {
    return this.reminderFee;
  }

  @JsonProperty ("reminderFee")
  public void setReminderFee(final Integer reminderFee) {
    this.reminderFee = reminderFee;
  }

  @JsonProperty ("totalAmount")
  public Integer getTotalAmount() {
    return this.totalAmount;
  }

  @JsonProperty ("totalAmount")
  public void setTotalAmount(final Integer totalAmount) {
    this.totalAmount = totalAmount;
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
    return new HashCodeBuilder().append(this.collectionFee).append(this.drawFee).append(
        this.extraServiceFee)
        .append(this.interest).append(this.lateInterest).append(this.lateInvoiceables).append(
            this.monthlyFee)
        .append(this.normalInvoiceables).append(this.principal).append(this.reminderFee).append(
            this.totalAmount)
        .append(this.additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof BodyGetOutstandingAmountResponse200)) {
      return false;
    }
    final BodyGetOutstandingAmountResponse200 rhs = ((BodyGetOutstandingAmountResponse200) other);
    return new EqualsBuilder().append(this.collectionFee, rhs.collectionFee)
        .append(this.drawFee, rhs.drawFee)
        .append(this.extraServiceFee, rhs.extraServiceFee).append(this.interest, rhs.interest)
        .append(this.lateInterest, rhs.lateInterest)
        .append(this.lateInvoiceables, rhs.lateInvoiceables)
        .append(this.monthlyFee, rhs.monthlyFee)
        .append(this.normalInvoiceables, rhs.normalInvoiceables)
        .append(this.principal, rhs.principal).append(this.reminderFee, rhs.reminderFee)
        .append(this.totalAmount, rhs.totalAmount)
        .append(this.additionalProperties, rhs.additionalProperties)
        .isEquals();
  }

}
