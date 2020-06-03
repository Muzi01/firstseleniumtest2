package bindings.commons.automation.generator.model.aio.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class InvoicingParams {
  public Long id;
  public Long entityVersion;
  public Date created;
  public Date closed;
  public Date firstDueDate;
  public int mmp;
  public Long contractId;
  public Timestamp updated;
  public Date nextDueDate;
  public MmpCalculationStrategy mmpCalculationStrategy;
  public Event event;
  public BigDecimal minimalPrincipalValue;
  public String minimalPrincipalType;

}
