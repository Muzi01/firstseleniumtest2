
package bindings.commons.automation.mule.model.mule.development.application.sfdc.channel;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude (JsonInclude.Include.NON_NULL)
@JsonPropertyOrder ({
    "ip",
    "postOfficeID",
    "sFContactID",
    "payoutMethod",
    "rejectReason",
    "purposeOfCredit",
    "firstDueDate",
    "tmxStatus",
    "otherData",
    "educationAndEmployment"
})
public class ApplicationData {

  @JsonProperty ("ip")
  private String ip;
  @JsonProperty ("postOfficeID")
  private String postOfficeID;
  @JsonProperty ("sFContactID")
  private String sFContactID;
  @JsonProperty ("payoutMethod")
  private String payoutMethod;
  @JsonProperty ("rejectReason")
  private String rejectReason;
  @JsonProperty ("purposeOfCredit")
  private String purposeOfCredit;
  @JsonProperty ("firstDueDate")
  private String firstDueDate;
  @JsonProperty ("tmxStatus")
  private TmxStatus tmxStatus;
  @JsonProperty ("otherData")
  private OtherData otherData;
  @JsonProperty ("educationAndEmployment")
  private EducationAndEmployment educationAndEmployment;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("ip")
  public String getIp() {
    return this.ip;
  }

  @JsonProperty ("ip")
  public void setIp(final String ip) {
    this.ip = ip;
  }

  @JsonProperty ("postOfficeID")
  public String getPostOfficeID() {
    return this.postOfficeID;
  }

  @JsonProperty ("postOfficeID")
  public void setPostOfficeID(final String postOfficeID) {
    this.postOfficeID = postOfficeID;
  }

  @JsonProperty ("sFContactID")
  public String getSFContactID() {
    return this.sFContactID;
  }

  @JsonProperty ("sFContactID")
  public void setSFContactID(final String sFContactID) {
    this.sFContactID = sFContactID;
  }

  @JsonProperty ("payoutMethod")
  public String getPayoutMethod() {
    return this.payoutMethod;
  }

  @JsonProperty ("payoutMethod")
  public void setPayoutMethod(final String payoutMethod) {
    this.payoutMethod = payoutMethod;
  }

  @JsonProperty ("rejectReason")
  public String getRejectReason() {
    return this.rejectReason;
  }

  @JsonProperty ("rejectReason")
  public void setRejectReason(final String rejectReason) {
    this.rejectReason = rejectReason;
  }

  @JsonProperty ("purposeOfCredit")
  public String getPurposeOfCredit() {
    return this.purposeOfCredit;
  }

  @JsonProperty ("purposeOfCredit")
  public void setPurposeOfCredit(final String purposeOfCredit) {
    this.purposeOfCredit = purposeOfCredit;
  }

  @JsonProperty ("firstDueDate")
  public String getFirstDueDate() {
    return this.firstDueDate;
  }

  @JsonProperty ("firstDueDate")
  public void setFirstDueDate(final String firstDueDate) {
    this.firstDueDate = firstDueDate;
  }

  @JsonProperty ("tmxStatus")
  public TmxStatus getTmxStatus() {
    return this.tmxStatus;
  }

  @JsonProperty ("tmxStatus")
  public void setTmxStatus(final TmxStatus tmxStatus) {
    this.tmxStatus = tmxStatus;
  }

  @JsonProperty ("otherData")
  public OtherData getOtherData() {
    return this.otherData;
  }

  @JsonProperty ("otherData")
  public void setOtherData(final OtherData otherData) {
    this.otherData = otherData;
  }

  @JsonProperty ("educationAndEmployment")
  public EducationAndEmployment getEducationAndEmployment() {
    return this.educationAndEmployment;
  }

  @JsonProperty ("educationAndEmployment")
  public void setEducationAndEmployment(final EducationAndEmployment educationAndEmployment) {
    this.educationAndEmployment = educationAndEmployment;
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
    return new HashCodeBuilder().append(this.ip)
        .append(this.postOfficeID)
        .append(this.sFContactID)
        .append(this.payoutMethod)
        .append(this.rejectReason)
        .append(this.purposeOfCredit)
        .append(this.firstDueDate)
        .append(this.tmxStatus)
        .append(this.otherData)
        .append(this.educationAndEmployment)
        .append(this.additionalProperties)
        .toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof ApplicationData)) {
      return false;
    }
    final ApplicationData rhs = ((ApplicationData) other);
    return new EqualsBuilder().append(this.ip, rhs.ip)
        .append(this.postOfficeID, rhs.postOfficeID)
        .append(this.sFContactID, rhs.sFContactID)
        .append(this.payoutMethod, rhs.payoutMethod)
        .append(this.rejectReason, rhs.rejectReason)
        .append(this.purposeOfCredit, rhs.purposeOfCredit)
        .append(this.firstDueDate, rhs.firstDueDate)
        .append(this.tmxStatus, rhs.tmxStatus)
        .append(this.otherData, rhs.otherData)
        .append(this.educationAndEmployment, rhs.educationAndEmployment)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

  public enum TmxStatus {

    REJECT("REJECT"),
    PASS("PASS"),
    REVIEW("REVIEW");
    private final String value;
    private static final Map<String, TmxStatus> CONSTANTS = new HashMap<>();

    static {
      for (final TmxStatus c : values()) {
        CONSTANTS.put(c.value, c);
      }
    }

    private TmxStatus(final String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.value;
    }

    @JsonValue
    public String value() {
      return this.value;
    }

    @JsonCreator
    public static TmxStatus fromValue(final String value) {
      final TmxStatus constant = CONSTANTS.get(value);
      if (constant == null) {
        throw new IllegalArgumentException(value);
      } else {
        return constant;
      }
    }

  }

}
