package bindings.commons.automation.generator.model.aio;

import java.sql.Date;
import java.sql.Timestamp;

public class BankPaymentBatch {
  public Long id;
  public Long entityVersion;
  public Date created;
  public String bank;
  public String country;
  public String data;
  public String externalId;
  public String processor;
  public Timestamp updated;
  public String createdBy;
  public String processingStatus;
}
