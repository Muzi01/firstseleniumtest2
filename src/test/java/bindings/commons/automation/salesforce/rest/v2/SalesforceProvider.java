package bindings.commons.automation.salesforce.rest.v2;

import com.force.api.ApiException;
import com.force.api.ResourceRepresentation;
import com.ipfdigital.automation.SfParamsConstants;
import com.ipfdigital.automation.config.PropertyProvider;
import com.ipfdigital.automation.config.PropertyProviderFactory;
import com.ipfdigital.automation.salesforce.SFAttachmentType;
import com.ipfdigital.automation.salesforce.SFCase;
import com.ipfdigital.automation.salesforce.SFQuery;
import com.ipfdigital.automation.salesforce.SFRecord;
import com.ipfdigital.automation.salesforce.SFTask;
import com.ipfdigital.automation.salesforce.rest.v2.exceptions.SalesForceExecutionException;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.awaitility.Awaitility;
import org.awaitility.core.ConditionTimeoutException;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.awaitility.Awaitility.await;

public class SalesforceProvider {

  private static final Logger LOG = LogManager.getLogger(SalesforceProvider.class);

  private static final String CONTRACT_NOT_REPLICATED_MSG_TEMPLATE = "Contract not replicated %s";
  private static final String TASK_NOT_CLOSED_TEMPLATE = "Task %s not closed";
  private static final String ACCOUNT_NOT_FOUND_TEMPLATE =
      "Account with account number %s not found";
  private static final Duration BANK_ACCOUNT_INTERVAL = Duration.ofSeconds(6);
  private static final Duration BANK_ACCOUNT_TIMEOUT = Duration.ofMinutes(1);
  private static final String ISSUER_VALUE = "issuer";
  private static final String TRUE = "true";

  private final SalesforceClient salesforceClient;
  private final long timeout;
  private final long waitTime;

  public SalesforceProvider(final String env, final SFRole role) {
    final PropertyProvider propertyProvider = PropertyProviderFactory.propertyProvider(env);
    waitTime = SalesforceUtils.getWaitTime(propertyProvider);
    timeout = SalesforceUtils.getTimeout(propertyProvider);
    salesforceClient =
        new SalesforceClient(SalesforceUtils.buildApiConfig(propertyProvider, role));
  }

  public SalesforceProvider(final String env) {
    this(env, SalesforceUtils.getAdminRole());
  }

  public List<Map> executeQuery(final String query) {
    return salesforceClient.sendQueryAndGetRecords(query);
  }

  public void identifyAccountWithBankAccount(final String accountId) {
    final Map input = MapUtils.putAll(new HashMap(), new String[] {
        SfParamsConstants.IDENTIFICATION_PARAM,
        SfParamsConstants.IDENTIFICATION_PARAM_EXPECTED_VALUE
    });
    salesforceClient.updateRecord(SFRecord.ACCOUNT.getName(), accountId, input);
    LOG.info("Customer account identified!");
  }

  public void manualDTICalculationDone(final String appId) {
    final Map input = MapUtils.putAll(new HashMap(), new String[] {
        SfParamsConstants.MANUAL_DTI_CALCULATION_DONE_C, TRUE
    });
    salesforceClient.updateRecord(SFRecord.CREDIT_APPLICATION.getName(), appId, input);
    LOG.info("Customer DTI Calculation Done!");
  }


  public void updateAccountIdentificationDocumentType(final long accountNumber) {
    final Map input = MapUtils.putAll(new HashMap(), new String[] {
        SfParamsConstants.IDENTIFICATION_DOCUMENT_TYPE,
        SfParamsConstants.IDENTIFICATION_DOCUMENT_TYPE_EXPECTED_VALUE
    });
    salesforceClient
        .updateRecord(SFRecord.ACCOUNT.getName(), getAccountId(accountNumber), input);
    LOG.info("Customer account Identification Document Type updated");
  }

