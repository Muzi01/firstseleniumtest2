package bindings.commons.automation.generator.model.aio.dao;

import java.sql.Blob;
import java.sql.Date;
import java.sql.Timestamp;

public class DocumentDelivery {
  public long id;
  public long entityVersion;
  public Date created;
  public String externalId;
  public Date externalSentStamp;
  public String externalDocId;
  public Blob data;
  public Date submitted;
  public String type;
  public String country;
  public String subType;
  public String deliveryMethod;
  public long customerId;
  public String status;
  public Timestamp updated;
  public String errorCode;
  public String emailId;
}
