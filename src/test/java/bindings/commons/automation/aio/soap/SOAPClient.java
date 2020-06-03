package bindings.commons.automation.aio.soap;

import com.ipfdigital.automation.aio.soap.bankscraping.BankScrapingPortType;
import com.ipfdigital.automation.aio.soap.bankscraping.BankScrapingWSImplService;
import com.ipfdigital.automation.aio.soap.broker.BrokerPortType;
import com.ipfdigital.automation.aio.soap.broker.BrokerWSImplService;
import com.ipfdigital.automation.aio.soap.c24comm.C24CommPortType;
import com.ipfdigital.automation.aio.soap.c24comm.C24CommWSImplService;
import com.ipfdigital.automation.aio.soap.cases.CaseWSImplService;
import com.ipfdigital.automation.aio.soap.cases.CasesPortType;
import com.ipfdigital.automation.aio.soap.cheque.ChequePortType;
import com.ipfdigital.automation.aio.soap.cheque.ChequeWSImplService;
import com.ipfdigital.automation.aio.soap.collection.CollectionPortType;
import com.ipfdigital.automation.aio.soap.collection.CollectionWSImplService;
import com.ipfdigital.automation.aio.soap.contract.ContractWSImplService;
import com.ipfdigital.automation.aio.soap.contract.ContractsPortType;
import com.ipfdigital.automation.aio.soap.creditapplication.CreditApplicationWSImplService;
import com.ipfdigital.automation.aio.soap.creditapplication.CreditApplicationsPortType;
import com.ipfdigital.automation.aio.soap.customer.CustomerWSImplService;
import com.ipfdigital.automation.aio.soap.customer.CustomersPortType;
import com.ipfdigital.automation.aio.soap.dailybatch.DailyBatchPortType;
import com.ipfdigital.automation.aio.soap.dailybatch.DailyBatchWSImplService;
import com.ipfdigital.automation.aio.soap.exceptions.SOAPClientException;
import com.ipfdigital.automation.aio.soap.externalresponse.ExternalResponsePortType;
import com.ipfdigital.automation.aio.soap.externalresponse.ExternalResponseWSImplService;
import com.ipfdigital.automation.aio.soap.extra.ExtraPortType;
import com.ipfdigital.automation.aio.soap.extra.ExtraWSImplService;
import com.ipfdigital.automation.aio.soap.infra.InfraPortType;
import com.ipfdigital.automation.aio.soap.infra.InfraWSImplService;
import com.ipfdigital.automation.aio.soap.mule.MulePortType;
import com.ipfdigital.automation.aio.soap.mule.MuleWsImplService;
import com.ipfdigital.automation.aio.soap.payment.PaymentWSImplService;
import com.ipfdigital.automation.aio.soap.payment.PaymentsPortType;
import com.ipfdigital.automation.aio.soap.paymentgateway.PaymentGatewayPortType;
import com.ipfdigital.automation.aio.soap.paymentgateway.PaymentGatewayWSImplService;
import com.ipfdigital.automation.aio.soap.product.ProductWSImplService;
import com.ipfdigital.automation.aio.soap.product.ProductsPortType;
import com.ipfdigital.automation.kibanalogger.KibanaLogger;
import com.ipfdigital.automation.kibanalogger.KibanaLoggerDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.MalformedURLException;
import java.net.URL;

public class SOAPClient {

  private static final Logger LOGGER = LogManager.getLogger(SOAPClient.class);
  private static final String MALFORMED_URL_ERROR = "Malformed endpoint URL: %s";

  private final String endpoint;

  private BrokerPortType brokerPort;
  private C24CommPortType c24CommPort;
  private CasesPortType casesPort;
  private ChequePortType chequePort;
  private CollectionPortType collectionPort;
  private ContractsPortType contractsPort;
  private CreditApplicationsPortType creditApplicationsPort;
  private CustomersPortType customersPort;
  private DailyBatchPortType dailyBatchPort;
  private ExternalResponsePortType externalResponsePort;
  private ExtraPortType extraPort;
  private InfraPortType infraPort;
  private MulePortType mulePort;
  private PaymentsPortType paymentsPort;
  private PaymentGatewayPortType paymentGatewayPort;
  private ProductsPortType productsPort;
  private BankScrapingPortType bankScrapingPort;
  private KibanaLogger kibanaLogger = new KibanaLogger();

  /**
   * Creates a SOAPClient which can call methods exposed by AIO /admin/ws
   * 
   * @param endpoint the soap endpoint of chosen environment, e.g.
   *        <code>https://uat.hapicredito.com/admin/ws</code>
   */
  public SOAPClient(final String endpoint) {
    this.endpoint = endpoint;
  }

  public void setKibanaLogger(final KibanaLogger kibanaLogger) {
    this.kibanaLogger = kibanaLogger;
  }

