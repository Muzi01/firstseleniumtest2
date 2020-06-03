
package bindings.commons.automation.mule.model.mule.development.product.sfdc.channel;

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
    "DocumentRequirement",
    "Status"
})
public class ChangeApplicationProductResponse200Example {

  @JsonProperty ("DocumentRequirement")
  private List<String> documentRequirement = new ArrayList<>();
  @JsonProperty ("Status")
  private String status;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("DocumentRequirement")
  public List<String> getDocumentRequirement() {
    return this.documentRequirement;
  }

  @JsonProperty ("DocumentRequirement")
  public void setDocumentRequirement(final List<String> documentRequirement) {
    this.documentRequirement = documentRequirement;
  }

  @JsonProperty ("Status")
  public String getStatus() {
    return this.status;
  }

  @JsonProperty ("Status")
  public void setStatus(final String status) {
    this.status = status;
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
    return new HashCodeBuilder().append(this.documentRequirement).append(this.status)
        .append(this.additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof ChangeApplicationProductResponse200Example)) {
      return false;
    }
    final ChangeApplicationProductResponse200Example rhs =
        ((ChangeApplicationProductResponse200Example) other);
    return new EqualsBuilder().append(this.documentRequirement, rhs.documentRequirement)
        .append(this.status, rhs.status).append(this.additionalProperties, rhs.additionalProperties)
        .isEquals();
  }

}
