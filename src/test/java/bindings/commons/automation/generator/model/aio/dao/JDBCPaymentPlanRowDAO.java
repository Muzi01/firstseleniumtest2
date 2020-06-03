package bindings.commons.automation.generator.model.aio.dao;

import com.ipfdigital.automation.generator.model.aio.JDBCUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JDBCPaymentPlanRowDAO implements PaymentPlanRowDAO {

  private static final Logger LOGGER = LogManager.getLogger(JDBCPaymentPlanRowDAO.class);

  private static final String SQL_GET_PAYMENT_PLAN_ROW_QUERY =
      "SELECT * FROM PaymentPlanRow ppr JOIN PaymentPlan pp ON pp.ID = ppr.paymentPlan_ID JOIN Contract co ON co.ID = pp.contract_ID JOIN Customer cu ON cu.ID = co.customer_ID WHERE cu.id = ? ORDER BY ppr.ID ASC";

  @Override
  public PaymentPlanRow getByCustomerId(final Long customerId, final String env) {
    LOGGER.info("Searching for PaymentPlanRow on env: {} for customerId: {}", env,
        customerId);
    return new JDBCUtils<PaymentPlanRow>().getByCustomerId(
        SQL_GET_PAYMENT_PLAN_ROW_QUERY, env, customerId, new PaymentPlanRowMapper());
  }
}
