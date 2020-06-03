package bindings.commons.automation.salesforce.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.Map;

@JsonInclude (Include.NON_NULL)
public class SfCollectionOrder {

  public static final String SF_COLLECTION_ORDER_ID = "Id";
  public static final String SF_COLLECTION_ORDER_BACKEND_ID = "BackendId__c";
  public static final String SF_COLLECTION_ORDER_IS_SENT = "IsSent__c";

  @JsonAlias (SF_COLLECTION_ORDER_ID)
  public String sfCollectionId;

  @JsonAlias (SF_COLLECTION_ORDER_BACKEND_ID)
  public Long collectionId;

  @JsonAlias (SF_COLLECTION_ORDER_IS_SENT)
  public boolean sent;

  private SfCollectionOrder() {
  }

  public SfCollectionOrder(final Map map) {
    this.sfCollectionId = (String) map.get(SF_COLLECTION_ORDER_ID);
    this.collectionId = Long.parseLong((String) map.get(SF_COLLECTION_ORDER_BACKEND_ID));
  }

  @Override
  public String toString() {
    return "SfCollectionOrder{" +
        "sfCollectionId='" + this.sfCollectionId + '\'' +
        ", collectionId=" + this.collectionId +
        ", sent=" + this.sent +
        '}';
  }
}
