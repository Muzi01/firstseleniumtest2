package bindings.commons.automation.salesforce.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.Map;

@JsonInclude (Include.NON_NULL)
public class SfAttachment {

  public static final String SF_ATTACHMENT_ID = "Id";
  public static final String SF_ATTACHMENT_FILE_NAME = "File_Name__c";
  public static final String SF_ATTACHMENT_PREVIEW_LINK = "Preview_Link__c";
  public static final String SF_ATTACHMENT_TYPE = "Type__c";


  @JsonAlias (SF_ATTACHMENT_ID)
  public String sfAttachmentId;

  @JsonAlias (SF_ATTACHMENT_FILE_NAME)
  public String fileName;

  @JsonAlias (SF_ATTACHMENT_PREVIEW_LINK)
  public String previewLink;

  @JsonAlias (SF_ATTACHMENT_TYPE)
  public String type;

  private SfAttachment() {
  }

  public SfAttachment(final Map map) {
    this.sfAttachmentId = (String) map.get(SF_ATTACHMENT_ID);
    this.fileName = (String) map.get(SF_ATTACHMENT_FILE_NAME);
    this.previewLink = (String) map.get(SF_ATTACHMENT_PREVIEW_LINK);
    this.type = (String) map.get(SF_ATTACHMENT_TYPE);
  }

  public String getSfAttachmentId() {
    return this.sfAttachmentId;
  }

  public String getFileName() {
    return this.fileName;
  }

  public String getPreviewLink() {
    return this.previewLink;
  }

  public String getType() {
    return this.type;
  }
}
