
package bindings.commons.automation.mule.model.mule.development.verificationtransfer.sfdc.channel;

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
    "countryCode",
    "file"
})
public class UploadVerificationTransferRequestExample {

  @JsonProperty ("countryCode")
  private String countryCode;
  @JsonProperty ("file")
  private String file;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("countryCode")
  public String getCountryCode() {
    return this.countryCode;
  }

  @JsonProperty ("countryCode")
  public void setCountryCode(final String countryCode) {
    this.countryCode = countryCode;
  }

  @JsonProperty ("file")
  public String getFile() {
    return this.file;
  }

  @JsonProperty ("file")
  public void setFile(final String file) {
    this.file = file;
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
    return new HashCodeBuilder().append(this.countryCode).append(this.file).append(
        this.additionalProperties)
        .toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof UploadVerificationTransferRequestExample)) {
      return false;
    }
    final UploadVerificationTransferRequestExample rhs =
        ((UploadVerificationTransferRequestExample) other);
    return new EqualsBuilder().append(this.countryCode, rhs.countryCode).append(this.file, rhs.file)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
