
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
    "archiveId",
    "accountBackendId",
    "applicationBackendId"
})
public class ObligationsReportRequestExample {

  @JsonProperty ("archiveId")
  private String archiveId;
  @JsonProperty ("accountBackendId")
  private String accountBackendId;
  @JsonProperty ("applicationBackendId")
  private String applicationBackendId;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("archiveId")
  public String getArchiveId() {
    return this.archiveId;
  }

  @JsonProperty ("archiveId")
  public void setArchiveId(final String archiveId) {
    this.archiveId = archiveId;
  }

  @JsonProperty ("accountBackendId")
  public String getAccountBackendId() {
    return this.accountBackendId;
  }

  @JsonProperty ("accountBackendId")
  public void setAccountBackendId(final String accountBackendId) {
    this.accountBackendId = accountBackendId;
  }

  @JsonProperty ("applicationBackendId")
  public String getApplicationBackendId() {
    return this.applicationBackendId;
  }

  @JsonProperty ("applicationBackendId")
  public void setApplicationBackendId(final String applicationBackendId) {
    this.applicationBackendId = applicationBackendId;
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
    return new HashCodeBuilder().append(this.archiveId)
        .append(this.accountBackendId)
        .append(this.applicationBackendId)
        .append(this.additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof ObligationsReportRequestExample)) {
      return false;
    }
    final ObligationsReportRequestExample rhs = ((ObligationsReportRequestExample) other);
    return new EqualsBuilder().append(this.archiveId, rhs.archiveId)
        .append(this.accountBackendId, rhs.accountBackendId)
        .append(this.applicationBackendId, rhs.applicationBackendId)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
