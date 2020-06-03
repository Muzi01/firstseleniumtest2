package bindings.commons.automation.salesforce.rest.v2;

import com.ipfdigital.automation.salesforce.model.SfAttachment;

import java.util.List;

import static com.ipfdigital.automation.salesforce.SFQuery.GET_ATTACHMENT;
import static java.util.stream.Collectors.toList;

public class SalesforceAttachmentProvider {

  private final SalesforceProvider salesforceProvider;

  public SalesforceAttachmentProvider(
      final SalesforceProvider salesforceProvider) {

    this.salesforceProvider = salesforceProvider;
  }

  public List<SfAttachment> getAttachments(final Long userId, final String terminationType) {

    final String query = String.format(GET_ATTACHMENT.getQuery(), terminationType, userId);
    return this.salesforceProvider.sendQueryAndGetRecords(query)
        .stream()
        .map(SfAttachment::new)
        .collect(toList());
  }
}
