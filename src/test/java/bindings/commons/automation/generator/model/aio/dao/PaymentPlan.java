package bindings.commons.automation.generator.model.aio.dao;

import java.sql.Date;
import java.sql.Timestamp;

public class PaymentPlan {
  public long id;
  public long entityVersion;
  public Date created;
  public Timestamp updated;
  public double dailyInterest;
  public int interestAmountTotal;
  public Date lastDueDate;
  public int lastPaymentAmount;
  public int maximumPaymentPeriod;
  public double mmp;
  public int openingFeeTotal;
  public int paymentDayOfMonth;
  public int provisionFeeTotal;
  public String repaymentIban;
  public int repaymentTotal;
  public int totalAnnualSum;
  public int totalCostOfLoan;
  public int totalMonthlySum;
  public long contractId;
  public int mandatoryFeeTotal;
}
