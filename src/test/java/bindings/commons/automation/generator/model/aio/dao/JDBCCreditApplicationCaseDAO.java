package bindings.commons.automation.generator.model.aio.dao;

import com.ipfdigital.automation.generator.model.aio.JDBCUtils;
import com.ipfdigital.automation.generator.model.aio.cases.CreditApplicationCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class JDBCCreditApplicationCaseDAO implements CreditApplicationCaseDAO {
  private static final Logger LOGGER = LogManager.getLogger(JDBCCreditApplicationCaseDAO.class);
  private static final String SQL_GET_QUERY =
      "SELECT cac.* FROM CreditApplicationCase cac "
          + "JOIN CreditApplication ca ON ca.ID = cac.creditApplication_ID "
          + "JOIN Customer cus ON ca.customer_ID = cus.ID "
          + "WHERE cus.ID = ? ORDER BY cac.ID DESC";

  @Override
  public CreditApplicationCase getByCustomerId(final Long customerId, final String env) {
    LOGGER.info("Searching for CreditApplicationCase on env: {} for customerId: {}", env,
        customerId);
    return new JDBCUtils<CreditApplicationCase>().getByCustomerId(
        SQL_GET_QUERY, env, customerId,
        new CreditApplicationCaseMapper());
  }
}
