package bindings.commons.automation.generator.model.aio.dao;

import java.sql.Date;
import java.sql.Timestamp;

public class PaymentPlanRow {

  public long id;
  public long entityVersion;
  public Date created;
  public Timestamp updated;
  public int beginningBalance;
  public int interestAmount;
  public int openingFee;
  public Date paymentDate;
  public int paymentNumber;
  public int repayment;
  public int totalMonthly;
  public long paymentPlanId;
  public int mandatoryFee;
}
