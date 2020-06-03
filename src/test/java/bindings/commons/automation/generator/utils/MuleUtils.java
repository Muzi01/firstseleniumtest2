package bindings.commons.automation.generator.utils;

import com.ipfdigital.automation.DocumentsConstants;
import com.ipfdigital.automation.mule.model.mule.development.documents.aio.channel.UploadDocumentUtils;
import com.ipfdigital.automation.mule.model.mule.development.documents.aio.channel.UploadDocumentsRequest;
import com.ipfdigital.automation.mule.model.mule.development.documents.aio.channel.UploadDocumentsResponse;
import com.ipfdigital.automation.mule.utils.clients.DocumentsAIOChannelClient;
import com.ipfdigital.automation.salesforce.SFAttachmentType;
import com.ipfdigital.automation.salesforce.rest.v2.SalesforceProvider;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MuleUtils {

  private static final Logger LOG = LogManager.getLogger(MuleUtils.class);

  private MuleUtils() {
  }

  public static boolean uploadDocumentToMule(final long accountNumber,
      final SFAttachmentType attachmentType,
      final Environment environment) {
    final Jaxb2Marshaller marshaller =
        UploadDocumentUtils.getJaxb2MarshallerSetupForUploadDocuments();
    final DocumentsAIOChannelClient documentsAIOChannelClient =
        getDocumentsAIOChannelClient(marshaller, environment);
    final UploadDocumentsRequest request = getUploadDocumentsRequest(accountNumber, attachmentType);
    final UploadDocumentsResponse uploadDocumentsResponse =
        documentsAIOChannelClient.getUploadDocuments(request);
    return uploadDocumentsResponse.getResponseStatus().isSuccess();
  }

  private static DocumentsAIOChannelClient getDocumentsAIOChannelClient(
      final Jaxb2Marshaller marshaller,
      final Environment environment) {
    final DocumentsAIOChannelClient documentsAIOChannelClient =
        new DocumentsAIOChannelClient(environment.envName);
    documentsAIOChannelClient.setMarshaller(marshaller);
    documentsAIOChannelClient.setUnmarshaller(marshaller);
    return documentsAIOChannelClient;
  }

  private static UploadDocumentsRequest getUploadDocumentsRequest(final long accountNumber,
      final SFAttachmentType attachmentType) {
    final UploadDocumentsRequest.Body requestBody = getBodyForUploadDocuments(attachmentType);
    final String randomPath = RandomStringUtils.random(20, true, true) + attachmentType.toString();
    requestBody.setPath(randomPath);
    final UploadDocumentsRequest.Body.Keys requestKeys = getKeysForUploadDocuments(accountNumber);
    final UploadDocumentsRequest request = new UploadDocumentsRequest();
    requestBody.getKeys().add(requestKeys);
    request.setBody(requestBody);
    return request;
  }

  private static UploadDocumentsRequest.Body.Keys getKeysForUploadDocuments(
      final long accountNumber) {
    final UploadDocumentsRequest.Body.Keys requestKeys = new UploadDocumentsRequest.Body.Keys();
    requestKeys.setType(DocumentsConstants.ACCOUNT.getValue());
    requestKeys.setValue(String.valueOf(accountNumber));
    return requestKeys;
  }

  private static UploadDocumentsRequest.Body getBodyForUploadDocuments(
      final SFAttachmentType attachmentType) {
    final UploadDocumentsRequest.Body requestBody = new UploadDocumentsRequest.Body();
    requestBody.setFileName(String.format("%s.jpg", attachmentType.toString()));
    requestBody.setContentType(DocumentsConstants.IMAGE_JPG_TYPE.getValue());
    requestBody.setFileType(attachmentType.toString());
    requestBody.setContent(extractBytesFromImage(attachmentType.getImagePath()));
    return requestBody;
  }

  private static byte[] extractBytesFromImage(final String fileLocationPath) {
    try (final InputStream imagePath =
        SalesforceProvider.class.getResourceAsStream(fileLocationPath)) {
      final BufferedImage bufferedImage = ImageIO.read(imagePath);
      return encodeToBytes(bufferedImage, DocumentsConstants.JPG.getValue());
    } catch (final IOException e) {
      LOG.error(String.format("Exception for file: %s : %s", fileLocationPath, e));
      throw new IllegalArgumentException(
          String.format("Exception for file: %s : %s", fileLocationPath, e));
    }
  }

  private static byte[] encodeToBytes(final BufferedImage image, final String type)
      throws IOException {
    try (final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
      ImageIO.write(image, type, byteArrayOutputStream);
      return byteArrayOutputStream.toByteArray();
    }
  }
}
