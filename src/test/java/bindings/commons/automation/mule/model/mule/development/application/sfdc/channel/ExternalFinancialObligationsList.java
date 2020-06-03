
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
    "backendId",
    "company",
    "bankAccountNumber",
    "startDate",
    "endDate",
    "amount",
    "outstandingAmount",
    "mmp",
    "paymentsLeft",
    "overdueAmount",
    "overdueInstallmentCount",
    "overdueDays",
    "installmentCount",
    "installmentAmount",
    "typeOfContract",
    "lastUpdated",
    "creditor",
    "contractIdentifier",
    "selected"
})
public class ExternalFinancialObligationsList {

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("backendId")
  private String backendId;
  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("company")
  private String company;
  @JsonProperty ("bankAccountNumber")
  private String bankAccountNumber;
  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("startDate")
  private String startDate;
  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("endDate")
  private String endDate;
  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("amount")
  private Integer amount;
  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("outstandingAmount")
  private Integer outstandingAmount;
  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("mmp")
  private Integer mmp;
  @JsonProperty ("paymentsLeft")
  private Integer paymentsLeft;
  @JsonProperty ("overdueAmount")
  private Integer overdueAmount;
  @JsonProperty ("overdueInstallmentCount")
  private Integer overdueInstallmentCount;
  @JsonProperty ("overdueDays")
  private Integer overdueDays;
  @JsonProperty ("installmentCount")
  private Integer installmentCount;
  @JsonProperty ("installmentAmount")
  private Integer installmentAmount;
  @JsonProperty ("typeOfContract")
  private String typeOfContract;
  @JsonProperty ("lastUpdated")
  private String lastUpdated;
  @JsonProperty ("creditor")
  private String creditor;
  @JsonProperty ("contractIdentifier")
  private String contractIdentifier;
  @JsonProperty ("selected")
  private Boolean selected;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("backendId")
  public String getBackendId() {
    return this.backendId;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("backendId")
  public void setBackendId(final String backendId) {
    this.backendId = backendId;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("company")
  public String getCompany() {
    return this.company;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("company")
  public void setCompany(final String company) {
    this.company = company;
  }

  @JsonProperty ("bankAccountNumber")
  public String getBankAccountNumber() {
    return this.bankAccountNumber;
  }

  @JsonProperty ("bankAccountNumber")
  public void setBankAccountNumber(final String bankAccountNumber) {
    this.bankAccountNumber = bankAccountNumber;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("startDate")
  public String getStartDate() {
    return this.startDate;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("startDate")
  public void setStartDate(final String startDate) {
    this.startDate = startDate;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("endDate")
  public String getEndDate() {
    return this.endDate;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("endDate")
  public void setEndDate(final String endDate) {
    this.endDate = endDate;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("amount")
  public Integer getAmount() {
    return this.amount;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("amount")
  public void setAmount(final Integer amount) {
    this.amount = amount;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("outstandingAmount")
  public Integer getOutstandingAmount() {
    return this.outstandingAmount;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("outstandingAmount")
  public void setOutstandingAmount(final Integer outstandingAmount) {
    this.outstandingAmount = outstandingAmount;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("mmp")
  public Integer getMmp() {
    return this.mmp;
  }

  /**
   * 
   * (Required)
   * 
   */
  @JsonProperty ("mmp")
  public void setMmp(final Integer mmp) {
    this.mmp = mmp;
  }

  @JsonProperty ("paymentsLeft")
  public Integer getPaymentsLeft() {
    return this.paymentsLeft;
  }

  @JsonProperty ("paymentsLeft")
  public void setPaymentsLeft(final Integer paymentsLeft) {
    this.paymentsLeft = paymentsLeft;
  }

  @JsonProperty ("overdueAmount")
  public Integer getOverdueAmount() {
    return this.overdueAmount;
  }

  @JsonProperty ("overdueAmount")
  public void setOverdueAmount(final Integer overdueAmount) {
    this.overdueAmount = overdueAmount;
  }

  @JsonProperty ("overdueInstallmentCount")
  public Integer getOverdueInstallmentCount() {
    return this.overdueInstallmentCount;
  }

  @JsonProperty ("overdueInstallmentCount")
  public void setOverdueInstallmentCount(final Integer overdueInstallmentCount) {
    this.overdueInstallmentCount = overdueInstallmentCount;
  }

  @JsonProperty ("overdueDays")
  public Integer getOverdueDays() {
    return this.overdueDays;
  }

  @JsonProperty ("overdueDays")
  public void setOverdueDays(final Integer overdueDays) {
    this.overdueDays = overdueDays;
  }

  @JsonProperty ("installmentCount")
  public Integer getInstallmentCount() {
    return this.installmentCount;
  }

  @JsonProperty ("installmentCount")
  public void setInstallmentCount(final Integer installmentCount) {
    this.installmentCount = installmentCount;
  }

  @JsonProperty ("installmentAmount")
  public Integer getInstallmentAmount() {
    return this.installmentAmount;
  }

  @JsonProperty ("installmentAmount")
  public void setInstallmentAmount(final Integer installmentAmount) {
    this.installmentAmount = installmentAmount;
  }

  @JsonProperty ("typeOfContract")
  public String getTypeOfContract() {
    return this.typeOfContract;
  }

  @JsonProperty ("typeOfContract")
  public void setTypeOfContract(final String typeOfContract) {
    this.typeOfContract = typeOfContract;
  }

  @JsonProperty ("lastUpdated")
  public String getLastUpdated() {
    return this.lastUpdated;
  }

  @JsonProperty ("lastUpdated")
  public void setLastUpdated(final String lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

  @JsonProperty ("creditor")
  public String getCreditor() {
    return this.creditor;
  }

  @JsonProperty ("creditor")
  public void setCreditor(final String creditor) {
    this.creditor = creditor;
  }

  @JsonProperty ("contractIdentifier")
  public String getContractIdentifier() {
    return this.contractIdentifier;
  }

  @JsonProperty ("contractIdentifier")
  public void setContractIdentifier(final String contractIdentifier) {
    this.contractIdentifier = contractIdentifier;
  }

  @JsonProperty ("selected")
  public Boolean getSelected() {
    return this.selected;
  }

  @JsonProperty ("selected")
  public void setSelected(final Boolean selected) {
    this.selected = selected;
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
    return new HashCodeBuilder().append(this.backendId).append(this.company).append(
        this.bankAccountNumber)
        .append(this.startDate).append(this.endDate).append(this.amount).append(
            this.outstandingAmount)
        .append(this.mmp)
        .append(this.paymentsLeft).append(this.overdueAmount).append(this.overdueInstallmentCount)
        .append(this.overdueDays).append(this.installmentCount).append(this.installmentAmount)
        .append(this.typeOfContract).append(this.lastUpdated).append(this.creditor).append(
            this.contractIdentifier)
        .append(this.selected).append(this.additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof ExternalFinancialObligationsList)) {
      return false;
    }
    final ExternalFinancialObligationsList rhs = ((ExternalFinancialObligationsList) other);
    return new EqualsBuilder().append(this.backendId, rhs.backendId)
        .append(this.company, rhs.company)
        .append(this.bankAccountNumber, rhs.bankAccountNumber).append(this.startDate, rhs.startDate)
        .append(this.endDate, rhs.endDate).append(this.amount, rhs.amount)
        .append(this.outstandingAmount, rhs.outstandingAmount).append(this.mmp, rhs.mmp)
        .append(this.paymentsLeft, rhs.paymentsLeft).append(this.overdueAmount, rhs.overdueAmount)
        .append(this.overdueInstallmentCount, rhs.overdueInstallmentCount)
        .append(this.overdueDays, rhs.overdueDays)
        .append(this.installmentCount, rhs.installmentCount)
        .append(this.installmentAmount, rhs.installmentAmount)
        .append(this.typeOfContract, rhs.typeOfContract)
        .append(this.lastUpdated, rhs.lastUpdated).append(this.creditor, rhs.creditor)
        .append(this.contractIdentifier, rhs.contractIdentifier).append(this.selected, rhs.selected)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
