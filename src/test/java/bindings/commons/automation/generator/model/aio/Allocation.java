package bindings.commons.automation.generator.model.aio;

import java.sql.Date;
import java.sql.Timestamp;

public class Allocation {
  public Long id;
  public Long entityVersion;
  public Date created;
  public int amount;
  public Long cashFlowId;
  public Long paymentId;
  public Timestamp updated;
  public Long invoiceId;
}
