package bindings.commons.automation.generator.model.aio.dao;

import com.ipfdigital.automation.generator.model.aio.JDBCUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class JDBCPaymentPlanDAO implements PaymentPlanDAO {

  private static final Logger LOGGER = LogManager.getLogger(JDBCPaymentPlanDAO.class);

  private static final String SQL_GET_PAYMENT_PLAN_QUERY =
      "SELECT * FROM PaymentPlan pp JOIN Contract co ON co.ID = pp.contract_ID JOIN Customer cu ON cu.ID = co.customer_ID WHERE cu.id = ? ORDER BY pp.ID DESC";

  @Override
  public PaymentPlan getByCustomerId(final Long customerId, final String env) {
    LOGGER.info("Searching for PaymentPlan on env: {} for customerId: {}", env,
        customerId);
    return new JDBCUtils<PaymentPlan>().getByCustomerId(
        SQL_GET_PAYMENT_PLAN_QUERY, env, customerId, new PaymentPlanMapper());
  }
}
