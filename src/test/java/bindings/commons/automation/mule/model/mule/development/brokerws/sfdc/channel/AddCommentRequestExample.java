
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
    "applicationId",
    "brokerId",
    "subject",
    "text",
    "author",
    "status"
})
public class AddCommentRequestExample {

  @JsonProperty ("id")
  private String id;
  @JsonProperty ("applicationId")
  private String applicationId;
  @JsonProperty ("brokerId")
  private String brokerId;
  @JsonProperty ("subject")
  private String subject;
  @JsonProperty ("text")
  private String text;
  @JsonProperty ("author")
  private String author;
  @JsonProperty ("status")
  private String status;
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

  @JsonProperty ("applicationId")
  public String getApplicationId() {
    return this.applicationId;
  }

  @JsonProperty ("applicationId")
  public void setApplicationId(final String applicationId) {
    this.applicationId = applicationId;
  }

  @JsonProperty ("brokerId")
  public String getBrokerId() {
    return this.brokerId;
  }

  @JsonProperty ("brokerId")
  public void setBrokerId(final String brokerId) {
    this.brokerId = brokerId;
  }

  @JsonProperty ("subject")
  public String getSubject() {
    return this.subject;
  }

  @JsonProperty ("subject")
  public void setSubject(final String subject) {
    this.subject = subject;
  }

  @JsonProperty ("text")
  public String getText() {
    return this.text;
  }

  @JsonProperty ("text")
  public void setText(final String text) {
    this.text = text;
  }

  @JsonProperty ("author")
  public String getAuthor() {
    return this.author;
  }

  @JsonProperty ("author")
  public void setAuthor(final String author) {
    this.author = author;
  }

  @JsonProperty ("status")
  public String getStatus() {
    return this.status;
  }

  @JsonProperty ("status")
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
    return new HashCodeBuilder().append(this.id).append(this.applicationId).append(this.brokerId)
        .append(
            this.subject)
        .append(this.text).append(this.author).append(this.status).append(this.additionalProperties)
        .toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof AddCommentRequestExample)) {
      return false;
    }
    final AddCommentRequestExample rhs = ((AddCommentRequestExample) other);
    return new EqualsBuilder().append(this.id, rhs.id).append(this.applicationId, rhs.applicationId)
        .append(this.brokerId, rhs.brokerId).append(this.subject, rhs.subject)
        .append(this.text, rhs.text)
        .append(this.author, rhs.author).append(this.status, rhs.status)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
