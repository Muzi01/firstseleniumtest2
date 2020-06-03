package bindings.commons.automation.generator.model.aio.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentPlanRowMapper implements ResultMapper<PaymentPlanRow> {

  @Override
  public PaymentPlanRow mapByResultSet(final ResultSet resultSet) throws SQLException {
    final PaymentPlanRow paymentPlanRow = new PaymentPlanRow();
    paymentPlanRow.id = resultSet.getLong("ID");
    paymentPlanRow.beginningBalance = resultSet.getInt("beginningBalance");
    paymentPlanRow.created = resultSet.getDate("created");
    paymentPlanRow.entityVersion = resultSet.getLong("entityVersion");
    paymentPlanRow.interestAmount = resultSet.getInt("interestAmount");
    paymentPlanRow.mandatoryFee = resultSet.getInt("mandatoryFee");
    paymentPlanRow.openingFee = resultSet.getInt("openingFee");
    paymentPlanRow.paymentDate = resultSet.getDate("paymentDate");
    paymentPlanRow.paymentNumber = resultSet.getInt("paymentNumber");
    paymentPlanRow.paymentPlanId = resultSet.getLong("paymentPlan_ID");
    paymentPlanRow.repayment = resultSet.getInt("repayment");
    paymentPlanRow.totalMonthly = resultSet.getInt("totalMonthly");
    paymentPlanRow.updated = resultSet.getTimestamp("updated");
    return paymentPlanRow;
  }
}
