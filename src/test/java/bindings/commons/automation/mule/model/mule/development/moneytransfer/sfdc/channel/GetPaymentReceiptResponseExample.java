
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude (JsonInclude.Include.NON_NULL)
@JsonPropertyOrder ({
    "GetPaymentReceiptResponseList"
})
public class GetPaymentReceiptResponseExample {

  @JsonProperty ("GetPaymentReceiptResponseList")
  private List<GetPaymentReceiptResponseList> getPaymentReceiptResponseList = new ArrayList<>();
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("GetPaymentReceiptResponseList")
  public List<GetPaymentReceiptResponseList> getGetPaymentReceiptResponseList() {
    return this.getPaymentReceiptResponseList;
  }

  @JsonProperty ("GetPaymentReceiptResponseList")
  public void setGetPaymentReceiptResponseList(
      final List<GetPaymentReceiptResponseList> getPaymentReceiptResponseList) {
    this.getPaymentReceiptResponseList = getPaymentReceiptResponseList;
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
    return new HashCodeBuilder().append(this.getPaymentReceiptResponseList).append(
        this.additionalProperties)
        .toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof GetPaymentReceiptResponseExample)) {
      return false;
    }
    final GetPaymentReceiptResponseExample rhs = ((GetPaymentReceiptResponseExample) other);
    return new EqualsBuilder()
        .append(this.getPaymentReceiptResponseList, rhs.getPaymentReceiptResponseList)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
