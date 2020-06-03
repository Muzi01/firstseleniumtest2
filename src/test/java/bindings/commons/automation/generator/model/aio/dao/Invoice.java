package bindings.commons.automation.generator.model.aio.dao;

import java.sql.Date;
import java.sql.Timestamp;

public class Invoice {
  public long id;
  public long entityVersion;
  public int amount;
  public Date dueDate;
  public Date invoiceDate;
  public String invoiceNumber;
  public String referenceNumber;
  public String invoiceType;
  public long contractId;
  public long documentDeliveryId;
  public Timestamp updated;
  public Date closed;
  public String stateType;
  public long parentInvoiceId;
  public long reminderSMSId;
  public Date onHold;
  public int childInvoicesOrder;
  public Date created;
  public Date firstDueDate;
}
