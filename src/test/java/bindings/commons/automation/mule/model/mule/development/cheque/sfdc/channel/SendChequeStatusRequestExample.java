
package bindings.commons.automation.mule.model.mule.development.cheque.sfdc.channel;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude (JsonInclude.Include.NON_NULL)
@JsonPropertyOrder ({
    "reportNumber",
    "cheques"
})
public class SendChequeStatusRequestExample {

  @JsonProperty ("reportNumber")
  private String reportNumber;
  @JsonProperty ("cheques")
  private List<ChequeSendStatus> cheques = new ArrayList<>();
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("reportNumber")
  public String getReportNumber() {
    return this.reportNumber;
  }

  @JsonProperty ("reportNumber")
  public void setReportNumber(final String reportNumber) {
    this.reportNumber = reportNumber;
  }

  @JsonProperty ("cheques")
  public List<ChequeSendStatus> getCheques() {
    return this.cheques;
  }

  @JsonProperty ("cheques")
  public void setCheques(final List<ChequeSendStatus> cheques) {
    this.cheques = cheques;
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
    return new HashCodeBuilder().append(this.reportNumber).append(this.cheques).append(
        this.additionalProperties)
        .toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof SendChequeStatusRequestExample)) {
      return false;
    }
    final SendChequeStatusRequestExample rhs = ((SendChequeStatusRequestExample) other);
    return new EqualsBuilder().append(this.reportNumber, rhs.reportNumber)
        .append(this.cheques, rhs.cheques)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