  public void updateAccountIdDocumentSerialNumber(final long accountNumber) {
    final Map input = MapUtils.putAll(new HashMap(), new String[] {
        SfParamsConstants.ID_DOCUMENT_SERIAL_NUMBER,
        SfParamsConstants.ID_DOCUMENT_SERIAL_NUMBER_EXPECTED_VALUE
    });
    salesforceClient
        .updateRecord(SFRecord.ACCOUNT.getName(), getAccountId(accountNumber), input);
    LOG.info("Customer account Id Document Serial Number updated");
  }

  public void updateBankAccount(final long accountNumber, final String bankAccount) {
    final Map input = MapUtils.putAll(new HashMap(), new String[] {
        SfParamsConstants.VERIFIED_PARAM, "true",
        SfParamsConstants.BANK_ACCOUNT_PARAM, bankAccount
    });
    this.salesforceClient
        .updateRecord(SFRecord.ACCOUNT.getName(), getAccountId(accountNumber), input);
    LOG.info("Customer bank account updated");
  }

  public void identifyAccountWithBankAccount(final long accountNumber) {
    final String accountId = getAccountId(accountNumber);
    if (Objects.isNull(accountId)) {
      throw new SalesForceExecutionException(
          String.format(ACCOUNT_NOT_FOUND_TEMPLATE, accountNumber));
    }
    identifyAccountWithBankAccount(accountId);
  }

  public void verifyPhone(final long accountNumber) {
    final String mcbCreditAppId = getMCBCreditAppId(accountNumber);

    if (Objects.isNull(mcbCreditAppId)) {
      throw new SalesForceExecutionException(
          "MCBCreditApplication not found for account " + accountNumber);
    }

    final Map input = MapUtils.putAll(new HashMap(), new String[] {
        SfParamsConstants.CUSTOMER_PHONE_VERIFICATION_PARAM,
        SfParamsConstants.CUSTOMER_PHONE_VERIFICATION_PARAM_EXPECTED_VALUE
    });

    salesforceClient.updateRecord(SFRecord.MCB_CREDIT_APP.getName(), mcbCreditAppId, input);
    LOG.info("Phone number verified!");
  }

  public void addArchiveBankAccount(final long accountNumber, final String bankAccount) {
    LOG.info("Adding Archive Bank Number {} to Draw Case for account {}", bankAccount,
        accountNumber);
    final String caseId = getCaseId(accountNumber, SFCase.DRAW);

    if (Objects.isNull(caseId)) {
      throw new SalesForceExecutionException(
          "Not found Case for C24 Draw Case for account: " + accountNumber);
    }

    LOG.info("Received caseId: {}", caseId);
    final Map input = MapUtils.putAll(new HashMap<>(), new String[] {
        SfParamsConstants.BANK_ARCHIVE_NUMBER_PARAM, bankAccount
    });
    LOG.info(String.format("Starting to update %s with id %s", SFRecord.CASE.getName(), caseId));
    salesforceClient.updateRecord(SFRecord.CASE.getName(), caseId, input);
    LOG.info("Archive Bank Account added!");
  }

  public boolean closeTask(final long accountNumber, final SFTask taskSubject) {
    LOG.info("Closing task {} for account {}", taskSubject.getSubject(), accountNumber);

    final String taskId = getTaskId(accountNumber, taskSubject);

    if (Objects.isNull(taskId)) {
      throw new SalesForceExecutionException(
          "Not found task with subject " + taskSubject + " for account " + accountNumber);
    }

    return closeTask(taskId, taskSubject);
  }

  public boolean closeTaskWithStatus(
      final long accountNumber, final SFTask taskSubject, final String status) {
    LOG.info("Closing task {} where status is: {}", taskSubject.getSubject(), status);
    final String taskId = getTaskIdWhereStatus(accountNumber, taskSubject, status);

    if (Objects.isNull(taskId)) {
      throw new SalesForceExecutionException("Not found task with subject " + taskSubject
          + " and status " + status + " for account " + accountNumber);
    }

    return closeTask(taskId, taskSubject);
  }

