package bindings.commons.automation.generator.model.aio;

import java.sql.Date;
import java.sql.Timestamp;

public class CollectionOrder {
  public Long id;
  public Long entityVersion;
  public Date created;
  public String closeType;
  public Date closed;
  public Date collectionDate;
  public Date submitted;
  public Long invoiceId;
  public String data;
  public int amount;
  public Timestamp updated;
  public Long collectionCompanyId;
  public String type;
  public Long documentDeliveryId;
  public int level;
  public String strategy;
  public String balanceClose;
  public int creditedAmount;
  public String debtRegisterStatus;
  public String sendingState;
}
