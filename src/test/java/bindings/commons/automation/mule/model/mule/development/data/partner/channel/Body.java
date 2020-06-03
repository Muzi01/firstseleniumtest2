
package bindings.commons.automation.mule.model.mule.development.data.partner.channel;

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
    "accountNumber",
    "applicationNumber",
    "applicationStatus",
    "taskStatus",
    "applicationChannel",
    "applicationLastModifiedDate",
    "inCollections",
    "productAmountTaken",
    "hasAttachment",
    "caseType"
})
public class Body {

  @JsonProperty ("accountNumber")
  private String accountNumber;
  @JsonProperty ("applicationNumber")
  private String applicationNumber;
  @JsonProperty ("applicationStatus")
  private String applicationStatus;
  @JsonProperty ("taskStatus")
  private String taskStatus;
  @JsonProperty ("applicationChannel")
  private String applicationChannel;
  @JsonProperty ("applicationLastModifiedDate")
  private String applicationLastModifiedDate;
  @JsonProperty ("inCollections")
  private Boolean inCollections;
  @JsonProperty ("productAmountTaken")
  private String productAmountTaken;
  @JsonProperty ("hasAttachment")
  private Boolean hasAttachment;
  @JsonProperty ("caseType")
  private String caseType;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("accountNumber")
  public String getAccountNumber() {
    return this.accountNumber;
  }

  @JsonProperty ("accountNumber")
  public void setAccountNumber(final String accountNumber) {
    this.accountNumber = accountNumber;
  }

  @JsonProperty ("applicationNumber")
  public String getApplicationNumber() {
    return this.applicationNumber;
  }

  @JsonProperty ("applicationNumber")
  public void setApplicationNumber(final String applicationNumber) {
    this.applicationNumber = applicationNumber;
  }

  @JsonProperty ("applicationStatus")
  public String getApplicationStatus() {
    return this.applicationStatus;
  }

  @JsonProperty ("applicationStatus")
  public void setApplicationStatus(final String applicationStatus) {
    this.applicationStatus = applicationStatus;
  }

  @JsonProperty ("taskStatus")
  public String getTaskStatus() {
    return this.taskStatus;
  }

  @JsonProperty ("taskStatus")
  public void setTaskStatus(final String taskStatus) {
    this.taskStatus = taskStatus;
  }

  @JsonProperty ("applicationChannel")
  public String getApplicationChannel() {
    return this.applicationChannel;
  }

  @JsonProperty ("applicationChannel")
  public void setApplicationChannel(final String applicationChannel) {
    this.applicationChannel = applicationChannel;
  }

  @JsonProperty ("applicationLastModifiedDate")
  public String getApplicationLastModifiedDate() {
    return this.applicationLastModifiedDate;
  }

  @JsonProperty ("applicationLastModifiedDate")
  public void setApplicationLastModifiedDate(final String applicationLastModifiedDate) {
    this.applicationLastModifiedDate = applicationLastModifiedDate;
  }

  @JsonProperty ("inCollections")
  public Boolean getInCollections() {
    return this.inCollections;
  }

  @JsonProperty ("inCollections")
  public void setInCollections(final Boolean inCollections) {
    this.inCollections = inCollections;
  }

  @JsonProperty ("productAmountTaken")
  public String getProductAmountTaken() {
    return this.productAmountTaken;
  }

  @JsonProperty ("productAmountTaken")
  public void setProductAmountTaken(final String productAmountTaken) {
    this.productAmountTaken = productAmountTaken;
  }

  @JsonProperty ("hasAttachment")
  public Boolean getHasAttachment() {
    return this.hasAttachment;
  }

  @JsonProperty ("hasAttachment")
  public void setHasAttachment(final Boolean hasAttachment) {
    this.hasAttachment = hasAttachment;
  }

  @JsonProperty ("caseType")
  public String getCaseType() {
    return this.caseType;
  }

  @JsonProperty ("caseType")
  public void setCaseType(final String caseType) {
    this.caseType = caseType;
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
    return new HashCodeBuilder().append(this.accountNumber).append(this.applicationNumber)
        .append(this.applicationStatus).append(this.taskStatus).append(this.applicationChannel)
        .append(this.applicationLastModifiedDate).append(this.inCollections).append(
            this.productAmountTaken)
        .append(this.hasAttachment).append(this.caseType).append(this.additionalProperties)
        .toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof Body)) {
      return false;
    }
    final Body rhs = ((Body) other);
    return new EqualsBuilder().append(this.accountNumber, rhs.accountNumber)
        .append(this.applicationNumber, rhs.applicationNumber)
        .append(this.applicationStatus, rhs.applicationStatus)
        .append(this.taskStatus, rhs.taskStatus)
        .append(this.applicationChannel, rhs.applicationChannel)
        .append(this.applicationLastModifiedDate, rhs.applicationLastModifiedDate)
        .append(this.inCollections, rhs.inCollections)
        .append(this.productAmountTaken, rhs.productAmountTaken)
        .append(this.hasAttachment, rhs.hasAttachment).append(this.caseType, rhs.caseType)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
