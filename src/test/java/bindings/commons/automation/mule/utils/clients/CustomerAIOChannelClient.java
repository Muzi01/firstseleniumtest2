package bindings.commons.automation.mule.utils.clients;

import com.ipfdigital.automation.mule.model.mule.development.customer.aio.channel.CheckBankRequest;
import com.ipfdigital.automation.mule.model.mule.development.customer.aio.channel.CheckBankResponse;
import com.ipfdigital.automation.mule.model.mule.development.customer.aio.channel.GetTmxSessionIdRequest;
import com.ipfdigital.automation.mule.model.mule.development.customer.aio.channel.GetTmxSessionIdResponse;
import com.ipfdigital.automation.mule.model.mule.development.customer.aio.channel.SearchForActiveLegacyCustomerDataRequest;
import com.ipfdigital.automation.mule.model.mule.development.customer.aio.channel.SearchForActiveLegacyCustomerDataResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class CustomerAIOChannelClient extends WebServiceGatewaySupport {

  private static final String SERVICE = "Customer-AIO-Channel";
  private String baseUrl;

  public CustomerAIOChannelClient(final String environment) {
    final MulePropertyProvider mulePropertyProvider = new MulePropertyProvider(environment);
    this.baseUrl = mulePropertyProvider.getMuleInternalUrlProperty();
    this.baseUrl += "/" + SERVICE;
  }

  public String getBaseUrl() {

    return this.baseUrl;
  }

  public CheckBankResponse getCheckBankResponse(final CheckBankRequest request) {

    return (CheckBankResponse) getWebServiceTemplate()
        .marshalSendAndReceive(this.baseUrl,
            request,
            new SoapActionCallback("CheckBank"));
  }

  public GetTmxSessionIdResponse getGetTmxSessionIdResponse(final GetTmxSessionIdRequest request) {

    return (GetTmxSessionIdResponse) getWebServiceTemplate()
        .marshalSendAndReceive(this.baseUrl,
            request,
            new SoapActionCallback("GetTmxSessionId"));
  }

  public SearchForActiveLegacyCustomerDataResponse getGearchForActiveLegacyCustomerDataResponse(
      final SearchForActiveLegacyCustomerDataRequest request) {

    return (SearchForActiveLegacyCustomerDataResponse) getWebServiceTemplate()
        .marshalSendAndReceive(this.baseUrl,
            request,
            new SoapActionCallback("SearchForActiveLegacyCustomerData"));
  }
}
