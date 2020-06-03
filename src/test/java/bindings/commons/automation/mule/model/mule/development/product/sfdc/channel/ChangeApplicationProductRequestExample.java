
package bindings.commons.automation.mule.model.mule.development.product.sfdc.channel;

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
    "Country",
    "CreditApplication_ID",
    "FirstDrawAmount",
    "Product_ID",
    "User"
})
public class ChangeApplicationProductRequestExample {

  @JsonProperty ("Country")
  private String country;
  @JsonProperty ("CreditApplication_ID")
  private String creditApplicationID;
  @JsonProperty ("FirstDrawAmount")
  private String firstDrawAmount;
  @JsonProperty ("Product_ID")
  private String productID;
  @JsonProperty ("User")
  private String user;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("Country")
  public String getCountry() {
    return this.country;
  }

  @JsonProperty ("Country")
  public void setCountry(final String country) {
    this.country = country;
  }

  @JsonProperty ("CreditApplication_ID")
  public String getCreditApplicationID() {
    return this.creditApplicationID;
  }

  @JsonProperty ("CreditApplication_ID")
  public void setCreditApplicationID(final String creditApplicationID) {
    this.creditApplicationID = creditApplicationID;
  }

  @JsonProperty ("FirstDrawAmount")
  public String getFirstDrawAmount() {
    return this.firstDrawAmount;
  }

  @JsonProperty ("FirstDrawAmount")
  public void setFirstDrawAmount(final String firstDrawAmount) {
    this.firstDrawAmount = firstDrawAmount;
  }

  @JsonProperty ("Product_ID")
  public String getProductID() {
    return this.productID;
  }

  @JsonProperty ("Product_ID")
  public void setProductID(final String productID) {
    this.productID = productID;
  }

  @JsonProperty ("User")
  public String getUser() {
    return this.user;
  }

  @JsonProperty ("User")
  public void setUser(final String user) {
    this.user = user;
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
    return new HashCodeBuilder().append(this.country).append(this.creditApplicationID).append(
        this.firstDrawAmount)
        .append(this.productID).append(this.user).append(this.additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof ChangeApplicationProductRequestExample)) {
      return false;
    }
    final ChangeApplicationProductRequestExample rhs =
        ((ChangeApplicationProductRequestExample) other);
    return new EqualsBuilder().append(this.country, rhs.country)
        .append(this.creditApplicationID, rhs.creditApplicationID)
        .append(this.firstDrawAmount, rhs.firstDrawAmount).append(this.productID, rhs.productID)
        .append(this.user, rhs.user).append(this.additionalProperties, rhs.additionalProperties)
        .isEquals();
  }

}