  public BrokerPortType getBrokerPort() {
    if (this.brokerPort == null) {
      LOGGER.info("Initializing BrokerPort on: " + this.endpoint);
      try {
        final URL url = new URL(this.endpoint + "/BrokerService?wsdl");
        this.brokerPort = new BrokerWSImplService(url).getBrokerWSImplPort();
      } catch (final MalformedURLException e) {
        throw new SOAPClientException(
            String.format(MALFORMED_URL_ERROR, this.endpoint + "/BrokerService?wsdl"));
      }
    }
    return this.brokerPort;
  }

  public C24CommPortType getC24CommPort() {
    if (this.c24CommPort == null) {
      LOGGER.info("Initializing C24CommPort on: " + this.endpoint);
      try {
        final URL url = new URL(this.endpoint + "/C24CommService?wsdl");
        this.c24CommPort = new C24CommWSImplService(url).getC24CommWSImplPort();
      } catch (final MalformedURLException e) {
        throw new SOAPClientException(
            String.format(MALFORMED_URL_ERROR, this.endpoint + "/C24CommService?wsdl"));
      }
    }
    return this.c24CommPort;
  }

  public CasesPortType getCasesPort() {
    if (this.casesPort == null) {
      LOGGER.info("Initializing CasesPort on: " + this.endpoint);
      try {
        final URL url = new URL(this.endpoint + "/CaseService?wsdl");
        this.casesPort = new CaseWSImplService(url).getCaseWSImplPort();
      } catch (final MalformedURLException e) {
        throw new SOAPClientException(
            String.format(MALFORMED_URL_ERROR, this.endpoint + "/CaseService?wsdl"));
      }
    }
    return this.casesPort;
  }

  public ChequePortType getChequePort() {
    if (this.chequePort == null) {
      LOGGER.info("Initializing ChequePort on: " + this.endpoint);
      try {
        final URL url = new URL(this.endpoint + "/ChequeService?wsdl");
        this.chequePort = new ChequeWSImplService(url).getChequeWSImplPort();
      } catch (final MalformedURLException e) {
        throw new SOAPClientException(
            String.format(MALFORMED_URL_ERROR, this.endpoint + "/ChequeService?wsdl"));
      }
    }
    return this.chequePort;
  }

  /**
   * Currently broken due to void returning methods in wsdl. Maybe it could return at least
   * EmptyType?
   */
  public CollectionPortType getCollectionPort() {
    if (this.collectionPort == null) {
      LOGGER.info("Initializing CollectionPort on: " + this.endpoint);
      try {
        final URL url = new URL(this.endpoint + "/CollectionService?wsdl");
        this.collectionPort = new CollectionWSImplService(url).getCollectionWSImplPort();
      } catch (final MalformedURLException e) {
        throw new SOAPClientException(
            String.format(MALFORMED_URL_ERROR, this.endpoint + "/CollectionService?wsdl"));
      }
    }
    return this.collectionPort;
  }

  public ContractsPortType getContractsPort() {
    if (this.contractsPort == null) {
      LOGGER.info("Initializing ContractsPort on: " + this.endpoint);
      try {
        final URL url = new URL(this.endpoint + "/ContractService?wsdl");
        this.contractsPort = new ContractWSImplService(url).getContractWSImplPort();
      } catch (final MalformedURLException e) {
        throw new SOAPClientException(
            String.format(MALFORMED_URL_ERROR, this.endpoint + "/ContractService?wsdl"));
      }
    }
    return this.contractsPort;
  }

  public CreditApplicationsPortType getCreditApplicationsPort() {
    if (this.creditApplicationsPort == null) {
      LOGGER.info("Initializing CreditApplicationsPort on: " + this.endpoint);
      try {
        final URL url = new URL(this.endpoint + "/CreditApplicationService?wsdl");
        this.creditApplicationsPort =
            new CreditApplicationWSImplService(url).getCreditApplicationWSImplPort();
      } catch (final MalformedURLException e) {
        throw new SOAPClientException(
            String.format(MALFORMED_URL_ERROR, this.endpoint + "/CreditApplicationService?wsdl"));
      }
    }
    return this.creditApplicationsPort;
  }

  public CustomersPortType getCustomersPort() {
    if (this.customersPort == null) {
      LOGGER.info("Initializing CustomersPort on: " + this.endpoint);
      try {
        final URL url = new URL(this.endpoint + "/CustomerService?wsdl");
        this.customersPort = new CustomerWSImplService(url).getCustomerWSImplPort();
      } catch (final MalformedURLException e) {
        throw new SOAPClientException(
            String.format(MALFORMED_URL_ERROR, this.endpoint + "/CustomerService?wsdl"));
      }
    }
    return this.customersPort;
  }

  public DailyBatchPortType getDailyBatchPort() {
    if (this.dailyBatchPort == null) {
      LOGGER.info("Initializing DailyBatchPort on: " + this.endpoint);
      try {
        final URL url = new URL(this.endpoint + "/DailyBatchService?wsdl");
        this.dailyBatchPort = new DailyBatchWSImplService(url).getDailyBatchWSImplPort();
      } catch (final MalformedURLException e) {
        throw new SOAPClientException(
            String.format(MALFORMED_URL_ERROR, this.endpoint + "/DailyBatchService?wsdl"));
      }
    }
    return this.dailyBatchPort;
  }

