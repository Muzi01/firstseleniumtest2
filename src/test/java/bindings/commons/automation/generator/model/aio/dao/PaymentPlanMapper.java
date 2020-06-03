package bindings.commons.automation.generator.model.aio.dao;


import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentPlanMapper implements ResultMapper<PaymentPlan> {

  @Override
  public PaymentPlan mapByResultSet(final ResultSet resultSet) throws SQLException {
    final PaymentPlan paymentPlan = new PaymentPlan();
    paymentPlan.id = resultSet.getLong("id");
    paymentPlan.entityVersion = resultSet.getLong("entityVersion");
    paymentPlan.created = resultSet.getDate("created");
    paymentPlan.updated = resultSet.getTimestamp("updated");
    paymentPlan.dailyInterest = resultSet.getDouble("dailyInterest");
    paymentPlan.interestAmountTotal = resultSet.getInt("interestAmountTotal");
    paymentPlan.lastDueDate = resultSet.getDate("lastDueDate");
    paymentPlan.lastPaymentAmount = resultSet.getInt("lastPaymentAmount");
    paymentPlan.maximumPaymentPeriod = resultSet.getInt("maximumPaymentPeriod");
    paymentPlan.mmp = resultSet.getDouble("mmp");
    paymentPlan.openingFeeTotal = resultSet.getInt("openingFeeTotal");
    paymentPlan.paymentDayOfMonth = resultSet.getInt("paymentDayOfMonth");
    paymentPlan.provisionFeeTotal = resultSet.getInt("provisionFeeTotal");
    paymentPlan.repaymentIban = resultSet.getString("repaymentIban");
    paymentPlan.repaymentTotal = resultSet.getInt("repaymentTotal");
    paymentPlan.totalAnnualSum = resultSet.getInt("totalAnnualSum");
    paymentPlan.totalCostOfLoan = resultSet.getInt("totalCostOfLoan");
    paymentPlan.totalMonthlySum = resultSet.getInt("totalMonthlySum");
    paymentPlan.mandatoryFeeTotal = resultSet.getInt("mandatoryFeeTotal");
    paymentPlan.contractId = resultSet.getLong("contract_ID");
    return paymentPlan;
  }
}
