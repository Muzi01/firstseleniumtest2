
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude (JsonInclude.Include.NON_NULL)
@JsonPropertyOrder ({
    "RetryTransfersRequestList"
})
public class RetryTransfersRequestExample {

  @JsonProperty ("RetryTransfersRequestList")
  private List<RetryTransfersRequestList> retryTransfersRequestList = new ArrayList<>();
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("RetryTransfersRequestList")
  public List<RetryTransfersRequestList> getRetryTransfersRequestList() {
    return this.retryTransfersRequestList;
  }

  @JsonProperty ("RetryTransfersRequestList")
  public void setRetryTransfersRequestList(
      final List<RetryTransfersRequestList> retryTransfersRequestList) {
    this.retryTransfersRequestList = retryTransfersRequestList;
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
        .append(this.retryTransfersRequestList)
        .append(this.additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof RetryTransfersRequestExample)) {
      return false;
    }
    final RetryTransfersRequestExample rhs = ((RetryTransfersRequestExample) other);
    return new EqualsBuilder()
        .append(this.retryTransfersRequestList, rhs.retryTransfersRequestList)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
