
package bindings.commons.automation.mule.model.mule.development.cheque.sfdc.channel;

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
    "chequeId",
    "payoutAmount",
    "personalDocumentNumber",
    "chequeStatus",
    "transactionDate",
    "SSN",
    "customerName",
    "customerSurname",
    "additionalIdentifier"
})
public class ChequeSendStatus {

  @JsonProperty ("chequeId")
  private String chequeId;
  @JsonProperty ("payoutAmount")
  private String payoutAmount;
  @JsonProperty ("personalDocumentNumber")
  private String personalDocumentNumber;
  @JsonProperty ("chequeStatus")
  private String chequeStatus;
  @JsonProperty ("transactionDate")
  private String transactionDate;
  @JsonProperty ("SSN")
  private String sSN;
  @JsonProperty ("customerName")
  private String customerName;
  @JsonProperty ("customerSurname")
  private String customerSurname;
  @JsonProperty ("additionalIdentifier")
  private String additionalIdentifier;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("chequeId")
  public String getChequeId() {
    return this.chequeId;
  }

  @JsonProperty ("chequeId")
  public void setChequeId(final String chequeId) {
    this.chequeId = chequeId;
  }

  @JsonProperty ("payoutAmount")
  public String getPayoutAmount() {
    return this.payoutAmount;
  }

  @JsonProperty ("payoutAmount")
  public void setPayoutAmount(final String payoutAmount) {
    this.payoutAmount = payoutAmount;
  }

  @JsonProperty ("personalDocumentNumber")
  public String getPersonalDocumentNumber() {
    return this.personalDocumentNumber;
  }

  @JsonProperty ("personalDocumentNumber")
  public void setPersonalDocumentNumber(final String personalDocumentNumber) {
    this.personalDocumentNumber = personalDocumentNumber;
  }

  @JsonProperty ("chequeStatus")
  public String getChequeStatus() {
    return this.chequeStatus;
  }

  @JsonProperty ("chequeStatus")
  public void setChequeStatus(final String chequeStatus) {
    this.chequeStatus = chequeStatus;
  }

  @JsonProperty ("transactionDate")
  public String getTransactionDate() {
    return this.transactionDate;
  }

  @JsonProperty ("transactionDate")
  public void setTransactionDate(final String transactionDate) {
    this.transactionDate = transactionDate;
  }

  @JsonProperty ("SSN")
  public String getSSN() {
    return this.sSN;
  }

  @JsonProperty ("SSN")
  public void setSSN(final String sSN) {
    this.sSN = sSN;
  }

  @JsonProperty ("customerName")
  public String getCustomerName() {
    return this.customerName;
  }

  @JsonProperty ("customerName")
  public void setCustomerName(final String customerName) {
    this.customerName = customerName;
  }

  @JsonProperty ("customerSurname")
  public String getCustomerSurname() {
    return this.customerSurname;
  }

  @JsonProperty ("customerSurname")
  public void setCustomerSurname(final String customerSurname) {
    this.customerSurname = customerSurname;
  }

  @JsonProperty ("additionalIdentifier")
  public String getAdditionalIdentifier() {
    return this.additionalIdentifier;
  }

  @JsonProperty ("additionalIdentifier")
  public void setAdditionalIdentifier(final String additionalIdentifier) {
    this.additionalIdentifier = additionalIdentifier;
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
    return new HashCodeBuilder().append(this.chequeId).append(this.payoutAmount)
        .append(this.personalDocumentNumber).append(this.chequeStatus).append(this.transactionDate)
        .append(
            this.sSN)
        .append(this.customerName).append(this.customerSurname).append(this.additionalIdentifier)
        .append(this.additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof ChequeSendStatus)) {
      return false;
    }
    final ChequeSendStatus rhs = ((ChequeSendStatus) other);
    return new EqualsBuilder().append(this.chequeId, rhs.chequeId)
        .append(this.payoutAmount, rhs.payoutAmount)
        .append(this.personalDocumentNumber, rhs.personalDocumentNumber)
        .append(this.chequeStatus, rhs.chequeStatus)
        .append(this.transactionDate, rhs.transactionDate)
        .append(this.sSN, rhs.sSN).append(this.customerName, rhs.customerName)
        .append(this.customerSurname, rhs.customerSurname)
        .append(this.additionalIdentifier, rhs.additionalIdentifier)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
