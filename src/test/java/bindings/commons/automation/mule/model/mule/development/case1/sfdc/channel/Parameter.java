
package bindings.commons.automation.mule.model.mule.development.case1.sfdc.channel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude (JsonInclude.Include.NON_NULL)
@JsonPropertyOrder ({
    "key",
    "value"
})
public class Parameter {

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("key")
  private String key;
  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("value")
  private String value;

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("key")
  public String getKey() {
    return this.key;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("key")
  public void setKey(final String key) {
    this.key = key;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("value")
  public String getValue() {
    return this.value;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("value")
  public void setValue(final String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(this.key).append(this.value).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof Parameter)) {
      return false;
    }
    final Parameter rhs = ((Parameter) other);
    return new EqualsBuilder().append(this.key, rhs.key).append(this.value, rhs.value).isEquals();
  }

}
