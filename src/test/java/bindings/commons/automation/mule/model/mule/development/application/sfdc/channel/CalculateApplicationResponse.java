
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
    "product",
    "dti",
    "apr",
    "mmp",
    "lastMmp",
    "totalAmount",
    "totalInterest"
})
public class CalculateApplicationResponse {

  @JsonProperty ("product")
  private Product product;
  @JsonProperty ("dti")
  private String dti;
  @JsonProperty ("apr")
  private String apr;
  @JsonProperty ("mmp")
  private String mmp;
  @JsonProperty ("lastMmp")
  private String lastMmp;
  @JsonProperty ("totalAmount")
  private String totalAmount;
  @JsonProperty ("totalInterest")
  private String totalInterest;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("product")
  public Product getProduct() {
    return this.product;
  }

  @JsonProperty ("product")
  public void setProduct(final Product product) {
    this.product = product;
  }

  @JsonProperty ("dti")
  public String getDti() {
    return this.dti;
  }

  @JsonProperty ("dti")
  public void setDti(final String dti) {
    this.dti = dti;
  }

  @JsonProperty ("apr")
  public String getApr() {
    return this.apr;
  }

  @JsonProperty ("apr")
  public void setApr(final String apr) {
    this.apr = apr;
  }

  @JsonProperty ("mmp")
  public String getMmp() {
    return this.mmp;
  }

  @JsonProperty ("mmp")
  public void setMmp(final String mmp) {
    this.mmp = mmp;
  }

  @JsonProperty ("lastMmp")
  public String getLastMmp() {
    return this.lastMmp;
  }

  @JsonProperty ("lastMmp")
  public void setLastMmp(final String lastMmp) {
    this.lastMmp = lastMmp;
  }

  @JsonProperty ("totalAmount")
  public String getTotalAmount() {
    return this.totalAmount;
  }

  @JsonProperty ("totalAmount")
  public void setTotalAmount(final String totalAmount) {
    this.totalAmount = totalAmount;
  }

  @JsonProperty ("totalInterest")
  public String getTotalInterest() {
    return this.totalInterest;
  }

  @JsonProperty ("totalInterest")
  public void setTotalInterest(final String totalInterest) {
    this.totalInterest = totalInterest;
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
    return new HashCodeBuilder().append(this.product).append(this.dti).append(this.apr).append(
        this.mmp).append(this.lastMmp)
        .append(this.totalAmount).append(this.totalInterest).append(this.additionalProperties)
        .toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof CalculateApplicationResponse)) {
      return false;
    }
    final CalculateApplicationResponse rhs = ((CalculateApplicationResponse) other);
    return new EqualsBuilder().append(this.product, rhs.product).append(this.dti, rhs.dti)
        .append(this.apr, rhs.apr).append(this.mmp, rhs.mmp).append(this.lastMmp, rhs.lastMmp)
        .append(this.totalAmount, rhs.totalAmount).append(this.totalInterest, rhs.totalInterest)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
