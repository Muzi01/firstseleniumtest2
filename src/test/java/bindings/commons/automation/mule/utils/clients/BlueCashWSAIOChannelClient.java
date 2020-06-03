package bindings.commons.automation.mule.utils.clients;

import com.ipfdigital.automation.mule.model.mule.development.bluecashws.aio.channel.MakeTransfersRequest;
import com.ipfdigital.automation.mule.model.mule.development.bluecashws.aio.channel.MakeTransfersResponse;
import com.ipfdigital.automation.mule.model.mule.development.bluecashws.aio.channel.RetryTransfersRequest;
import com.ipfdigital.automation.mule.model.mule.development.bluecashws.aio.channel.RetryTransfersResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class BlueCashWSAIOChannelClient extends WebServiceGatewaySupport {

  private static final String SERVICE = "BlueCashWS-AIO-Channel";
  private String baseUrl;

  public BlueCashWSAIOChannelClient(final String environment) {
    final MulePropertyProvider mulePropertyProvider = new MulePropertyProvider(environment);
    this.baseUrl = mulePropertyProvider.getMuleInternalUrlProperty();
    this.baseUrl += "/" + SERVICE;
  }

  public String getBaseUrl() {

    return this.baseUrl;
  }

  public MakeTransfersResponse getMakeTransfersResponse(final MakeTransfersRequest request) {

    return (MakeTransfersResponse) getWebServiceTemplate()
        .marshalSendAndReceive(this.baseUrl,
            request,
            new SoapActionCallback("MakeTransfers"));

  }

  public RetryTransfersResponse getRetryransfersResponse(final RetryTransfersRequest request) {

    return (RetryTransfersResponse) getWebServiceTemplate()
        .marshalSendAndReceive(this.baseUrl,
            request,
            new SoapActionCallback("RetryTransfers"));

  }
}