  public void uploadAttachment(final long accountNumber, final SFAttachmentType attachmentType) {
    final String accountId = getAccountId(accountNumber);
    if (Objects.isNull(accountId)) {
      throw new SalesForceExecutionException(
          String.format(ACCOUNT_NOT_FOUND_TEMPLATE, accountNumber));
    }
    uploadAttachment(accountId, attachmentType);
  }

  public void uploadAttachment(final String accountId, final SFAttachmentType attachmentType) {
    final Map input = MapUtils.putAll(new HashMap<>(), new String[] {
        SfParamsConstants.TYPE_PARAM, attachmentType.toString(),
        SfParamsConstants.ACCOUNT_PARAM, accountId,
        SfParamsConstants.VALID_UTIL_PARAM, SalesforceUtils.generateDocumentValidityDate(),
        SfParamsConstants.VERIFICATION_STATUS_PARAM,
        SfParamsConstants.VERIFICATION_STATUS_PARAM_EXPECTED_VALUE,
        SfParamsConstants.ISSUER_PARAM, ISSUER_VALUE
    });
    salesforceClient.createRecord(SFRecord.ATTACHMENT.getName(), input);
    LOG.info("attachment successfully added! {}", attachmentType.toString());
  }

  public void sendExternalVerificationReport(Map input) {
    this.salesforceClient.createRecord(SFRecord.EXTERNAL_VERIFICATION_REPORT.getName(), input);
    LOG.info("EXTERNAL_VERIFICATION_REPORT successfully added!");
  }

  public boolean closeTask(final String taskId, final SFTask taskSubject) {
    final Map input = MapUtils.putAll(new HashMap(), new String[] {
        SfParamsConstants.STATUS_PARAM, SfParamsConstants.STATUS_PARAM_EXPECTED_VALUE
    });
    salesforceClient.updateTaskRecord(SFRecord.TASK.getName(), taskId, input,
        taskSubject.getSubject());
    return isTaskClosed(taskId, taskSubject);
  }

  public boolean isContractReady(final String accountId) {
    LOG.info("Waiting for Contract replication for account {}", accountId);
    try {
      await().timeout(timeout, TimeUnit.MILLISECONDS)
          .pollInterval(waitTime, TimeUnit.MILLISECONDS)
          .dontCatchUncaughtExceptions().until(() -> {
            final ResourceRepresentation accountResult = salesforceClient.getRecord(
                SFRecord.ACCOUNT.getName(), accountId, SfParamsConstants.LAST_CONTRACT_PARAM);
            return Objects.nonNull(
                MapUtils.getObject(accountResult.asMap(), SfParamsConstants.LAST_CONTRACT_PARAM));
          });
      return true;
    } catch (final ConditionTimeoutException e) {
      final String errorMsg = String.format(CONTRACT_NOT_REPLICATED_MSG_TEMPLATE, accountId);
      LOG.error(errorMsg, e);
      return false;
    }
  }

  public boolean isContractReady(final long accountNumber) {
    final String accountId = getAccountId(accountNumber);
    if (Objects.isNull(accountId)) {
      throw new SalesForceExecutionException(
          String.format(ACCOUNT_NOT_FOUND_TEMPLATE, accountNumber));
    }
    return isContractReady(accountId);
  }

  public boolean isTaskClosed(final String taskId, final SFTask taskSubject) {
    try {
      await().timeout(timeout, TimeUnit.MILLISECONDS)
          .pollInterval(waitTime, TimeUnit.MILLISECONDS)
          .dontCatchUncaughtExceptions().until(() -> {
            final ResourceRepresentation taskResult =
                salesforceClient.getRecord(SFRecord.TASK.getName(),
                    taskId, SfParamsConstants.STATUS_PARAM);
            return StringUtils.equals(SfParamsConstants.STATUS_PARAM_EXPECTED_VALUE,
                MapUtils.getString(taskResult.asMap(), SfParamsConstants.STATUS_PARAM));
          });
      LOG.info("Task {} closed successfully! Task ID : {}", taskSubject.getSubject(), taskId);
      return true;
    } catch (final ConditionTimeoutException e) {
      final String msg = String.format(TASK_NOT_CLOSED_TEMPLATE, taskSubject.getSubject());
      LOG.error(msg, e);
      return false;
    }
  }

