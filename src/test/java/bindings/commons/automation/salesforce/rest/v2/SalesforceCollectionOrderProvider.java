package bindings.commons.automation.salesforce.rest.v2;

import com.ipfdigital.automation.salesforce.model.SfCollectionOrder;

import java.util.List;

import static com.ipfdigital.automation.salesforce.SFQuery.GET_COLLECTION_ORDER_BY_ID_AND_STATE;
import static com.ipfdigital.automation.salesforce.SFQuery.GET_COLLECTION_ORDER_BY_INVOICE_ID;
import static java.util.stream.Collectors.toList;

public class SalesforceCollectionOrderProvider {

  private final SalesforceProvider salesforceProvider;

  public SalesforceCollectionOrderProvider(
      final SalesforceProvider salesforceProvider) {
    this.salesforceProvider = salesforceProvider;
  }

  public boolean isCollectionOrderWithState(final String sfCollectionId,
      final String state) {

    final String query =
        String.format(GET_COLLECTION_ORDER_BY_ID_AND_STATE.getQuery(), sfCollectionId, state);
    return !this.salesforceProvider.sendQueryAndGetRecords(query).isEmpty();
  }

  public List<SfCollectionOrder> getCollectionOrdersBy(final String sfInvoiceId) {

    final String query = String.format(GET_COLLECTION_ORDER_BY_INVOICE_ID.getQuery(), sfInvoiceId);
    return this.salesforceProvider.sendQueryAndGetRecords(query)
        .stream()
        .map(SfCollectionOrder::new)
        .collect(toList());
  }
}