  public ExternalResponsePortType getExternalResponsePort() {
    if (this.externalResponsePort == null) {
      LOGGER.info("Initializing ExternalResponsePort on: " + this.endpoint);
      try {
        final URL url = new URL(this.endpoint + "/ExternalResponseService?wsdl");
        this.externalResponsePort =
            new ExternalResponseWSImplService(url).getExternalResponseWSImplPort();
      } catch (final MalformedURLException e) {
        throw new SOAPClientException(
            String.format(MALFORMED_URL_ERROR, this.endpoint + "/ExternalResponseService?wsdl"));
      }
    }
    return this.externalResponsePort;
  }

  public ExtraPortType getExtraPort() {
    if (this.extraPort == null) {
      LOGGER.info("Initializing ExtraPort on: " + this.endpoint);
      try {
        final URL url = new URL(this.endpoint + "/ExtraService?wsdl");
        this.extraPort = new ExtraWSImplService(url).getExtraWSImplPort();
      } catch (final MalformedURLException e) {
        throw new SOAPClientException(
            String.format(MALFORMED_URL_ERROR, this.endpoint + "/ExtraService?wsdl"));
      }
    }
    return this.extraPort;
  }

  public InfraPortType getInfraPort() {
    if (this.infraPort == null) {
      LOGGER.info("Initializing InfraPort on: " + this.endpoint);
      try {
        final URL url = new URL(this.endpoint + "/InfraService?wsdl");
        this.infraPort = new InfraWSImplService(url).getInfraWSImplPort();
      } catch (final MalformedURLException e) {
        throw new SOAPClientException(
            String.format(MALFORMED_URL_ERROR, this.endpoint + "/InfraService?wsdl"));
      }
    }
    return this.infraPort;
  }

  public MulePortType getMulePort() {
    if (this.mulePort == null) {
      LOGGER.info("Initializing MulePort on: " + this.endpoint);
      try {
        final URL url = new URL(this.endpoint + "/MuleService?wsdl");
        this.mulePort = new MuleWsImplService(url).getMuleWsImplPort();
      } catch (final MalformedURLException e) {
        throw new SOAPClientException(
            String.format(MALFORMED_URL_ERROR, this.endpoint + "/MuleService?wsdl"));
      }
    }
    return this.mulePort;
  }

  public PaymentGatewayPortType getPaymentGatewayPort() {
    if (this.paymentGatewayPort == null) {
      LOGGER.info("Initializing PaymentGatewayPort on: " + this.endpoint);
      try {
        final URL url = new URL(this.endpoint + "/paymentGatewayService?wsdl");
        this.paymentGatewayPort =
            new PaymentGatewayWSImplService(url).getPaymentGatewayWSImplPort();
      } catch (final MalformedURLException e) {
        throw new SOAPClientException(
            String.format(MALFORMED_URL_ERROR, this.endpoint + "/paymentGatewayService?wsdl"));
      }
    }
    return this.paymentGatewayPort;
  }

  public PaymentsPortType getPaymentsPort() {
    if (this.paymentsPort == null) {
      LOGGER.info("Initializing PaymentsPort on: " + this.endpoint);
      try {
        final URL url = new URL(this.endpoint + "/PaymentService?wsdl");
        this.paymentsPort = new PaymentWSImplService(url).getPaymentWSImplPort();
      } catch (final MalformedURLException e) {
        throw new SOAPClientException(
            String.format(MALFORMED_URL_ERROR, this.endpoint + "/PaymentService?wsdl"));
      }
    }
    return this.paymentsPort;
  }

  public ProductsPortType getProductsPort() {
    if (this.productsPort == null) {
      LOGGER.info("Initializing ProductsPort on: " + this.endpoint);
      try {
        final URL url = new URL(this.endpoint + "/ProductService?wsdl");
        this.productsPort = new ProductWSImplService(url).getProductWSImplPort();
      } catch (final MalformedURLException e) {
        throw new SOAPClientException(
            String.format(MALFORMED_URL_ERROR, this.endpoint + "/ProductService?wsdl"));
      }
    }
    return this.productsPort;
  }

  public BankScrapingPortType getBankScrapingPort() {
    if (this.bankScrapingPort == null) {
      LOGGER.info("Initializing BankScrapingPort on: " + this.endpoint);
      try {
        final URL url = new URL(this.endpoint + "/BankScraping?wsdl");
        this.bankScrapingPort = new BankScrapingWSImplService(url).getBankScrapingWSImplPort();
      } catch (final MalformedURLException e) {
        throw new SOAPClientException(
            String.format(MALFORMED_URL_ERROR, this.endpoint + "/BankScraping?wsdl"));
      }
    }
    return this.bankScrapingPort;
  }

  public void createAndSaveKibanaLoggerDetails(final String message) {
    final KibanaLoggerDetails kibanaLoggerDetails =
        new KibanaLoggerDetails(this.kibanaLogger.kibanaLoggerWithNextProcessStep());
    kibanaLoggerDetails.setMessage(message);
    kibanaLoggerDetails.save();
  }
}
