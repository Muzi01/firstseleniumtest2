package bindings.commons.automation.salesforce.rest.v2;

import com.ipfdigital.automation.SfParamsConstants;
import com.ipfdigital.automation.salesforce.SFQuery;
import com.ipfdigital.exceptions.NotFoundInDBException;

import java.util.List;
import java.util.Map;

public class SalesforceProductAmountTakenProvider {

  private final SalesforceProvider salesforceProvider;

  public SalesforceProductAmountTakenProvider(final SalesforceProvider salesforceProvider) {
    this.salesforceProvider = salesforceProvider;
  }

  public String getProductAmountTaken(final String ssn) {
    final String query =
        String.format(SFQuery.SF_GET_APPLICATION_PRODUCT_AMOUNT.getQuery(), ssn);
    final List<Map> results = salesforceProvider.sendQueryAndGetRecords(query);
    return SalesforceUtils
        .getFieldValueFromResults(results, SfParamsConstants.PRODUCT_AMOUNT_TAKEN_PARAM)
        .orElseThrow(
            () -> new NotFoundInDBException("Product amount taken not found for customer " + ssn));
  }
}
