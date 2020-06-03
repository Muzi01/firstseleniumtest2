
package bindings.commons.automation.mule.model.mule.development.sms.sfdc.channel;

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
    "externalId",
    "country",
    "to",
    "from",
    "text"
})
public class Message {

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("id")
  private String id;
  @JsonProperty ("externalId")
  private String externalId;
  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("country")
  private String country;
  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("to")
  private String to;
  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("from")
  private String from;
  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("text")
  private String text;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("id")
  public String getId() {
    return this.id;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("id")
  public void setId(final String id) {
    this.id = id;
  }

  @JsonProperty ("externalId")
  public String getExternalId() {
    return this.externalId;
  }

  @JsonProperty ("externalId")
  public void setExternalId(final String externalId) {
    this.externalId = externalId;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("country")
  public String getCountry() {
    return this.country;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("country")
  public void setCountry(final String country) {
    this.country = country;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("to")
  public String getTo() {
    return this.to;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("to")
  public void setTo(final String to) {
    this.to = to;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("from")
  public String getFrom() {
    return this.from;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("from")
  public void setFrom(final String from) {
    this.from = from;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("text")
  public String getText() {
    return this.text;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("text")
  public void setText(final String text) {
    this.text = text;
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
    return new HashCodeBuilder().append(this.id).append(this.externalId).append(this.country)
        .append(
            this.to)
        .append(this.from).append(this.text).append(this.additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof Message)) {
      return false;
    }
    final Message rhs = ((Message) other);
    return new EqualsBuilder().append(this.id, rhs.id).append(this.externalId, rhs.externalId)
        .append(this.country, rhs.country).append(this.to, rhs.to).append(this.from, rhs.from)
        .append(this.text, rhs.text).append(this.additionalProperties, rhs.additionalProperties)
        .isEquals();
  }

}
