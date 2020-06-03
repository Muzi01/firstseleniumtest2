package bindings.commons.automation.rest.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class HttpClient implements AutoCloseable {
  private static final Logger LOGGER = LogManager.getLogger(HttpClient.class);

  @Override
  public abstract void close();

  public abstract Response getLastResponse();

  public abstract void sendDELETE(Request request);

  public abstract void sendGET(Request request);

  public abstract void sendHEAD(Request request);

  public abstract void sendPATCH(Request request);

  public abstract void sendPOST(Request request);

  public abstract void sendPUT(Request request);

  public void sendRequest(final RequestType requestType, final Request request) {
    switch (requestType) {
      case DELETE:
        sendDELETE(request);
        break;
      case GET:
        sendGET(request);
        break;
      case HEAD:
        sendHEAD(request);
        break;
      case PATCH:
        sendPATCH(request);
        break;
      case POST:
        sendPOST(request);
        break;
      case PUT:
        sendPUT(request);
        break;

      default:
        final String eMessage =
            String.format("Unsupported request type: %s!", requestType.toString());
        LOGGER.error(eMessage);
        throw new UnsupportedOperationException(eMessage);
    }
  }
}
