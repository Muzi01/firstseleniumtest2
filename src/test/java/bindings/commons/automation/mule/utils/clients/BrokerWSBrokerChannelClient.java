package bindings.commons.automation.mule.utils.clients;

import com.ipfdigital.automation.mule.model.mule.development.brokerws.broker.channel.AcceptOfferRequest;
import com.ipfdigital.automation.mule.model.mule.development.brokerws.broker.channel.AcceptOfferResponse;
import com.ipfdigital.automation.mule.model.mule.development.brokerws.broker.channel.ChangeSelectedProductRequest;
import com.ipfdigital.automation.mule.model.mule.development.brokerws.broker.channel.ChangeSelectedProductResponse;
import com.ipfdigital.automation.mule.model.mule.development.brokerws.broker.channel.CheckCustomerRequest;
import com.ipfdigital.automation.mule.model.mule.development.brokerws.broker.channel.CheckCustomerResponse;
import com.ipfdigital.automation.mule.model.mule.development.brokerws.broker.channel.CreateLoanApplicationRequest;
import com.ipfdigital.automation.mule.model.mule.development.brokerws.broker.channel.CreateLoanApplicationResponse;
import com.ipfdigital.automation.mule.model.mule.development.brokerws.broker.channel.DownloadDocumentsRequest;
import com.ipfdigital.automation.mule.model.mule.development.brokerws.broker.channel.DownloadDocumentsResponse;
import com.ipfdigital.automation.mule.model.mule.development.brokerws.broker.channel.SendCommentToCustomerCareRequest;
import com.ipfdigital.automation.mule.model.mule.development.brokerws.broker.channel.SendCommentToCustomerCareResponse;
import com.ipfdigital.automation.mule.model.mule.development.brokerws.broker.channel.SetPayoutDateRequest;
import com.ipfdigital.automation.mule.model.mule.development.brokerws.broker.channel.SetPayoutDateResponse;
import com.ipfdigital.automation.mule.model.mule.development.brokerws.broker.channel.UpdateCustomerRequest;
import com.ipfdigital.automation.mule.model.mule.development.brokerws.broker.channel.UpdateCustomerResponse;
import com.ipfdigital.automation.mule.model.mule.development.brokerws.broker.channel.UploadDocumentsRequest;
import com.ipfdigital.automation.mule.model.mule.development.brokerws.broker.channel.UploadDocumentsResponse;
import com.ipfdigital.automation.mule.utils.Tools;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wss4j.common.crypto.Crypto;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.springframework.ws.soap.saaj.SaajSoapMessage;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.security.wss4j2.support.CryptoFactoryBean;
import org.springframework.ws.transport.HeadersAwareSenderWebServiceConnection;
import org.springframework.ws.transport.context.TransportContext;
import org.springframework.ws.transport.context.TransportContextHolder;

import java.io.IOException;

public class BrokerWSBrokerChannelClient extends WebServiceGatewaySupport {
  private static final Logger LOG = LogManager.getLogger(BrokerWSBrokerChannelClient.class);
  private final String basicAuthorization;
  private final String keystoreFilename;
  private final String keyStorePass;
  private final String keyStoreAlias;
  private String baseUrl;

  public BrokerWSBrokerChannelClient(final String environment) {
    final MulePropertyProvider mulePropertyProvider = new MulePropertyProvider(environment);
    baseUrl = mulePropertyProvider.getMulePublicUrlProperty();
    baseUrl = baseUrl + "/BrokerWS-Broker-Channel";
    basicAuthorization =
        (new Tools()).getAuthorizationBasic(mulePropertyProvider.getDefaultBrokerName(),
            mulePropertyProvider.getDefaultBrokerPassword());
    keystoreFilename = mulePropertyProvider.getDefaultKeyStoreLocation();
    keyStorePass = mulePropertyProvider.getDefaultKeyStorePassword();
    keyStoreAlias = mulePropertyProvider.getDefaultKeyStoreAlias();
    setInterceptors(new ClientInterceptor[] {securityInterceptor()});
  }

  public String getBaseUrl() {
    return baseUrl;
  }

  public AcceptOfferResponse getAcceptOfferResponse(final AcceptOfferRequest request) {
    final SoapActionCallback soapActionCallback = getSoapActionCallback("AcceptOffer");
    return (AcceptOfferResponse) getWebServiceTemplate().marshalSendAndReceive(baseUrl, request,
        soapActionCallback);
  }

  public ChangeSelectedProductResponse getChangeSelectedProductResponse(
      final ChangeSelectedProductRequest request) {
    final SoapActionCallback soapActionCallback = getSoapActionCallback("ChangeSelectedProduct");
    return (ChangeSelectedProductResponse) getWebServiceTemplate().marshalSendAndReceive(baseUrl,
        request, soapActionCallback);
  }

