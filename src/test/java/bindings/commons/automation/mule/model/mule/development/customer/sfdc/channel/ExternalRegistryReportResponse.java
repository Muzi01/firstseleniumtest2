
package bindings.commons.automation.mule.model.mule.development.customer.sfdc.channel;

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
    "header",
    "body"
})
public class ExternalRegistryReportResponse {

  @JsonProperty ("header")
  private Header header;
  @JsonProperty ("body")
  private Body body;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("header")
  public Header getHeader() {
    return this.header;
  }

  @JsonProperty ("header")
  public void setHeader(final Header header) {
    this.header = header;
  }

  @JsonProperty ("body")
  public Body getBody() {
    return this.body;
  }

  @JsonProperty ("body")
  public void setBody(final Body body) {
    this.body = body;
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
    return new HashCodeBuilder().append(this.header).append(this.body).append(
        this.additionalProperties)
        .toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof ExternalRegistryReportResponse)) {
      return false;
    }
    final ExternalRegistryReportResponse rhs = ((ExternalRegistryReportResponse) other);
    return new EqualsBuilder()
        .append(this.header, rhs.header)
        .append(this.body, rhs.body)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
