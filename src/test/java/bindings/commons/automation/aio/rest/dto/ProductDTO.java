package bindings.commons.automation.aio.rest.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ProductDTO {

  public Long id;
  public String name;
  public int openingFee;
  public Integer principal;
  public int invoicingFee;
  public int minimumWithdrawalFee;
  public double drawFeePercentage;
  public double interest;
  public double annualInterest;
  public double apr;
  public int maturityPeriods;
  public String maturityPeriodLength;
  public String type;
  public int withdrawalFixedFee;
  public String brand;
  public String groupName;
  public int totalPayment;
  public int totalInterest;
  public int fullMaturityMmp;
  public ProductDTO higherPriceProduct;
  public boolean available;

  public ProductDTO() {
  }

  public ProductDTO(final String name, final String groupName, final int maturityPeriods,
      final int principal) {
    this.name = name;
    this.groupName = groupName;
    this.maturityPeriods = maturityPeriods;
    this.principal = principal;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    final ProductDTO product = (ProductDTO) o;

    return new EqualsBuilder()
        .append(this.name, product.name)
        .append(this.principal, product.principal)
        .append(this.maturityPeriods, product.maturityPeriods)
        .append(this.groupName, product.groupName)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(this.name)
        .append(this.principal)
        .append(this.maturityPeriods)
        .append(this.groupName)
        .toHashCode();
  }
}
