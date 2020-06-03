
package bindings.commons.automation.mule.model.mule.development.jumiows.aio.channel;

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
    "timestamp",
    "authorizationToken",
    "redirectUrl",
    "transactionReference"
})
public class InitiateJumioResponseExample {

  @JsonProperty ("timestamp")
  private String timestamp;
  @JsonProperty ("authorizationToken")
  private String authorizationToken;
  @JsonProperty ("redirectUrl")
  private String clientRedirectUrl;
  @JsonProperty ("transactionReference")
  private String jumioIdScanReference;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("timestamp")
  public String getTimestamp() {
    return this.timestamp;
  }

  @JsonProperty ("timestamp")
  public void setTimestamp(final String timestamp) {
    this.timestamp = timestamp;
  }

  @JsonProperty ("authorizationToken")
  public String getAuthorizationToken() {
    return this.authorizationToken;
  }

  @JsonProperty ("authorizationToken")
  public void setAuthorizationToken(final String authorizationToken) {
    this.authorizationToken = authorizationToken;
  }

  @JsonProperty ("redirectUrl")
  public String getClientRedirectUrl() {
    return this.clientRedirectUrl;
  }

  @JsonProperty ("redirectUrl")
  public void setClientRedirectUrl(final String clientRedirectUrl) {
    this.clientRedirectUrl = clientRedirectUrl;
  }

  @JsonProperty ("transactionReference")
  public String getJumioIdScanReference() {
    return this.jumioIdScanReference;
  }

  @JsonProperty ("transactionReference")
  public void setJumioIdScanReference(final String jumioIdScanReference) {
    this.jumioIdScanReference = jumioIdScanReference;
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
    return new HashCodeBuilder().append(this.timestamp)
        .append(this.authorizationToken)
        .append(this.clientRedirectUrl)
        .append(this.jumioIdScanReference)
        .append(this.additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof InitiateJumioResponseExample)) {
      return false;
    }
    final InitiateJumioResponseExample rhs = ((InitiateJumioResponseExample) other);
    return new EqualsBuilder()
        .append(this.timestamp, rhs.timestamp)
        .append(this.authorizationToken, rhs.authorizationToken)
        .append(this.clientRedirectUrl, rhs.clientRedirectUrl)
        .append(this.jumioIdScanReference, rhs.jumioIdScanReference)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