  public boolean isAccountReplicated(final long accountNumber) {
    final String accountId = getAccountId(accountNumber);
    LOG.debug("Get accountId {} for account {}", accountId, accountNumber);
    return StringUtils.isNotEmpty(accountId);
  }

  public boolean isProductReplicated(final long aioProductId) {
    final String productId = getProductId(aioProductId);
    LOG.debug("Get productIid {} for aio product id {}", productId, aioProductId);
    return StringUtils.isNotEmpty(productId);
  }

  public boolean isTaskReplicated(final long accountNumber, final SFTask taskSubject) {
    return isTaskReplicated(accountNumber, taskSubject, timeout);
  }

  public boolean isTaskReplicated(final long accountNumber, final SFTask taskSubject,
      final Long timeout) {
    final String taskId = getTaskId(accountNumber, taskSubject, timeout);
    LOG.debug("Get taskId {} for account {} and task {}", taskId, accountNumber, taskSubject);
    return StringUtils.isNotEmpty(taskId);
  }

  public boolean isCaseReplicated(final long accountNumber, final SFCase caseType) {
    final String caseId = getCaseId(accountNumber, caseType);
    LOG.debug("Get caseId for account {} and case {}", accountNumber, caseType);
    return StringUtils.isNotEmpty(caseId);
  }

  public boolean isCreditApplicationReplicated(final long accountNumber) {
    final String mcbCreditAppId = getMCBCreditAppId(accountNumber);
    if (Objects.isNull(mcbCreditAppId)) {
      LOG.error("MCBCreditApplication not found account {}", accountNumber);
    }
    return StringUtils.isNotEmpty(mcbCreditAppId);
  }

  public boolean isAddressInUse(final String accountId) {
    final ResourceRepresentation accountResult =
        salesforceClient.getRecord(SFRecord.ACCOUNT.getName(),
            accountId, SfParamsConstants.ADDRESS_IN_USE_PARAM);
    return MapUtils.getBoolean(accountResult.asMap(), SfParamsConstants.ADDRESS_IN_USE_PARAM,
        false);
  }

  public boolean isBankAccountIsUse(final String accountId) {
    final ResourceRepresentation accountResult =
        salesforceClient.getRecord(SFRecord.ACCOUNT.getName(),
            accountId, SfParamsConstants.BANK_ACCOUNT_IN_USE_PARAM);
    return MapUtils.getBoolean(accountResult.asMap(), SfParamsConstants.BANK_ACCOUNT_IN_USE_PARAM,
        false);
  }

  public boolean isMissingDocuments(final long accountNumber) {
    final String caseId = getCaseId(accountNumber, SFCase.NEW_APPLICATION);
    final ResourceRepresentation caseResult = salesforceClient
        .getRecord(SFRecord.CASE.getName(), caseId,
            SfParamsConstants.MISSING_DOCUMENTS_PARAM);
    return MapUtils.getBoolean(caseResult.asMap(), SfParamsConstants.MISSING_DOCUMENTS_PARAM,
        false);
  }

  public boolean isIncreasedRisk(final String accountId) {
    final String fieldsTemplate = "%s,%s";
    final String fields = String.format(fieldsTemplate, SfParamsConstants.RISK_GROUP_PARAM,
        SfParamsConstants.RISK_NOTES_PARAM);
    final ResourceRepresentation accountResult =
        salesforceClient.getRecord(SFRecord.ACCOUNT.getName(), accountId, fields);
    final Map resultMap = accountResult.asMap();
    final boolean compareRiskGroups =
        MapUtils.getString(resultMap, SfParamsConstants.RISK_GROUP_PARAM, StringUtils.EMPTY)
            .equals(SfParamsConstants.RISK_GROUP_PARAM_EXPECTED_VALUE);
    final boolean compareRiskNotes =
        MapUtils.getString(resultMap, SfParamsConstants.RISK_NOTES_PARAM, StringUtils.EMPTY)
            .equals(SfParamsConstants.RISK_NOTES_PARAM_EXPECTED_VALUE);
    return compareRiskGroups && compareRiskNotes;
  }

