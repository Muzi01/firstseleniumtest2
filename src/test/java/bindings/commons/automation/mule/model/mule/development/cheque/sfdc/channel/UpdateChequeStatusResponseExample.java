
package bindings.commons.automation.mule.model.mule.development.cheque.sfdc.channel;

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
    "cheques"
})
public class UpdateChequeStatusResponseExample {

  @JsonProperty ("cheques")
  private List<ChequeUpdateStatus> cheques = new ArrayList<>();
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("cheques")
  public List<ChequeUpdateStatus> getCheques() {
    return this.cheques;
  }

  @JsonProperty ("cheques")
  public void setCheques(final List<ChequeUpdateStatus> cheques) {
    this.cheques = cheques;
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
    return new HashCodeBuilder().append(this.cheques).append(this.additionalProperties)
        .toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof UpdateChequeStatusResponseExample)) {
      return false;
    }
    final UpdateChequeStatusResponseExample rhs = ((UpdateChequeStatusResponseExample) other);
    return new EqualsBuilder().append(this.cheques, rhs.cheques)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
