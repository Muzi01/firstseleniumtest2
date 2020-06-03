
package bindings.commons.automation.mule.model.mule.development.bluecashws.sf.channel;

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
    "Amount",
    "Title",
    "ReceiverAccount",
    "ReceiverName",
    "ReceiverAddress",
    "Currency",
    "SenderAccount",
    "SenderName",
    "SenderAddress",
    "SenderMobile",
    "SenderEmail",
    "BlueCashTransfer"
})
public class MakeTransfersRequestList {

  @JsonProperty ("TransferAppId")
  private String transferAppId;
  @JsonProperty ("Amount")
  private String amount;
  @JsonProperty ("Title")
  private String title;
  @JsonProperty ("ReceiverAccount")
  private String receiverAccount;
  @JsonProperty ("ReceiverName")
  private String receiverName;
  @JsonProperty ("ReceiverAddress")
  private String receiverAddress;
  @JsonProperty ("Currency")
  private String currency;
  @JsonProperty ("SenderAccount")
  private String senderAccount;
  @JsonProperty ("SenderName")
  private String senderName;
  @JsonProperty ("SenderAddress")
  private String senderAddress;
  @JsonProperty ("SenderMobile")
  private String senderMobile;
  @JsonProperty ("SenderEmail")
  private String senderEmail;
  @JsonProperty ("BlueCashTransfer")
  private Boolean blueCashTransfer;
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

  @JsonProperty ("Amount")
  public String getAmount() {
    return this.amount;
  }

  @JsonProperty ("Amount")
  public void setAmount(final String amount) {
    this.amount = amount;
  }

  @JsonProperty ("Title")
  public String getTitle() {
    return this.title;
  }

  @JsonProperty ("Title")
  public void setTitle(final String title) {
    this.title = title;
  }

  @JsonProperty ("ReceiverAccount")
  public String getReceiverAccount() {
    return this.receiverAccount;
  }

  @JsonProperty ("ReceiverAccount")
  public void setReceiverAccount(final String receiverAccount) {
    this.receiverAccount = receiverAccount;
  }

  @JsonProperty ("ReceiverName")
  public String getReceiverName() {
    return this.receiverName;
  }

  @JsonProperty ("ReceiverName")
  public void setReceiverName(final String receiverName) {
    this.receiverName = receiverName;
  }

  @JsonProperty ("ReceiverAddress")
  public String getReceiverAddress() {
    return this.receiverAddress;
  }

  @JsonProperty ("ReceiverAddress")
  public void setReceiverAddress(final String receiverAddress) {
    this.receiverAddress = receiverAddress;
  }

  @JsonProperty ("Currency")
  public String getCurrency() {
    return this.currency;
  }

  @JsonProperty ("Currency")
  public void setCurrency(final String currency) {
    this.currency = currency;
  }

  @JsonProperty ("SenderAccount")
  public String getSenderAccount() {
    return this.senderAccount;
  }

  @JsonProperty ("SenderAccount")
  public void setSenderAccount(final String senderAccount) {
    this.senderAccount = senderAccount;
  }

  @JsonProperty ("SenderName")
  public String getSenderName() {
    return this.senderName;
  }

  @JsonProperty ("SenderName")
  public void setSenderName(final String senderName) {
    this.senderName = senderName;
  }

  @JsonProperty ("SenderAddress")
  public String getSenderAddress() {
    return this.senderAddress;
  }

  @JsonProperty ("SenderAddress")
  public void setSenderAddress(final String senderAddress) {
    this.senderAddress = senderAddress;
  }

  @JsonProperty ("SenderMobile")
  public String getSenderMobile() {
    return this.senderMobile;
  }

  @JsonProperty ("SenderMobile")
  public void setSenderMobile(final String senderMobile) {
    this.senderMobile = senderMobile;
  }

  @JsonProperty ("SenderEmail")
  public String getSenderEmail() {
    return this.senderEmail;
  }

  @JsonProperty ("SenderEmail")
  public void setSenderEmail(final String senderEmail) {
    this.senderEmail = senderEmail;
  }

  @JsonProperty ("BlueCashTransfer")
  public Boolean getBlueCashTransfer() {
    return this.blueCashTransfer;
  }

  @JsonProperty ("BlueCashTransfer")
  public void setBlueCashTransfer(final Boolean blueCashTransfer) {
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
    return new HashCodeBuilder()
        .append(this.transferAppId).append(this.amount)
        .append(this.title).append(this.receiverAccount)
        .append(this.receiverName).append(this.receiverAddress)
        .append(this.currency).append(this.senderAccount)
        .append(this.senderName).append(this.senderAddress)
        .append(this.senderMobile).append(this.senderEmail)
        .append(this.blueCashTransfer).append(this.additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof MakeTransfersRequestList)) {
      return false;
    }
    final MakeTransfersRequestList rhs = ((MakeTransfersRequestList) other);
    return new EqualsBuilder()
        .append(this.transferAppId, rhs.transferAppId).append(this.amount, rhs.amount)
        .append(this.title, rhs.title)
        .append(this.receiverAccount, rhs.receiverAccount)
        .append(this.receiverName, rhs.receiverName)
        .append(this.receiverAddress, rhs.receiverAddress)
        .append(this.currency, rhs.currency).append(this.senderAccount, rhs.senderAccount)
        .append(this.senderName, rhs.senderName)
        .append(this.senderAddress, rhs.senderAddress).append(this.senderMobile, rhs.senderMobile)
        .append(this.senderEmail, rhs.senderEmail)
        .append(this.blueCashTransfer, rhs.blueCashTransfer)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
