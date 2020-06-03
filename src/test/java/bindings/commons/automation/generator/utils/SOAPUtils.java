package bindings.commons.automation.generator.utils;

import com.ipfdigital.automation.aio.soap.SOAPClient;
import com.ipfdigital.automation.aio.soap.XMLUtils;
import com.ipfdigital.automation.aio.soap.bankscraping.UpdateBankScrapingRequest;
import com.ipfdigital.automation.aio.soap.cases.CloseCaseRequest;
import com.ipfdigital.automation.aio.soap.cases.ParameterMap;
import com.ipfdigital.automation.aio.soap.contract.ContractCloseRequest;
import com.ipfdigital.automation.aio.soap.contract.OutstandingAmountRequest;
import com.ipfdigital.automation.aio.soap.customer.HandleIdentificationRequestV2;
import com.ipfdigital.automation.aio.soap.customer.SfRequestType;
import com.ipfdigital.automation.aio.soap.customer.VerifyBankAccountRequest;
import com.ipfdigital.automation.aio.soap.dailybatch.JobIdRequest;
import com.ipfdigital.automation.aio.soap.extra.JobIdRequestWithParams;
import com.ipfdigital.automation.aio.soap.payment.IdReferenceType;
import com.ipfdigital.automation.aio.soap.payment.ManualBankPayment;
import com.ipfdigital.automation.aio.soap.payment.ManualBankPaymentBatch;
import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.generator.exceptions.TimeoutException;
import com.ipfdigital.automation.generator.model.aio.BankPayment;
import com.ipfdigital.automation.generator.model.aio.Contract;
import com.ipfdigital.automation.generator.model.aio.Customer;
import com.ipfdigital.automation.generators.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.soap.SOAPFaultException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SOAPUtils extends SOAPClient {
  private static final Logger LOG = LogManager.getLogger(SOAPUtils.class);

  private static final int DELAY_BETWEEN_JOBS_MS = 30000;
  private static final int JOB_EXECUTION_TIMEOUT_MS = 120000;

  /**
   * Creates a SOAPClient which can call methods exposed by AIO /admin/ws
   *
   * @param endpoint the soap endpoint of chosen environment, e.g.
   *        <code>https://uat.hapicredito.com/admin/ws</code>
   */
  public SOAPUtils(final String endpoint) {
    super(endpoint);
  }

  private SfRequestType buildRequestHeader(final Country country) {
    return new SfRequestType(String.valueOf(country), StringUtils.EMPTY);
  }

  public void verifyBankAccount(final Country country, final String customerId,
      final String bankAccount) {
    LOG.info("Calling verifyBankAccount - country: {}, customerId: {}, bankAccount: {}", country,
        customerId, bankAccount);
    getCustomersPort().verifyBankAccount(
        new VerifyBankAccountRequest(customerId, bankAccount),
        buildRequestHeader(country));
  }

  public void handleIdentificationV2(final Country country, final long customerId,
      final String status) {
    LOG.info("Calling handleIdentificationV2 - country: {}, customerId: {}, status: {}", country,
        customerId, status);
    final HandleIdentificationRequestV2 requestV2 = new HandleIdentificationRequestV2();
    requestV2.setId(customerId);
    requestV2.setIdentificationStatus(status);

    getCustomersPort().handleIdentificationV2(
        requestV2,
        buildRequestHeader(country));
  }

  public void handleIdentificationV2(final Country country, final long customerId,
      final String status,
      final String bankAccount) {
    LOG.info(
        "Calling handleIdentificationV2 - country: {}, customerId: {}, status: {}, bankAccount: {}",
        country, customerId, status, bankAccount);
    final HandleIdentificationRequestV2 requestV2 = new HandleIdentificationRequestV2();
    requestV2.setId(customerId);
    requestV2.setIdentificationStatus(status);
    requestV2.setBankAccount(bankAccount);

    getCustomersPort().handleIdentificationV2(
        requestV2,
        buildRequestHeader(country));
  }

  public void closeCase(final Country country, final String caseId, final String result) {
    LOG.info("Calling closeCase - country: {}, caseId: {}, result: {}", country, caseId, result);
    getCasesPort().closeCase(
        new CloseCaseRequest(caseId, result, new ParameterMap()),
        new com.ipfdigital.automation.aio.soap.cases.SfRequestType(String.valueOf(country),
            StringUtils.EMPTY));
  }

  public void triggerJobWithParams(final Country country, final String name,
      final String customersIdRange,
      final String user) {
    LOG.info("Triggering {} for country: {}, customersIdRange: {}", name, country,
        customersIdRange);
    try {
      Utils.waitUntil(() -> {
        try {
          final com.ipfdigital.automation.aio.soap.extra.SfRequestType requestType =
              new com.ipfdigital.automation.aio.soap.extra.SfRequestType(String.valueOf(country),
                  user);
          getExtraPort().triggerJobWithParams(
              new JobIdRequestWithParams(name, customersIdRange), requestType);
          return true;

        } catch (final SOAPFaultException e) {
          LOG.warn("Error during execution of SOAP job: {}. Retrying", e.getMessage());
          return false;
        }

      }, DELAY_BETWEEN_JOBS_MS, JOB_EXECUTION_TIMEOUT_MS);

    } catch (final TimeoutException e) {
      LOG.error("Unable to execute SOAP triggerJobWithParams: {} within provided time {} (MS)",
          name, JOB_EXECUTION_TIMEOUT_MS);
      throw e;
    }
  }

  private void triggerJobByName(final Customer customer, final String user, final String jobName) {
    final String stringId = String.valueOf(customer.id);
    final String customersIdRange = stringId + "-" + stringId;
    triggerJobWithParams(customer.country, jobName, customersIdRange, user);
  }

  public void triggerContractSendingJob(final Customer customer, final String user) {
    triggerJobByName(customer, user, "ContractSendingJob");
  }

  void triggerInvoiceGeneratingJob(final Customer customer, final String user) {
    triggerJobByName(customer, user, "InvoiceGeneratingJob");
  }

  void triggerInvoiceSendingJob(final Customer customer, final String user,
      final Environment environment) {
    if (environment != Environment.ST03) {
      triggerJobByName(customer, user, "InvoiceSendingJob");

    } else { // todo AU - keep track for jobs standardisation between env's
      final String name = "InvoiceGeneratingJob";
      final Country country = customer.country;
      final com.ipfdigital.automation.aio.soap.dailybatch.SfRequestType requestType =
          new com.ipfdigital.automation.aio.soap.dailybatch.SfRequestType(String.valueOf(country),
              StringUtils.EMPTY);
      LOG.info("Triggering {} for country: {} for customer: {}", name, country, customer.id);
      getDailyBatchPort().triggerJob(
          new JobIdRequest(name),
          requestType);
    }
  }

  void triggerCollectionProcessingJob(final Customer customer, final String user) {
    triggerJobByName(customer, user, "CollectionProcessingJob");
  }

  void triggerCollectionUploadingJob(final Customer customer, final String user) {
    triggerJobByName(customer, user, "CollectionUploadingJob");
  }

  String uploadManualBankPayments(final Customer customer, final int amount, final Date date) {
    final String externalReference =
        (1000 + RandomUtils.randomInt(8999)) + new SimpleDateFormat("yyyyMMddHHmmss").format(date);
    final ManualBankPayment payment = setPaymentData(customer, amount, date, externalReference);

    final ManualBankPaymentBatch batch = new ManualBankPaymentBatch();
    batch.setBank("INTERNAL");
    batch.getPayment().add(payment);

    LOG.info(
        "Calling uploadManualBankPayments - country: {}, customerID: {}, amount: {}, externalReference: {}",
        customer.country, customer.id, amount, externalReference);
    getPaymentsPort().uploadManualBankPayments(batch,
        new com.ipfdigital.automation.aio.soap.payment.SfRequestType(
            String.valueOf(customer.country), StringUtils.EMPTY));
    return externalReference;
  }

  private ManualBankPayment setPaymentData(final Customer customer, final int amount,
      final Date date,
      final String externalReference) {
    final XMLGregorianCalendar nowXML = XMLUtils.dateToXML(date);
    final ManualBankPayment payment = new ManualBankPayment();
    payment.setCustomer(new IdReferenceType(customer.id));
    payment.setMessage("Test generator payment");
    payment.setValueDate(nowXML);
    payment.setPaymentDate(nowXML);
    payment.setAmount(amount);
    payment.setPayerName("IPF Test Generator");
    payment.setReference(customer.bankAccount);
    payment.setExternalReference(externalReference);
    return payment;
  }

  void allocatePayment(final Country country, final BankPayment bankPayment) {
    LOG.info("Calling allocatePayment - country: {}, bankPayment: {}", country,
        bankPayment.getId());
    getPaymentsPort().allocatePayment(
        new IdReferenceType(bankPayment.getId()),
        new com.ipfdigital.automation.aio.soap.payment.SfRequestType(String.valueOf(country),
            StringUtils.EMPTY));
  }

  Integer getTotalOutstandingAmount(final Contract contract, final Date date,
      final Country country) {
    LOG.info("Calling getTotalOutstandingAmount - contract: {}, date: {}", contract.id, date);
    return getContractsPort().getOutstandingAmount(
        new OutstandingAmountRequest(contract.id, XMLUtils.dateToXML(date)),
        new com.ipfdigital.automation.aio.soap.contract.SfRequestType(
            String.valueOf(country), StringUtils.EMPTY))
        .getTotalAmount();
  }

  void closeContract(final Contract contract, final Date date, final String closeType) {
    LOG.info("Calling closeContract - contract: {}, date: {}, closeType: {}", contract.id,
        date, closeType);
    final ContractCloseRequest contractCloseRequest = new ContractCloseRequest();
    contractCloseRequest.setCloseType(closeType);
    contractCloseRequest.setCloseDateTime(XMLUtils.dateToXML(date));
    contractCloseRequest.setContractId(
        new com.ipfdigital.automation.aio.soap.contract.IdReferenceType(contract.id));
    getContractsPort().contractClose(contractCloseRequest,
        new com.ipfdigital.automation.aio.soap.contract.SfRequestType(
            String.valueOf(contract.customer.country), StringUtils.EMPTY));
  }

  void updateBankScraping(final String compressedCustomerUUID, final String bankScrapingProvider,
      final String bankAccount, final String bankScrapingResponse) {
    LOG.info("Calling updateBankScraping - contract: ");
    getBankScrapingPort().update(
        new UpdateBankScrapingRequest(compressedCustomerUUID, bankScrapingProvider, bankAccount,
            bankScrapingResponse));
  }
}
