
package bindings.commons.automation.mule.model.mule.development.extraservices.aio.channel.deactivateextraservices;

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
    "type",
    "properties",
    "required"
})
public class Body {

  @JsonProperty ("type")
  private String type;
  @JsonProperty ("properties")
  private Properties__ properties;
  @JsonProperty ("required")
  private List<String> required = new ArrayList<>();
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("type")
  public String getType() {
    return this.type;
  }

  @JsonProperty ("type")
  public void setType(final String type) {
    this.type = type;
  }

  @JsonProperty ("properties")
  public Properties__ getProperties() {
    return this.properties;
  }

  @JsonProperty ("properties")
  public void setProperties(final Properties__ properties) {
    this.properties = properties;
  }

  @JsonProperty ("required")
  public List<String> getRequired() {
    return this.required;
  }

  @JsonProperty ("required")
  public void setRequired(final List<String> required) {
    this.required = required;
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
    return new HashCodeBuilder().append(this.type).append(this.properties).append(this.required)
        .append(this.additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof Body)) {
      return false;
    }
    final Body rhs = ((Body) other);
    return new EqualsBuilder().append(this.type, rhs.type).append(this.properties, rhs.properties)
        .append(this.required, rhs.required)
        .append(this.additionalProperties, rhs.additionalProperties)
        .isEquals();
  }

}
