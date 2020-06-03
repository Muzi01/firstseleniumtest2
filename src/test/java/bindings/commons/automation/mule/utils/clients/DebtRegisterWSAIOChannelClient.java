package bindings.commons.automation.mule.utils.clients;

import com.ipfdigital.automation.mule.model.mule.development.debtregistryws.aio.channel.FetchRegistryDataRequest;
import com.ipfdigital.automation.mule.model.mule.development.debtregistryws.aio.channel.FetchRegistryDataResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class DebtRegisterWSAIOChannelClient extends WebServiceGatewaySupport {

  private static final String SERVICE = "DebtRegistryWS-AIO-Channel";
  private String baseUrl;

  public DebtRegisterWSAIOChannelClient(final String environment) {
    final MulePropertyProvider mulePropertyProvider = new MulePropertyProvider(environment);
    this.baseUrl = mulePropertyProvider.getMuleInternalUrlProperty();
    this.baseUrl += "/" + SERVICE;
  }

  public String getBaseUrl() {

    return this.baseUrl;
  }

  public FetchRegistryDataResponse getFetchRegistryData(final FetchRegistryDataRequest request) {

    return (FetchRegistryDataResponse) getWebServiceTemplate()
        .marshalSendAndReceive(this.baseUrl,
            request,
            new SoapActionCallback("FetchRegistryData"));
  }

}
