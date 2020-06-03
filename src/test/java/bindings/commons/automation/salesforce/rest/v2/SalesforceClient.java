package bindings.commons.automation.salesforce.rest.v2;

import com.force.api.ApiConfig;
import com.force.api.ApiException;
import com.force.api.ForceApi;
import com.force.api.QueryResult;
import com.force.api.ResourceException;
import com.force.api.ResourceRepresentation;
import com.ipfdigital.automation.salesforce.rest.v2.exceptions.SalesForceExecutionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.awaitility.core.ConditionTimeoutException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

class SalesforceClient {

  private static final Logger LOG = LogManager.getLogger(SalesforceClient.class);
  private static final String CREATE_ERROR_MSG_TEMPLATE =
      "Unexpected exception during create SF record for task: %s";
  private static final String GET_ERROR_MSG_TEMPLATE =
      "Unexpected exception during get SF record for task: %s";
  private static final String UPDATE_ERROR_MSG_TEMPLATE =
      "Unexpected exception during update record for task: %s";
  private static final String UPDATE_TASK_ERROR_MSG_TEMPLATE =
      "Unexpected exception during update record for task name: %s";
  private static final String EXECUTE_QUERY_MSG_TEMPLATE =
      "Unexpected exception during execute query %s";

  private final ForceApi forceApi;

  SalesforceClient(final ApiConfig apiConfig) {
    this.forceApi = new ForceApi(apiConfig);
  }

  ResourceRepresentation createRecord(final String sfObjectName, final Object input) {
    try {
      return this.forceApi.post(SalesforceUtils.buildRequestPath(sfObjectName, null, null), input);
    } catch (final ResourceException | ApiException e) {
      LOG.error(String.format(CREATE_ERROR_MSG_TEMPLATE, sfObjectName), e);
      throw new SalesForceExecutionException(String.format(CREATE_ERROR_MSG_TEMPLATE, sfObjectName),
          e);
    }
  }

  ResourceRepresentation getRecord(final String sfObjectName, final String recordId,
      final String fields) {
    try {
      return this.forceApi.get(SalesforceUtils.buildRequestPath(sfObjectName, recordId, fields));
    } catch (final ResourceException | ApiException e) {
      LOG.error(String.format(GET_ERROR_MSG_TEMPLATE, sfObjectName), e);
      throw new SalesForceExecutionException(String.format(GET_ERROR_MSG_TEMPLATE, sfObjectName),
          e);
    }
  }

  ResourceRepresentation updateRecord(final String sfObjectName, final String recordId,
      final Object input) {
    try {
      return patchObject(sfObjectName, recordId, input);
    } catch (final ResourceException | ApiException e) {

      return logAndThrowExceptionForObjectPatch(UPDATE_ERROR_MSG_TEMPLATE, sfObjectName, e);
    }
  }

  ResourceRepresentation updateTaskRecord(
      final String sfObjectName, final String recordId, final Object input,
      final String taskSubject) {
    try {
      return patchObject(sfObjectName, recordId, input);
    } catch (final ResourceException | ApiException e) {

      return logAndThrowExceptionForObjectPatch(UPDATE_TASK_ERROR_MSG_TEMPLATE, taskSubject, e);
    }
  }

  private ResourceRepresentation logAndThrowExceptionForObjectPatch(final String errorMsgTemplate,
      final String taskOrObjectName, final Throwable e) {
    LOG.error(String.format(errorMsgTemplate, taskOrObjectName), e);
    throw new SalesForceExecutionException(String.format(errorMsgTemplate, taskOrObjectName),
        e);
  }

  private ResourceRepresentation patchObject(final String sfObjectName, final String recordId,
      final Object input) {
    return this.forceApi.patch(SalesforceUtils.buildRequestPath(sfObjectName, recordId, null),
        input);
  }


  List<Map> sendQueryAndGetRecords(final String query, final long timeout, final long waitTime) {
    LOG.info("Executing query: {}", query);
    final List<Map> records = new ArrayList<>(Collections.emptyList());

    try {
      await().timeout(timeout, TimeUnit.MILLISECONDS).pollInterval(waitTime, TimeUnit.MILLISECONDS)
          .dontCatchUncaughtExceptions().until(() -> {
            try {
              final QueryResult<Map> queryResult = this.sendQuery(query);
              LOG.info(String.format("Executed query '%s'", query));
              records.addAll(queryResult.getRecords());
              return !queryResult.getRecords().isEmpty();
            } catch (final SalesForceExecutionException e) {
              LOG.error("Error when executing query " + query, e.getCause());
              return false;
            }
          });
    } catch (final ConditionTimeoutException e) {
      final String message = SalesforceUtils.generateQueryTimeoutMessage(query);
      LOG.error(message, e);
    }

    return records;
  }

  List<Map> sendQueryAndGetRecords(final String query) {
    return sendQuery(query).getRecords();
  }

  private QueryResult<Map> sendQuery(final String query) {
    try {
      return this.forceApi.query(query);
    } catch (final ResourceException | ApiException e) {
      final String errorMsg = String.format(EXECUTE_QUERY_MSG_TEMPLATE, query);
      LOG.error(errorMsg, e);
      throw new SalesForceExecutionException(errorMsg, e);
    }
  }
}
