
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
    "MakeTransfersRequestList"
})
public class MakeTransfersRequestExample {

  @JsonProperty ("MakeTransfersRequestList")
  private List<MakeTransfersRequestList> makeTransfersRequestList = new ArrayList<>();
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("MakeTransfersRequestList")
  public List<MakeTransfersRequestList> getMakeTransfersRequestList() {
    return this.makeTransfersRequestList;
  }

  @JsonProperty ("MakeTransfersRequestList")
  public void setMakeTransfersRequestList(
      final List<MakeTransfersRequestList> makeTransfersRequestList) {
    this.makeTransfersRequestList = makeTransfersRequestList;
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
    return new HashCodeBuilder().append(this.makeTransfersRequestList).append(
        this.additionalProperties)
        .toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof MakeTransfersRequestExample)) {
      return false;
    }
    final MakeTransfersRequestExample rhs = ((MakeTransfersRequestExample) other);
    return new EqualsBuilder().append(this.makeTransfersRequestList, rhs.makeTransfersRequestList)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
