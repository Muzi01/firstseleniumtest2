package bindings.commons.automation.salesforce.rest.v2;

import com.ipfdigital.automation.SfParamsConstants;
import com.ipfdigital.automation.generator.model.aio.Customer;
import org.apache.commons.collections.MapUtils;

import java.util.HashMap;
import java.util.Map;

public class ExternalVerificationReportService {

  private final SalesforceProvider salesforceProvider;
  private static final String TRUE = "true";
  private static final String POSITIVE = "POSITIVE";
  private static final String SALARY = "150000";
  private static final String PENNY_TRANSFER = "Penny Transfer";

  public ExternalVerificationReportService(final SalesforceProvider salesforceProvider) {
    this.salesforceProvider = salesforceProvider;
  }

  public void addPennyTransfer(final Customer customer, final String accountId) {
    final Map input = MapUtils.putAll(new HashMap<>(), new String[] {
        SfParamsConstants.ACCOUNT_PARAM, accountId,
        SfParamsConstants.BACKEND_ID_PARAM, accountId,
        SfParamsConstants.REPORT_SOURCE_PARAM, PENNY_TRANSFER,
        SfParamsConstants.STATUS_C_PARAM, POSITIVE,
        SfParamsConstants.CUSTOMER_AVERAGE_SALARY_PARAM, SALARY,
        SfParamsConstants.IS_NAME_VERIFIED_C, TRUE,
        SfParamsConstants.SUCCESSFUL_LOGIN_C, TRUE,
        SfParamsConstants.IS_PERSONAL_NUMBER_VERIFIED_C, TRUE,
        SfParamsConstants.IS_ACCOUNT_NUMBER_VERIFIED_C, TRUE,
        SfParamsConstants.NAME_FOR_VERIFICATION_C, customer.firstName + customer.lastName,
        SfParamsConstants.FULL_NAME_C, customer.firstName + customer.lastName
    });

    salesforceProvider.sendExternalVerificationReport(input);
  }
}
