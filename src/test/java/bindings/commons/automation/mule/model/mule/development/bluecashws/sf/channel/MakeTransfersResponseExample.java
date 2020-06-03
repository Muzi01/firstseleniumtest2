
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
    "MakeTransfersResponseList"
})
public class MakeTransfersResponseExample {

  @JsonProperty ("MakeTransfersResponseList")
  private List<MakeTransfersResponseList> makeTransfersResponseList = new ArrayList<>();
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("MakeTransfersResponseList")
  public List<MakeTransfersResponseList> getMakeTransfersResponseList() {
    return this.makeTransfersResponseList;
  }

  @JsonProperty ("MakeTransfersResponseList")
  public void setMakeTransfersResponseList(
      final List<MakeTransfersResponseList> makeTransfersResponseList) {
    this.makeTransfersResponseList = makeTransfersResponseList;
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
    return new HashCodeBuilder().append(this.makeTransfersResponseList).append(
        this.additionalProperties)
        .toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof MakeTransfersResponseExample)) {
      return false;
    }
    final MakeTransfersResponseExample rhs = ((MakeTransfersResponseExample) other);
    return new EqualsBuilder()
        .append(this.makeTransfersResponseList, rhs.makeTransfersResponseList)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
