package bindings.commons.automation.salesforce.rest.v2;

import com.force.api.ApiConfig;
import com.force.api.ApiVersion;
import com.ipfdigital.automation.SfParamsConstants;
import com.ipfdigital.automation.config.PropertyProvider;
import com.ipfdigital.automation.salesforce.SFTask;
import com.ipfdigital.automation.salesforce.model.SfInvoice;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Map;
import java.util.Optional;

class SalesforceUtils {

  private static final String ENDPOINT_PARAM = "sf.endpoint";
  private static final String USERNAME_PARAM = "sf.login";
  private static final String USERNAME_PASS_PARAM = "sf.password";
  private static final String ID_PARAM = "sf.id";
  private static final String SECRET_PARAM = "sf.secret";
  private static final String WEB_SEPARATOR = "/";
  private static final String FIELDS_SUFFIX = "?fields=";
  private static final String RECORDS_PATH = "/sobjects/";
  private static final String VALIDITY_DATE_PATTERN = "yyyy-MM-dd";
  private static final String QUERY_TIMEOUT_TEMPLATE = "Execution of query %s timeout";
  private static final String WAIT_PARAM = "sf.query.sleep";
  private static final String TIMEOUT_PARAM = "sf.query.timeout";
  private static final Logger LOGGER = LogManager.getLogger(SalesforceUtils.class);

  private SalesforceUtils() {
  }

  static SFTask getTaskBySubject(final String subject) {
    for (final SFTask task : SFTask.values()) {
      if (task.getSubject().equals(subject)) {
        return task;
      }
    }
    throw new IllegalStateException("Not found task for subject " + subject);
  }

  static String generateQueryTimeoutMessage(final String query) {
    return String.format(QUERY_TIMEOUT_TEMPLATE, query);
  }

  static String generateDocumentValidityDate() {
    return LocalDate.now().with(TemporalAdjusters.lastDayOfYear())
        .format(DateTimeFormatter.ofPattern(VALIDITY_DATE_PATTERN));
  }

  static String getIdFromResults(final List<Map> result) {
    final Optional<Map> mapOptional = result.stream().findFirst();
    return mapOptional.map(map -> (String) map.get(SfParamsConstants.QUERY_ID)).orElse(null);
  }

  static Optional<String> getFieldValueFromResults(final List<Map> result, final String fieldName) {
    return result.stream()
        .findFirst()
        .map(map -> (String) map.get(fieldName));
  }

  static String buildRequestPath(final String sfObjectName, final String recordId,
      final String fields) {
    final StringBuilder path = new StringBuilder();
    path.append(RECORDS_PATH).append(sfObjectName);

    if (StringUtils.isNotBlank(recordId)) {
      path.append(WEB_SEPARATOR).append(recordId);
    }

    if (StringUtils.isNotBlank(fields)) {
      path.append(FIELDS_SUFFIX).append(fields);
    }
    return path.toString();
  }

  static long getTimeout(final PropertyProvider provider) {
    try {
      return Long.parseLong(provider.getProperty(TIMEOUT_PARAM));
    } catch (final NumberFormatException e) {
      LOGGER.error("Can't parse property " + TIMEOUT_PARAM, e);
      throw e;
    }
  }

  static long getWaitTime(final PropertyProvider provider) {
    try {
      return Long.parseLong(provider.getProperty(WAIT_PARAM));
    } catch (final NumberFormatException e) {
      LOGGER.error("Can't parse property " + WAIT_PARAM, e);
      throw e;
    }
  }

  static ApiConfig buildApiConfig(final PropertyProvider provider, final SFRole role) {
    return new ApiConfig()
        .setApiVersion(ApiVersion.DEFAULT_VERSION)
        .setLoginEndpoint(provider.getProperty(ENDPOINT_PARAM))
        .setClientId(provider.getProperty(role.getClientIdKey()))
        .setClientSecret(provider.getProperty(role.getClientSecretKey()))
        .setUsername(provider.getProperty(role.getUsernameKey()))
        .setPassword(provider.getProperty(role.getPassKey()));
  }

  static SFRole getAdminRole() {
    return new SFRole(ID_PARAM, SECRET_PARAM, USERNAME_PARAM, USERNAME_PASS_PARAM);
  }

  static SfInvoice mapToSfInvoice(final Map map) {
    final SfInvoice sfInvoice = new SfInvoice();
    sfInvoice.sfInvoiceId = (String) map.get("Id");
    sfInvoice.daysLate = ((Double) map.get("Days_late__c")).intValue();
    sfInvoice.state = (String) map.get("Status__c");
    return sfInvoice;
  }
}
