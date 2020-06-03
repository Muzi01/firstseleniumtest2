
package bindings.commons.automation.mule.model.mule.development.case1.sfdc.channel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude (JsonInclude.Include.NON_NULL)
@JsonPropertyOrder ({
    "country",
    "id",
    "parameters",
    "result",
    "user"
})
public class CloseCaseRequest {

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
  @JsonProperty ("id")
  private String id;
  @JsonProperty ("parameters")
  private List<Parameter> parameters = new ArrayList<>();
  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("result")
  private Result result;
  @JsonProperty ("user")
  private String user;

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

  @JsonProperty ("parameters")
  public List<Parameter> getParameters() {
    return this.parameters;
  }

  @JsonProperty ("parameters")
  public void setParameters(final List<Parameter> parameters) {
    this.parameters = parameters;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("result")
  public Result getResult() {
    return this.result;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("result")
  public void setResult(final Result result) {
    this.result = result;
  }

  @JsonProperty ("user")
  public String getUser() {
    return this.user;
  }

  @JsonProperty ("user")
  public void setUser(final String user) {
    this.user = user;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(this.country).append(this.id).append(this.parameters)
        .append(
            this.result)
        .append(this.user).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof CloseCaseRequest)) {
      return false;
    }
    final CloseCaseRequest rhs = ((CloseCaseRequest) other);
    return new EqualsBuilder().append(this.country, rhs.country)
        .append(this.id, rhs.id)
        .append(this.parameters, rhs.parameters)
        .append(this.result, rhs.result)
        .append(this.user, rhs.user).isEquals();
  }

  public enum Result {

    ACCEPTED("Accepted"),
    REJECTED("Rejected");
    private final String value;
    private static final Map<String, Result> CONSTANTS = new HashMap<>();

    static {
      for (final Result c : values()) {
        CONSTANTS.put(c.value, c);
      }
    }

    private Result(final String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.value;
    }

    @JsonValue
    public String value() {
      return this.value;
    }

    @JsonCreator
    public static Result fromValue(final String value) {
      final Result constant = CONSTANTS.get(value);
      if (constant == null) {
        throw new IllegalArgumentException(value);
      } else {
        return constant;
      }
    }

  }

}
