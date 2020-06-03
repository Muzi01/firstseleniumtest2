package bindings.commons.automation.mule.utils.clients;

import com.ipfdigital.automation.mule.model.mule.development.documents.aio.channel.UploadDocumentsRequest;
import com.ipfdigital.automation.mule.model.mule.development.documents.aio.channel.UploadDocumentsResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class DocumentsAIOChannelClient extends WebServiceGatewaySupport {

  private static final String SERVICE = "Documents-AIO-Channel";
  private String baseUrl;

  public DocumentsAIOChannelClient(final String environment) {
    final MulePropertyProvider mulePropertyProvider = new MulePropertyProvider(environment);
    this.baseUrl = mulePropertyProvider.getMuleInternalUrlProperty();
    this.baseUrl += "/" + SERVICE;
  }

  public String getBaseUrl() {

    return this.baseUrl;
  }

  public UploadDocumentsResponse getUploadDocuments(final UploadDocumentsRequest request) {

    return (UploadDocumentsResponse) getWebServiceTemplate()
        .marshalSendAndReceive(this.baseUrl,
            request,
            new SoapActionCallback("UploadDocuments"));
  }

}
