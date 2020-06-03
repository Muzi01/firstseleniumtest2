
package bindings.commons.automation.mule.model.mule.development.brokerws.sfdc.channel;

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
    "id",
    "status",
    "success"
})
public class AddCommentResponseExample {

  @JsonProperty ("id")
  private String id;
  @JsonProperty ("status")
  private String status;
  @JsonProperty ("success")
  private String success;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("id")
  public String getId() {
    return this.id;
  }

  @JsonProperty ("id")
  public void setId(final String id) {
    this.id = id;
  }

  @JsonProperty ("status")
  public String getStatus() {
    return this.status;
  }

  @JsonProperty ("status")
  public void setStatus(final String status) {
    this.status = status;
  }

  @JsonProperty ("success")
  public String getSuccess() {
    return this.success;
  }

  @JsonProperty ("success")
  public void setSuccess(final String success) {
    this.success = success;
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
    return new HashCodeBuilder().append(this.id).append(this.status).append(this.success)
        .append(this.additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof AddCommentResponseExample)) {
      return false;
    }
    final AddCommentResponseExample rhs = ((AddCommentResponseExample) other);
    return new EqualsBuilder().append(this.id, rhs.id).append(this.status, rhs.status)
        .append(this.success, rhs.success)
        .append(this.additionalProperties, rhs.additionalProperties)
        .isEquals();
  }

}
