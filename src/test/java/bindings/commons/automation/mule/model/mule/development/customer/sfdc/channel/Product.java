
package bindings.commons.automation.mule.model.mule.development.customer.sfdc.channel;

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
    "apr",
    "interest",
    "invoicingFee",
    "maturityPeriods",
    "minimumWithdrawalFee",
    "name",
    "openingFee",
    "principal",
    "requiredScore",
    "requiredScoreRepeat",
    "type",
    "withdrawalFeePercentage",
    "withdrawalFixedFee",
    "brand",
    "groupName",
    "scope",
    "mmp",
    "id"
})
public class Product {

  @JsonProperty ("apr")
  private String apr;
  @JsonProperty ("interest")
  private String interest;
  @JsonProperty ("invoicingFee")
  private String invoicingFee;
  @JsonProperty ("maturityPeriods")
  private String maturityPeriods;
  @JsonProperty ("minimumWithdrawalFee")
  private String minimumWithdrawalFee;
  @JsonProperty ("name")
  private String name;
  @JsonProperty ("openingFee")
  private String openingFee;
  @JsonProperty ("principal")
  private String principal;
  @JsonProperty ("requiredScore")
  private String requiredScore;
  @JsonProperty ("requiredScoreRepeat")
  private String requiredScoreRepeat;
  @JsonProperty ("type")
  private String type;
  @JsonProperty ("withdrawalFeePercentage")
  private String withdrawalFeePercentage;
  @JsonProperty ("withdrawalFixedFee")
  private String withdrawalFixedFee;
  @JsonProperty ("brand")
  private String brand;
  @JsonProperty ("groupName")
  private String groupName;
  @JsonProperty ("scope")
  private String scope;
  @JsonProperty ("mmp")
  private String mmp;
  @JsonProperty ("id")
  private String id;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("apr")
  public String getApr() {
    return this.apr;
  }

  @JsonProperty ("apr")
  public void setApr(final String apr) {
    this.apr = apr;
  }

  @JsonProperty ("interest")
  public String getInterest() {
    return this.interest;
  }

  @JsonProperty ("interest")
  public void setInterest(final String interest) {
    this.interest = interest;
  }

  @JsonProperty ("invoicingFee")
  public String getInvoicingFee() {
    return this.invoicingFee;
  }

  @JsonProperty ("invoicingFee")
  public void setInvoicingFee(final String invoicingFee) {
    this.invoicingFee = invoicingFee;
  }

  @JsonProperty ("maturityPeriods")
  public String getMaturityPeriods() {
    return this.maturityPeriods;
  }

  @JsonProperty ("maturityPeriods")
  public void setMaturityPeriods(final String maturityPeriods) {
    this.maturityPeriods = maturityPeriods;
  }

  @JsonProperty ("minimumWithdrawalFee")
  public String getMinimumWithdrawalFee() {
    return this.minimumWithdrawalFee;
  }

  @JsonProperty ("minimumWithdrawalFee")
  public void setMinimumWithdrawalFee(final String minimumWithdrawalFee) {
    this.minimumWithdrawalFee = minimumWithdrawalFee;
  }

  @JsonProperty ("name")
  public String getName() {
    return this.name;
  }

  @JsonProperty ("name")
  public void setName(final String name) {
    this.name = name;
  }

  @JsonProperty ("openingFee")
  public String getOpeningFee() {
    return this.openingFee;
  }

  @JsonProperty ("openingFee")
  public void setOpeningFee(final String openingFee) {
    this.openingFee = openingFee;
  }

  @JsonProperty ("principal")
  public String getPrincipal() {
    return this.principal;
  }

  @JsonProperty ("principal")
  public void setPrincipal(final String principal) {
    this.principal = principal;
  }

  @JsonProperty ("requiredScore")
  public String getRequiredScore() {
    return this.requiredScore;
  }

