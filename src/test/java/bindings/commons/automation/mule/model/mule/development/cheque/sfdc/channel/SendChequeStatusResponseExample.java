
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

import java.util.HashMap;
import java.util.Map;

@JsonInclude (JsonInclude.Include.NON_NULL)
@JsonPropertyOrder ({
    "fileName"
})
public class SendChequeStatusResponseExample {

  @JsonProperty ("fileName")
  private String fileName;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("fileName")
  public String getFileName() {
    return this.fileName;
  }

  @JsonProperty ("fileName")
  public void setFileName(final String fileName) {
    this.fileName = fileName;
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
    return new HashCodeBuilder().append(this.fileName).append(this.additionalProperties)
        .toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof SendChequeStatusResponseExample)) {
      return false;
    }
    final SendChequeStatusResponseExample rhs = ((SendChequeStatusResponseExample) other);
    return new EqualsBuilder().append(this.fileName, rhs.fileName)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
