package bindings.commons.automation.rest.client;

import com.ipfdigital.automation.rest.client.exceptions.HttpClientException;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class HTTPEntity {
  private static final Logger LOGGER = LogManager.getLogger(HTTPEntity.class);

  private HTTPEntity() {
    throw new IllegalStateException("HTTPEntity is utility class and should not be instantiated!");
  }

  public static AbstractHttpEntity getEntity(final HTTPEntityType entityType, final Object entity) {
    if (entityType == HTTPEntityType.JSON) {
      return getStringEntity(entity);

    } else {
      return getUrlEncodedFormEntity(entity);
    }
  }

  private static StringEntity getStringEntity(final Object object) {
    try {
      return new StringEntity (object.toString());

    } catch (final UnsupportedEncodingException eStackTrace) {
      final String eMessage =
          String.format("Unsupported encoding in message: %s", object.toString());
      LOGGER.error(eMessage);
      throw new HttpClientException(eMessage, eStackTrace);
    }
  }

  @SuppressWarnings("unchecked")
  private static UrlEncodedFormEntity getUrlEncodedFormEntity(final Object object) {
    final List<NameValuePair> form = (List<NameValuePair>) object;
    if (form == null || form.isEmpty()) {
      throw new IllegalStateException("Provided object cannot be empty!");
    }

    return new UrlEncodedFormEntity (form, Consts.UTF_8);
  }
}
