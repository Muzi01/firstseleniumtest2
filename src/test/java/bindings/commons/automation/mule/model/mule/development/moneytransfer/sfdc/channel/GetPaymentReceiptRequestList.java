
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
    "TransferId",
    "TransferAmount",
    "TransferDate",
    "ReceiverAccount"
})
public class GetPaymentReceiptRequestList {

  @JsonProperty ("TransferId")
  private String transferId;
  @JsonProperty ("TransferAmount")
  private String transferAmount;
  @JsonProperty ("TransferDate")
  private String transferDate;
  @JsonProperty ("ReceiverAccount")
  private String receiverAccount;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

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

  @JsonProperty ("TransferDate")
  public String getTransferDate() {
    return this.transferDate;
  }

  @JsonProperty ("TransferDate")
  public void setTransferDate(final String transferDate) {
    this.transferDate = transferDate;
  }

  @JsonProperty ("ReceiverAccount")
  public String getReceiverAccount() {
    return this.receiverAccount;
  }

  @JsonProperty ("ReceiverAccount")
  public void setReceiverAccount(final String receiverAccount) {
    this.receiverAccount = receiverAccount;
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
    return new HashCodeBuilder().append(this.transferId)
        .append(this.transferAmount)
        .append(this.transferDate)
        .append(this.receiverAccount)
        .append(this.additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof GetPaymentReceiptRequestList)) {
      return false;
    }
    final GetPaymentReceiptRequestList rhs = ((GetPaymentReceiptRequestList) other);
    return new EqualsBuilder().append(this.transferId, rhs.transferId)
        .append(this.transferAmount, rhs.transferAmount).append(this.transferDate, rhs.transferDate)
        .append(this.receiverAccount, rhs.receiverAccount)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
