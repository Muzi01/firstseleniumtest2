package bindings.commons.automation.generator.model.aio;

import java.sql.Date;
import java.sql.Timestamp;

public class ExtraServiceSubscription {
  public Long id;
  public Long entityVersion;
  public Date created;
  public Date closed;
  public String extraService;
  public int price;
  public Long contractId;
  public Timestamp updated;
  public String issuerMode;
  public String issuedBy;
  public Date validFromDateTime;
  public Date validToDateTime;
  public String deactivationReason;
  public Date deactivationRequestedOn;
}
