package bindings.commons.automation.mule.utils.clients;

import com.ipfdigital.automation.mule.model.mule.development.collection.aio.channel.UploadCollectionOrderBatchRequest;
import com.ipfdigital.automation.mule.model.mule.development.collection.aio.channel.UploadCollectionOrderBatchResponse;
import com.ipfdigital.automation.mule.model.mule.development.collection.aio.channel.UploadCollectionOrderToSellBatchRequest;
import com.ipfdigital.automation.mule.model.mule.development.collection.aio.channel.UploadCollectionOrderToSellBatchResponse;
import com.ipfdigital.automation.mule.utils.exceptions.NotSupportedEnvironmentException;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class CollectionAIOChannelClient extends WebServiceGatewaySupport {

  private static final String SERVICE = "Collection-AIO-Channel";
  private static final Logger LOG = LogManager.getLogger(CollectionAIOChannelClient.class);
  private final String environment;
  private String baseUrl;

  public CollectionAIOChannelClient(final String environment) {
    this.environment = environment;
  }

  void setupUrl() {
    if (StringUtils.isNotBlank(this.baseUrl)) {
      return;
    }
    final MulePropertyProvider mulePropertyProvider = new MulePropertyProvider(this.environment);
    this.baseUrl = mulePropertyProvider.getMuleInternalUrlProperty();
    if (this.baseUrl == null) {
      final String logMessage = String.format("Not supported environment: %s", this.environment);
      LOG.error(logMessage);
      throw new NotSupportedEnvironmentException(logMessage);
    }
    this.baseUrl += "/" + SERVICE;

  }

  public String getBaseUrl() {
    setupUrl();
    return this.baseUrl;
  }

  public UploadCollectionOrderBatchResponse getUploadCollectionOrderBatchResponse(
      final UploadCollectionOrderBatchRequest request) {
    setupUrl();
    return (UploadCollectionOrderBatchResponse) getWebServiceTemplate()
        .marshalSendAndReceive(this.baseUrl,
            request,
            new SoapActionCallback("UploadCollectionOrderBatch"));
  }

  public UploadCollectionOrderToSellBatchResponse getUploadCollectionOrderToSellBatchResponse(
      final UploadCollectionOrderToSellBatchRequest request) {
    setupUrl();
    return (UploadCollectionOrderToSellBatchResponse) getWebServiceTemplate()
        .marshalSendAndReceive(this.baseUrl,
            request,
            new SoapActionCallback("UploadCollectionOrderToSellBatch"));
  }

}