  public boolean isDebitCardReplicatedForAccount(final String ssn, final String debitCard) {
    final String query =
        String.format(SFQuery.GET_ACCOUNT_ID_WHERE_ACCOUNT_NUM_AND_DEBIT_CARD.getQuery(),
            ssn, debitCard);
    final List<Map> results = salesforceClient.sendQueryAndGetRecords(query, timeout,
        waitTime);
    return !results.isEmpty();
  }

  public String getTaskId(final long accountNumber, final SFTask taskSubject) {
    return getTaskId(accountNumber, taskSubject, timeout);
  }

  private String getTaskId(final long accountNumber, final SFTask taskSubject, final Long timeout) {
    final String query =
        String.format(SFQuery.GET_TASK_ID.getQuery(), accountNumber, taskSubject.getSubject());
    return executeQueryAndGetId(query, timeout);
  }

  public String getAccountId(final long accountNumber) {
    final String query = String.format(SFQuery.GET_ACCOUNT_ID.getQuery(), accountNumber);
    return executeQueryAndGetId(query);
  }

  public Map findAccountBySSN(final String ssn) {
    final String query = String.format(SFQuery.GET_ACCOUNT_BY_SSN.getQuery(), ssn);
    final List<Map> results = salesforceClient.sendQueryAndGetRecords(query, timeout,
        waitTime);
    final Optional<Map> firstElement = results.stream().findFirst();
    if (firstElement.isPresent()) {
      return firstElement.get();
    } else {
      LOG.error("Not found account with ssn {}", ssn);
      return Collections.emptyMap();
    }
  }

  public boolean closeAllOpenTaskUtilFindTaskWithSubject(final long accountNumber,
      final SFTask task) {
    try {
      await().timeout(timeout, TimeUnit.MILLISECONDS)
          .pollInterval(waitTime, TimeUnit.MILLISECONDS)
          .until(() -> {
            final List<Map> openedTasks = getOpenedTasks(accountNumber);
            for (final Map openTask : openedTasks) {
              final String taskId = (String) openTask.get("Id");
              final SFTask actualTask =
                  SalesforceUtils.getTaskBySubject((String) openTask.get("Subject"));
              closeTask(taskId, actualTask);
              if (actualTask == task) {
                return true;
              }
            }
            return false;
          });
      return true;
    } catch (final ConditionTimeoutException e) {
      LOG.error("Closing task util find subject " + task.getSubject() + " timeout");
      return false;
    }
  }

  public List<String> getCaseStatuses(final long accountNumber) {
    final String query = String.format(SFQuery.GET_CASE_STATUS.getQuery(), accountNumber);
    final List<Map> results = salesforceClient.sendQueryAndGetRecords(query);
    return results.stream().map(entry -> (String) entry.get("Status")).collect(Collectors.toList());
  }

  public List<Map> getMarketingConsents(final String accountId) {
    final String query = String.format(SFQuery.GET_MARKETING_CONSENTS.getQuery(), accountId);
    return salesforceClient.sendQueryAndGetRecords(query);
  }

  public List<Map> getMarketingConsents(final long accountNumber) {
    final String accountId = getAccountId(accountNumber);
    return getMarketingConsents(accountId);
  }

  public void updateAttachmentVerification(final long accountNumber,
      final SFAttachmentType attachmentType) {
    final Map<String, Object> jsonMap = new HashMap<>();
    jsonMap.put(SfParamsConstants.VALID_UTIL_PARAM, SalesforceUtils.generateDocumentValidityDate());
    jsonMap.put(SfParamsConstants.VERIFICATION_STATUS_PARAM,
        SfParamsConstants.VERIFICATION_STATUS_PARAM_EXPECTED_VALUE);
    final String attachmentId = getAttachmentId(accountNumber, attachmentType);
    if (Objects.isNull(attachmentId)) {
      throw new SalesForceExecutionException("Not found attachment for account number "
          + accountNumber + " and type " + attachmentType.toString());
    }
    salesforceClient.updateRecord(SFRecord.ATTACHMENT.getName(), attachmentId, jsonMap);
    LOG.info(attachmentType.toString() + " attachment successfully updated!");
  }

