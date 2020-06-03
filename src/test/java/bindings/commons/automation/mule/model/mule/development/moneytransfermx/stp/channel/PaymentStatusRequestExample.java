
package bindings.commons.automation.mule.model.mule.development.moneytransfermx.stp.channel;

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
    "paymentStatus"
})
public class PaymentStatusRequestExample {

  @JsonProperty ("paymentStatus")
  private PaymentStatus paymentStatus;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("paymentStatus")
  public PaymentStatus getPaymentStatus() {
    return this.paymentStatus;
  }

  @JsonProperty ("paymentStatus")
  public void setPaymentStatus(final PaymentStatus paymentStatus) {
    this.paymentStatus = paymentStatus;
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
    return new HashCodeBuilder().append(this.paymentStatus).append(this.additionalProperties)
        .toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof PaymentStatusRequestExample)) {
      return false;
    }
    final PaymentStatusRequestExample rhs = ((PaymentStatusRequestExample) other);
    return new EqualsBuilder().append(this.paymentStatus, rhs.paymentStatus)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
