package bindings.commons.automation.generator.model.aio;

import java.sql.Date;
import java.sql.Timestamp;

public class ContractTerminationNotice {
  public Long id;
  public Long entityVersion;
  public Date created;
  public Long contractId;
  public Long documentDeliveryId;
  public String type;
  public Timestamp updated;
  public Long invoiceId;
}