  public CheckCustomerResponse getCheckCustomerResponse(final CheckCustomerRequest request) {
    final SoapActionCallback soapActionCallback = getSoapActionCallback("CheckCustomer");
    return (CheckCustomerResponse) getWebServiceTemplate().marshalSendAndReceive(baseUrl, request,
        soapActionCallback);
  }

  public CreateLoanApplicationResponse getCreateLoanApplicationResponse(
      final CreateLoanApplicationRequest request) {
    final SoapActionCallback soapActionCallback = getSoapActionCallback("CreateLoanApplication");
    return (CreateLoanApplicationResponse) getWebServiceTemplate().marshalSendAndReceive(baseUrl,
        request, soapActionCallback);
  }

  public DownloadDocumentsResponse getDownloadDocumentsResponse(
      final DownloadDocumentsRequest request) {
    final SoapActionCallback soapActionCallback = getSoapActionCallback("DownloadDocuments");
    return (DownloadDocumentsResponse) getWebServiceTemplate().marshalSendAndReceive(baseUrl,
        request, soapActionCallback);
  }

  public SendCommentToCustomerCareResponse getSendCommentToCustomerCareResponse(
      final SendCommentToCustomerCareRequest request) {
    final SoapActionCallback soapActionCallback =
        getSoapActionCallback("SendCommentToCustomerCare");
    return (SendCommentToCustomerCareResponse) getWebServiceTemplate()
        .marshalSendAndReceive(baseUrl, request, soapActionCallback);
  }

  public SetPayoutDateResponse getSetPayoutDateResponse(final SetPayoutDateRequest request) {
    final SoapActionCallback soapActionCallback = getSoapActionCallback("SetPayoutDate");
    return (SetPayoutDateResponse) getWebServiceTemplate().marshalSendAndReceive(baseUrl, request,
        soapActionCallback);
  }

  public UpdateCustomerResponse getUpdateCustomerResponse(final UpdateCustomerRequest request) {
    final SoapActionCallback soapActionCallback = getSoapActionCallback("UpdateCustomer");
    return (UpdateCustomerResponse) getWebServiceTemplate().marshalSendAndReceive(baseUrl, request,
        soapActionCallback);
  }

  public UploadDocumentsResponse getUploadDocumentsResponse(final UploadDocumentsRequest request) {
    final SoapActionCallback soapActionCallback = getSoapActionCallback("UploadDocuments");
    return (UploadDocumentsResponse) getWebServiceTemplate().marshalSendAndReceive(baseUrl, request,
        soapActionCallback);
  }

  private SoapActionCallback getSoapActionCallback(final String action) {
    return new SoapActionCallback(action) {
      @Override
      public void doWithMessage(final WebServiceMessage webServiceMessage) throws IOException {
        final TransportContext context = TransportContextHolder.getTransportContext();
        final HeadersAwareSenderWebServiceConnection connection =
            (HeadersAwareSenderWebServiceConnection) context.getConnection();
        ((SaajSoapMessage) webServiceMessage).setSoapAction(action);
        connection.addRequestHeader("Authorization", basicAuthorization);
      }
    };
  }

  private Wss4jSecurityInterceptor securityInterceptor() {
    final Wss4jSecurityInterceptor wss4jSecurityInterceptor = new Wss4jSecurityInterceptor();
    wss4jSecurityInterceptor.setSecurementActions("Signature");
    wss4jSecurityInterceptor.setSecurementUsername(keyStoreAlias);
    wss4jSecurityInterceptor.setSecurementPassword(keyStorePass);

    try {
      wss4jSecurityInterceptor.setSecurementSignatureCrypto(getCryptoFactoryBean());
      wss4jSecurityInterceptor.afterPropertiesSet();
    } catch (final Exception var3) {
      LOG.error(var3.getMessage(), var3);
    }

    return wss4jSecurityInterceptor;
  }

  private Crypto getCryptoFactoryBean() {
    final CryptoFactoryBean cryptoFactoryBean = new CryptoFactoryBean();
    cryptoFactoryBean.setKeyStoreType("JKS");
    cryptoFactoryBean.setDefaultX509Alias(keyStoreAlias);
    cryptoFactoryBean.setKeyStorePassword(keyStorePass);

    try {
      cryptoFactoryBean.setKeyStoreLocation(new ClassPathResource (keystoreFilename));
      cryptoFactoryBean.afterPropertiesSet();
      return cryptoFactoryBean.getObject();
    } catch (final Exception var3) {
      LOG.error(var3.getMessage(), var3);
      return null;
    }
  }
}
