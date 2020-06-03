
package bindings.commons.automation.mule.model.mule.development.case1.sfdc.channel;

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
    "dti",
    "maturityPeriods",
    "minRefinancedAmount",
    "mmp",
    "principal",
    "id",
    "type"
})
public class Product {

  @JsonProperty ("dti")
  private Double dti;
  @JsonProperty ("maturityPeriods")
  private Integer maturityPeriods;
  @JsonProperty ("minRefinancedAmount")
  private Double minRefinancedAmount;
  @JsonProperty ("mmp")
  private Double mmp;
  @JsonProperty ("principal")
  private Double principal;
  @JsonProperty ("id")
  private String id;
  @JsonProperty ("type")
  private String type;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("dti")
  public Double getDti() {
    return this.dti;
  }

  @JsonProperty ("dti")
  public void setDti(final Double dti) {
    this.dti = dti;
  }

  @JsonProperty ("maturityPeriods")
  public Integer getMaturityPeriods() {
    return this.maturityPeriods;
  }

  @JsonProperty ("maturityPeriods")
  public void setMaturityPeriods(final Integer maturityPeriods) {
    this.maturityPeriods = maturityPeriods;
  }

  @JsonProperty ("minRefinancedAmount")
  public Double getMinRefinancedAmount() {
    return this.minRefinancedAmount;
  }

  @JsonProperty ("minRefinancedAmount")
  public void setMinRefinancedAmount(final Double minRefinancedAmount) {
    this.minRefinancedAmount = minRefinancedAmount;
  }

  @JsonProperty ("mmp")
  public Double getMmp() {
    return this.mmp;
  }

  @JsonProperty ("mmp")
  public void setMmp(final Double mmp) {
    this.mmp = mmp;
  }

  @JsonProperty ("principal")
  public Double getPrincipal() {
    return this.principal;
  }

  @JsonProperty ("principal")
  public void setPrincipal(final Double principal) {
    this.principal = principal;
  }

  @JsonProperty ("id")
  public String getId() {
    return this.id;
  }

  @JsonProperty ("id")
  public void setId(final String id) {
    this.id = id;
  }

  @JsonProperty ("type")
  public String getType() {
    return this.type;
  }

  @JsonProperty ("type")
  public void setType(final String type) {
    this.type = type;
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
    return new HashCodeBuilder().append(this.dti)
        .append(this.maturityPeriods)
        .append(this.minRefinancedAmount)
        .append(this.mmp).append(this.principal)
        .append(this.id).append(this.type)
        .append(this.additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof Product)) {
      return false;
    }
    final Product rhs = ((Product) other);
    return new EqualsBuilder().append(this.dti, rhs.dti)
        .append(this.maturityPeriods, rhs.maturityPeriods)
        .append(this.minRefinancedAmount, rhs.minRefinancedAmount)
        .append(this.mmp, rhs.mmp).append(this.principal, rhs.principal)
        .append(this.id, rhs.id).append(this.type, rhs.type)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
