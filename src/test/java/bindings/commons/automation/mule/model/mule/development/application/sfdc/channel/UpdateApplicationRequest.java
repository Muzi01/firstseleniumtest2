
package bindings.commons.automation.mule.model.mule.development.application.sfdc.channel;

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
    "brokerId",
    "agentId",
    "applicationId",
    "productId",
    "channel",
    "processor",
    "state",
    "applicationData"
})
public class UpdateApplicationRequest {

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("countryCode")
  private String countryCode;
  @JsonProperty ("brokerId")
  private String brokerId;
  @JsonProperty ("agentId")
  private String agentId;
  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("applicationId")
  private String applicationId;
  @JsonProperty ("productId")
  private String productId;
  @JsonProperty ("channel")
  private String channel;
  @JsonProperty ("processor")
  private String processor;
  @JsonProperty ("state")
  private String state;
  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("applicationData")
  private ApplicationData applicationData;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("countryCode")
  public String getCountryCode() {
    return this.countryCode;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("countryCode")
  public void setCountryCode(final String countryCode) {
    this.countryCode = countryCode;
  }

  @JsonProperty ("brokerId")
  public String getBrokerId() {
    return this.brokerId;
  }

  @JsonProperty ("brokerId")
  public void setBrokerId(final String brokerId) {
    this.brokerId = brokerId;
  }

  @JsonProperty ("agentId")
  public String getAgentId() {
    return this.agentId;
  }

  @JsonProperty ("agentId")
  public void setAgentId(final String agentId) {
    this.agentId = agentId;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("applicationId")
  public String getApplicationId() {
    return this.applicationId;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("applicationId")
  public void setApplicationId(final String applicationId) {
    this.applicationId = applicationId;
  }

  @JsonProperty ("productId")
  public String getProductId() {
    return this.productId;
  }

  @JsonProperty ("productId")
  public void setProductId(final String productId) {
    this.productId = productId;
  }

  @JsonProperty ("channel")
  public String getChannel() {
    return this.channel;
  }

  @JsonProperty ("channel")
  public void setChannel(final String channel) {
    this.channel = channel;
  }

  @JsonProperty ("processor")
  public String getProcessor() {
    return this.processor;
  }

  @JsonProperty ("processor")
  public void setProcessor(final String processor) {
    this.processor = processor;
  }

  @JsonProperty ("state")
  public String getState() {
    return this.state;
  }

  @JsonProperty ("state")
  public void setState(final String state) {
    this.state = state;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("applicationData")
  public ApplicationData getApplicationData() {
    return this.applicationData;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("applicationData")
  public void setApplicationData(final ApplicationData applicationData) {
    this.applicationData = applicationData;
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
    return new HashCodeBuilder().append(this.countryCode).append(this.brokerId).append(this.agentId)
        .append(this.applicationId).append(this.productId).append(this.channel).append(
            this.processor)
        .append(this.state)
        .append(this.applicationData).append(this.additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof UpdateApplicationRequest)) {
      return false;
    }
    final UpdateApplicationRequest rhs = ((UpdateApplicationRequest) other);
    return new EqualsBuilder().append(this.countryCode, rhs.countryCode)
        .append(this.brokerId, rhs.brokerId)
        .append(this.agentId, rhs.agentId).append(this.applicationId, rhs.applicationId)
        .append(this.productId, rhs.productId).append(this.channel, rhs.channel)
        .append(this.processor, rhs.processor).append(this.state, rhs.state)
        .append(this.applicationData, rhs.applicationData)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