  List<Map> sendQueryAndGetRecords(final String query) {
    return salesforceClient.sendQueryAndGetRecords(query, timeout, waitTime);
  }

  private List<Map> getOpenedTasks(final long accountNumber) {
    final String query = String.format(SFQuery.GET_ALL_OPENED_TASK.getQuery(), accountNumber);
    return salesforceClient.sendQueryAndGetRecords(query);
  }

  private String getCaseId(final long accountNumber, final SFCase caseType) {
    final String query =
        String.format(SFQuery.GET_CASE_ID.getQuery(), accountNumber, caseType.getType());
    return executeQueryAndGetId(query);
  }

  private String getCaseIdByName(final String ssn, final SFCase caseName) {
    final String query =
        String.format(SFQuery.GET_CASE_ID_BY_NAME.getQuery(), ssn, caseName.getRecordTypeName());
    return executeQueryAndGetId(query);
  }

  private String getProductId(final long productId) {
    final String query = String.format(SFQuery.GET_MCB_PRODUCT_ID.getQuery(), productId);
    return executeQueryAndGetId(query);
  }

  private String getTaskIdWhereStatus(
      final long accountNumber, final SFTask taskSubject, final String status) {
    final String query = String.format(SFQuery.GET_TASK_ID_WHERE_STATUS.getQuery(), accountNumber,
        taskSubject.getSubject(), status);
    return executeQueryAndGetId(query);
  }

  private String getMCBCreditAppId(final long accountNumber) {
    final String query = String.format(SFQuery.GET_MCB_CREDIT_APP_ID.getQuery(), accountNumber);
    return executeQueryAndGetId(query);
  }

  private String getAttachmentId(final long accountNumber, final SFAttachmentType attachmentType) {
    final String query = String.format(SFQuery.GET_ATTACHMENT_ID.getQuery(), accountNumber,
        attachmentType.toString());
    return executeQueryAndGetId(query);
  }

  private String executeQueryAndGetId(final String query) {
    return executeQueryAndGetId(query, timeout);
  }

  private String executeQueryAndGetId(final String query, final Long timeout) {
    final List<Map> results = salesforceClient.sendQueryAndGetRecords(query, timeout,
        waitTime);
    return SalesforceUtils.getIdFromResults(results);
  }

  public void updateCase(final String ssn, final Map<String, String> caseDetails) {
    final String caseId = getCaseIdByName(ssn, SFCase.REGISTRATION);
    salesforceClient.updateRecord(SFRecord.CASE.getName(), caseId, caseDetails);
  }

  public void waitForBankAccountReplication(final long customerId) {
    LOG.info("Preparing query to get bank account from Salesforce for customer with ID {}.",
        customerId);
    final String query =
        String.format(SFQuery.SF_GET_BANK_ACCOUNT_NUMBER.getQuery(), customerId);
    Awaitility
        .with()
        .atMost(BANK_ACCOUNT_TIMEOUT)
        .pollInterval(BANK_ACCOUNT_INTERVAL)
        .ignoreException(ApiException.class)
        .await()
        .until(() -> isBankAccountReplicated(query, customerId));
  }

  private boolean isBankAccountReplicated(final String query, final long customerID) {
    final List<Map> results = salesforceClient.sendQueryAndGetRecords(query, timeout,
        waitTime);
    final Optional<String> bankAccountFromQuery =
        SalesforceUtils.getFieldValueFromResults(results, SfParamsConstants.BANK_ACCOUNT_PARAM);
    if (bankAccountFromQuery.isPresent()) {
      LOG.info("Bank account for customer {} is present in Salesforce and has value: {}.",
          customerID, bankAccountFromQuery.get());
      return true;
    }
    LOG.info("Customer {} doesn't have bank account present in Salesforce.", customerID);
    return false;
  }
}
