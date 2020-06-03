package bindings.commons.automation.generator.model.aio;

import java.sql.Date;
import java.sql.Timestamp;

public class CashFlow {
  public String dType;
  public Long id;
  public Long entityVersion;
  public Date created;
  public int amount;
  public String type;
  public Date valueDate;
  public Timestamp updated;
  public Long parentId;
  public Long invoiceId;
  public Long contractId;
  public Long interestWrapperId;
  public double interest;
  public double dailyInterest;
}
