package bindings.commons.automation.salesforce.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude (Include.NON_NULL)
public class SfInvoice {
  @JsonAlias ("Id")
  public String sfInvoiceId;

  @JsonAlias ("Days_late__c")
  public Integer daysLate;


  @JsonAlias ("Status__c")
  public String state;

  @Override
  public String toString() {
    return "SfInvoice{" +
        "sfInvoiceId='" + this.sfInvoiceId + '\'' +
        ", daysLate=" + this.daysLate +
        ", state='" + this.state + '\'' +
        '}';
  }
}
