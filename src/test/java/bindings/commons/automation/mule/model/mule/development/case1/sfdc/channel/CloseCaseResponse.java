
package bindings.commons.automation.mule.model.mule.development.case1.sfdc.channel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude (JsonInclude.Include.NON_NULL)
@JsonPropertyOrder ({
    "reason",
    "success"
})
public class CloseCaseResponse {

  @JsonProperty ("reason")
  private String reason;
  @JsonProperty ("success")
  private Boolean success;

  @JsonProperty ("reason")
  public String getReason() {
    return this.reason;
  }

  @JsonProperty ("reason")
  public void setReason(final String reason) {
    this.reason = reason;
  }

  @JsonProperty ("success")
  public Boolean getSuccess() {
    return this.success;
  }

  @JsonProperty ("success")
  public void setSuccess(final Boolean success) {
    this.success = success;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(this.reason).append(this.success).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof CloseCaseResponse)) {
      return false;
    }
    final CloseCaseResponse rhs = ((CloseCaseResponse) other);
    return new EqualsBuilder().append(this.reason, rhs.reason).append(this.success, rhs.success)
        .isEquals();
  }

}
