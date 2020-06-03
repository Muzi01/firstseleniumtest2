package bindings.commons.automation.salesforce.rest.v2;

import com.ipfdigital.automation.salesforce.model.SfInvoice;

import static com.ipfdigital.automation.salesforce.SFQuery.GET_INVOICE_BY_ACCOUNT_NUMBER_AND_DAYS_LATE;

public class SalesforceInvoiceProvider {

  private final SalesforceProvider salesforceProvider;

  public SalesforceInvoiceProvider(
      final SalesforceProvider salesforceProvider) {
    this.salesforceProvider = salesforceProvider;
  }

  public SfInvoice getInvoiceBy(final long accountNumber,
      final int daysLate) {

    final String query =
        String.format(GET_INVOICE_BY_ACCOUNT_NUMBER_AND_DAYS_LATE.getQuery(), accountNumber,
            daysLate);
    return this.salesforceProvider.sendQueryAndGetRecords(query)
        .stream()
        .map(SalesforceUtils::mapToSfInvoice)
        .findFirst()
        .get();
  }
}
