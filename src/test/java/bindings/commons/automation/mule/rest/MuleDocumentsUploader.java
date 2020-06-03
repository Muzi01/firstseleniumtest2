package bindings.commons.automation.mule.rest;

import com.ipfdigital.automation.generator.utils.Environment;
import com.ipfdigital.automation.generator.utils.MuleUtils;
import com.ipfdigital.automation.mule.exception.MuleDocumentUploadException;
import com.ipfdigital.automation.salesforce.SFAttachmentType;
import com.ipfdigital.automation.salesforce.rest.v2.SalesforceProvider;

import java.util.List;
import java.util.Objects;

public class MuleDocumentsUploader {

  public void upload(final long customerId, final SalesforceProvider salesforceProvider,
      final String accountId, final Environment environment,
      final List<SFAttachmentType> attachmentsToSend) {

    attachmentsToSend.forEach(at -> {
      if (Objects.nonNull(at.getImagePath())) {
        uploadAttachmentToMule(customerId, at, environment);
        salesforceProvider.updateAttachmentVerification(customerId, at);
      } else {
        salesforceProvider.uploadAttachment(accountId, at);
      }
    });
  }

  private void uploadAttachmentToMule(final long customerId, final SFAttachmentType at,
      final Environment environment) {
    if (!MuleUtils.uploadDocumentToMule(customerId, at, environment)) {

      throw new MuleDocumentUploadException(
          "Document was not uploaded properly to MULE, document: " + at.toString());
    }
  }

}
