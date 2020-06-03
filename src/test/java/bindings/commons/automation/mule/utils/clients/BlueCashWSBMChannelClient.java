package bindings.commons.automation.mule.utils.clients;

import com.ipfdigital.automation.mule.model.mule.development.bluecashws.bm.channel.BlueCashTransferStatusReq;
import com.ipfdigital.automation.mule.model.mule.development.bluecashws.bm.channel.BlueCashTransferStatusResp;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class BlueCashWSBMChannelClient extends WebServiceGatewaySupport {

  private static final String SERVICE = "BlueCashTransferStatus";
  private String baseUrl;

  public BlueCashWSBMChannelClient(final String environment) {
    final MulePropertyProvider mulePropertyProvider = new MulePropertyProvider(environment);
    this.baseUrl = mulePropertyProvider.getMuleInternalUrlProperty();
    this.baseUrl += "/" + SERVICE;
  }

  public String getBaseUrl() {

    return this.baseUrl;
  }

  public BlueCashTransferStatusResp getBlueCashTransferStatus(
      final BlueCashTransferStatusReq request) {

    return (BlueCashTransferStatusResp) getWebServiceTemplate()
        .marshalSendAndReceive(this.baseUrl,
            request);

  }

}
