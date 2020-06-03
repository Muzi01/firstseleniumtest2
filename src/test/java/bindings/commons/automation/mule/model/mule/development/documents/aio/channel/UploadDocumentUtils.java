package bindings.commons.automation.mule.model.mule.development.documents.aio.channel;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import java.util.HashMap;
import java.util.Map;

public class UploadDocumentUtils {
  private static final String CONTEXT_PATH_ADDRESS =
      "com.ipfdigital.automation.mule.model.mule.development.documents.aio.channel";
  private static final String JAXB_FORMATTED_OUTPUT = "jaxb.formatted.output";

  private UploadDocumentUtils() {
    throw new IllegalStateException(
        "UploadDocumentUtils is utility class and should not be instantiated!");
  }

  public static Jaxb2Marshaller getJaxb2MarshallerSetupForUploadDocuments() {
    final Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    final Map<String, Boolean> map = new HashMap<>();

    map.put(JAXB_FORMATTED_OUTPUT, true);
    marshaller.setMarshallerProperties(map);
    marshaller.setContextPath(CONTEXT_PATH_ADDRESS);

    return marshaller;
  }
}
