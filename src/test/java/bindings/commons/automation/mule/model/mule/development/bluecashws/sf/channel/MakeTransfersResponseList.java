
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
    "Success",
    "ResultCode",
    "TransferAppId",
    "TransferId"
})
public class MakeTransfersResponseList {

  @JsonProperty ("Success")
  private Boolean success;
  @JsonProperty ("ResultCode")
  private String resultCode;
  @JsonProperty ("TransferAppId")
  private String transferAppId;
  @JsonProperty ("TransferId")
  private String transferId;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("Success")
  public Boolean getSuccess() {
    return this.success;
  }

  @JsonProperty ("Success")
  public void setSuccess(final Boolean success) {
    this.success = success;
  }

  @JsonProperty ("ResultCode")
  public String getResultCode() {
    return this.resultCode;
  }

  @JsonProperty ("ResultCode")
  public void setResultCode(final String resultCode) {
    this.resultCode = resultCode;
  }

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
        .append(this.success)
        .append(this.resultCode)
        .append(this.transferAppId)
        .append(this.transferId)
        .append(this.additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof MakeTransfersResponseList)) {
      return false;
    }
    final MakeTransfersResponseList rhs = ((MakeTransfersResponseList) other);
    return new EqualsBuilder()
        .append(this.success, rhs.success)
        .append(this.resultCode, rhs.resultCode)
        .append(this.transferAppId, rhs.transferAppId)
        .append(this.transferId, rhs.transferId)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