  @JsonProperty ("requiredScore")
  public void setRequiredScore(final String requiredScore) {
    this.requiredScore = requiredScore;
  }

  @JsonProperty ("requiredScoreRepeat")
  public String getRequiredScoreRepeat() {
    return this.requiredScoreRepeat;
  }

  @JsonProperty ("requiredScoreRepeat")
  public void setRequiredScoreRepeat(final String requiredScoreRepeat) {
    this.requiredScoreRepeat = requiredScoreRepeat;
  }

  @JsonProperty ("type")
  public String getType() {
    return this.type;
  }

  @JsonProperty ("type")
  public void setType(final String type) {
    this.type = type;
  }

  @JsonProperty ("withdrawalFeePercentage")
  public String getWithdrawalFeePercentage() {
    return this.withdrawalFeePercentage;
  }

  @JsonProperty ("withdrawalFeePercentage")
  public void setWithdrawalFeePercentage(final String withdrawalFeePercentage) {
    this.withdrawalFeePercentage = withdrawalFeePercentage;
  }

  @JsonProperty ("withdrawalFixedFee")
  public String getWithdrawalFixedFee() {
    return this.withdrawalFixedFee;
  }

  @JsonProperty ("withdrawalFixedFee")
  public void setWithdrawalFixedFee(final String withdrawalFixedFee) {
    this.withdrawalFixedFee = withdrawalFixedFee;
  }

  @JsonProperty ("brand")
  public String getBrand() {
    return this.brand;
  }

  @JsonProperty ("brand")
  public void setBrand(final String brand) {
    this.brand = brand;
  }

  @JsonProperty ("groupName")
  public String getGroupName() {
    return this.groupName;
  }

  @JsonProperty ("groupName")
  public void setGroupName(final String groupName) {
    this.groupName = groupName;
  }

  @JsonProperty ("scope")
  public String getScope() {
    return this.scope;
  }

  @JsonProperty ("scope")
  public void setScope(final String scope) {
    this.scope = scope;
  }

  @JsonProperty ("mmp")
  public String getMmp() {
    return this.mmp;
  }

  @JsonProperty ("mmp")
  public void setMmp(final String mmp) {
    this.mmp = mmp;
  }

  @JsonProperty ("id")
  public String getId() {
    return this.id;
  }

  @JsonProperty ("id")
  public void setId(final String id) {
    this.id = id;
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
    return new HashCodeBuilder()
        .append(this.apr).append(this.interest)
        .append(this.invoicingFee).append(this.maturityPeriods)
        .append(this.minimumWithdrawalFee).append(this.name)
        .append(this.openingFee).append(this.principal)
        .append(this.requiredScore).append(this.requiredScoreRepeat)
        .append(this.type).append(this.withdrawalFeePercentage)
        .append(this.withdrawalFixedFee).append(this.brand).append(this.groupName)
        .append(this.scope).append(this.mmp).append(this.id).append(this.additionalProperties)
        .toHashCode();
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
    return new EqualsBuilder().append(this.apr, rhs.apr).append(this.interest, rhs.interest)
        .append(this.invoicingFee, rhs.invoicingFee)
        .append(this.maturityPeriods, rhs.maturityPeriods)
        .append(this.minimumWithdrawalFee, rhs.minimumWithdrawalFee).append(this.name, rhs.name)
        .append(this.openingFee, rhs.openingFee).append(this.principal, rhs.principal)
        .append(this.requiredScore, rhs.requiredScore)
        .append(this.requiredScoreRepeat, rhs.requiredScoreRepeat).append(this.type, rhs.type)
        .append(this.withdrawalFeePercentage, rhs.withdrawalFeePercentage)
        .append(this.withdrawalFixedFee, rhs.withdrawalFixedFee).append(this.brand, rhs.brand)
        .append(this.groupName, rhs.groupName)
        .append(this.scope, rhs.scope).append(this.mmp, rhs.mmp).append(this.id, rhs.id)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
