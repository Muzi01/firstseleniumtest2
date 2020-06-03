package bindings.commons.automation.generator.model.aio;

import java.sql.Date;
import java.sql.Timestamp;

public class Payment {
  public Long id;
  public Long entityVersion;
  public int amount;
  public Date paymentDate;
  public Date valueDate;
  public Timestamp updated;
  public Date closed;
  public String extraInfo;
  public String stateType;
  public Date created;
  public String country;
}
