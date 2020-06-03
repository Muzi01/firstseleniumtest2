package bindings.commons.automation.salesforce.rest.v2;

import com.ipfdigital.automation.SfParamsConstants;
import com.ipfdigital.automation.salesforce.SFQuery;
import com.ipfdigital.exceptions.NotFoundInDBException;

import java.util.List;
import java.util.Map;

import static com.ipfdigital.automation.SfParamsConstants.QUERY_ID;

public class SalesforceApplicationDataProvider {

  private final SalesforceProvider salesforceProvider;

  public SalesforceApplicationDataProvider(final SalesforceProvider salesforceProvider) {
    this.salesforceProvider = salesforceProvider;
  }

  public String getApplicationNumber(final String customerId) {
    final String query =
        String.format(SFQuery.GET_APPLICATION_NUMBER.getQuery(), customerId);
    final List<Map> results = salesforceProvider.sendQueryAndGetRecords(query);
    return SalesforceUtils
        .getFieldValueFromResults(results, SfParamsConstants.APPLICATION_NUMBER_PARAM)
        .orElseThrow(() -> new NotFoundInDBException(
            "Application number not found for customer " + customerId));
  }

  public String getApplicationID(final String customerId) {
    final String query =
        String.format(SFQuery.GET_APPLICATION_ID.getQuery(), customerId);
    final List<Map> results = salesforceProvider.sendQueryAndGetRecords(query);
    return SalesforceUtils
        .getFieldValueFromResults(results, QUERY_ID)
        .orElseThrow(() -> new NotFoundInDBException(
            "Application id not found for customer " + customerId));
  }
}
