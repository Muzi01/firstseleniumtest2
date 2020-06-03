
package bindings.commons.automation.mule.model.mule.development.moneytransfer.sfdc.channel;

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
    "TransferAppId",
    "TransferId",
    "TransferAmount",
    "Currency",
    "TransferStatus",
    "BlueCashTransfer"
})
public class RetryTransfersRequestList {

  @JsonProperty ("TransferAppId")
  private String transferAppId;
  @JsonProperty ("TransferId")
  private String transferId;
  @JsonProperty ("TransferAmount")
  private String transferAmount;
  @JsonProperty ("Currency")
  private String currency;
  @JsonProperty ("TransferStatus")
  private String transferStatus;
  @JsonProperty ("BlueCashTransfer")
  private String blueCashTransfer;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("TransferAppId")
  public String getTransferAppId() {
    return this.transferAppId;
  }

  @JsonProperty ("TransferAppId")
  public void setTransferAppId(final String transferAppId) {
    this.transferAppId = transferAppId;
  }

  @JsonProperty ("TransferId")
  public String getTransferId() {
    return this.transferId;
  }

  @JsonProperty ("TransferId")
  public void setTransferId(final String transferId) {
    this.transferId = transferId;
  }

  @JsonProperty ("TransferAmount")
  public String getTransferAmount() {
    return this.transferAmount;
  }

  @JsonProperty ("TransferAmount")
  public void setTransferAmount(final String transferAmount) {
    this.transferAmount = transferAmount;
  }

  @JsonProperty ("Currency")
  public String getCurrency() {
    return this.currency;
  }

  @JsonProperty ("Currency")
  public void setCurrency(final String currency) {
    this.currency = currency;
  }

  @JsonProperty ("TransferStatus")
  public String getTransferStatus() {
    return this.transferStatus;
  }

  @JsonProperty ("TransferStatus")
  public void setTransferStatus(final String transferStatus) {
    this.transferStatus = transferStatus;
  }

  @JsonProperty ("BlueCashTransfer")
  public String getBlueCashTransfer() {
    return this.blueCashTransfer;
  }

  @JsonProperty ("BlueCashTransfer")
  public void setBlueCashTransfer(final String blueCashTransfer) {
    this.blueCashTransfer = blueCashTransfer;
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
    return new HashCodeBuilder().append(this.transferAppId).append(this.transferId).append(
        this.transferAmount)
        .append(this.currency).append(this.transferStatus).append(this.blueCashTransfer)
        .append(this.additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof RetryTransfersRequestList)) {
      return false;
    }
    final RetryTransfersRequestList rhs = ((RetryTransfersRequestList) other);
    return new EqualsBuilder().append(this.transferAppId, rhs.transferAppId)
        .append(this.transferId, rhs.transferId).append(this.transferAmount, rhs.transferAmount)
        .append(this.currency, rhs.currency).append(this.transferStatus, rhs.transferStatus)
        .append(this.blueCashTransfer, rhs.blueCashTransfer)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
